package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Adapter.ProductAdapter;
import com.example.project.Adapter.StaticRecycleViewAdapter;
import com.example.project.Database.DBHelper;
import com.example.project.Entity.Account;
import com.example.project.Entity.Cart;
import com.example.project.Entity.Category;
import com.example.project.Entity.Product;
import com.example.project.Interface.IClickItemCategory;
import com.example.project.Interface.IClickItemProduct;

import java.util.ArrayList;
import java.util.Locale;

public class MainPageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private StaticRecycleViewAdapter staticRecycleViewAdapter;
    private ProductAdapter productAdapter;
    private TextView tv_name,tv_currentCart;
    private ImageView img_search;
    private EditText edit_search;
    private ImageButton btn_goCart;
    ArrayList<Product> itemProduct;
    DBHelper db;
    Account currentAccount;
    public  static final int REQUEST = 100;
    public  static final int UPDATE_CART = 300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getControls();



        Intent callerIntent = getIntent();
        Bundle packageCaller = callerIntent.getBundleExtra("MyPackage");
        currentAccount = (Account) packageCaller.getSerializable("account");
        tv_name.setText(currentAccount.getUsername()+"!");

        ArrayList<Cart> listCart = db.getAllUnFinishCart(currentAccount.getId());
        tv_currentCart.setText(listCart.size()+" Items");

        ArrayList<Category> item = new ArrayList<>();
        item.add(new Category(R.drawable.cate1,Product.TYPE_GUNDAM,"#fefcfe"));
        item.add(new Category(R.drawable.cate2,Product.TYPE_GAME,"#fed7cc"));
        item.add(new Category(R.drawable.cate3,Product.TYPE_ANIME,"#ebfbea"));
        item.add(new Category(R.drawable.cate4,Product.TYPE_POKEMON,"#FFA07A"));

        this.recyclerView = (RecyclerView) findViewById(R.id.rv1);

        staticRecycleViewAdapter = new StaticRecycleViewAdapter(item, new IClickItemCategory() {
            @Override
            public void onClickItemCategory(String category) {
                itemProduct= filterByType(db.getAllProducts(), category);
                loadData();
            }
        });

        recyclerView.setAdapter(staticRecycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


        itemProduct =  db.getAllProducts();
        recyclerView2 = (RecyclerView) findViewById(R.id.rv2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        loadData();
        recyclerView2.setLayoutManager(gridLayoutManager);



        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemProduct= db.getAllProducts();
                loadData();
            }
        });
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = edit_search.getText().toString();
                itemProduct= filterByName(db.getAllProducts(),search);
                loadData();


            }
        });

        btn_goCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPageActivity.this,CartActivity.class);
                Bundle bundle =new Bundle();
                bundle.putSerializable("account",currentAccount);
                intent.putExtra("MyPackage",bundle);
                startActivityForResult(intent,REQUEST);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<Cart> listCart = db.getAllUnFinishCart(currentAccount.getId());
        tv_currentCart.setText(listCart.size()+" Items");
    }



    public void getControls(){
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_currentCart = (TextView) findViewById(R.id.tv_currentCart);
        img_search = (ImageView) findViewById(R.id.img_search);
        edit_search = (EditText) findViewById(R.id.edit_search);
        btn_goCart = (ImageButton) findViewById(R.id.btn_goCart);
        db =new DBHelper(this);
    }

    public ArrayList<Product> filterByType(ArrayList<Product> list, String type){
        ArrayList<Product> newList  = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().compareTo(type)==0){
                newList.add(list.get(i));
            }
        }
        return newList;
    }
    public ArrayList<Product> filterByName(ArrayList<Product> list, String name){
        name = name.toLowerCase(Locale.ROOT);
        ArrayList<Product> newList  = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().toLowerCase(Locale.ROOT).contains(name)){
                newList.add(list.get(i));
            }
        }
        return newList;
    }


    public void loadData(){
        productAdapter = new ProductAdapter(itemProduct, new IClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {
                Intent intent = new Intent(MainPageActivity.this,DetailProduct.class);
                Bundle bundle =new Bundle();
                bundle.putSerializable("product",product);
                bundle.putSerializable("account",currentAccount);
                intent.putExtra("MyPackage",bundle);
                startActivityForResult(intent,REQUEST);


            }
        });
        recyclerView2.setAdapter(productAdapter);
    }

}