package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Database.DBHelper;
import com.example.project.Entity.Account;
import com.example.project.Entity.Product;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailProduct extends AppCompatActivity {
    CollapsingToolbarLayout tool_bar;
    ImageView img_view;
    TextView tv_price,tv_description;
    Button btn_buy;
    ImageButton btnBack;
    DBHelper db;
    FloatingActionButton btn_goToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);
        getControls();
        db = new DBHelper(DetailProduct.this);
        Intent callerIntent = getIntent();
        Bundle packageCaller = callerIntent.getBundleExtra("MyPackage");
        Product currentProduct= (Product) packageCaller.getSerializable("product");
        Account currentAccount = (Account)   packageCaller.getSerializable("account");

        img_view.setImageResource(currentProduct.getImage());
        tool_bar.setTitle(currentProduct.getName());
        tv_price.setText(currentProduct.getPrice()+" VND");
        tv_description.setText(currentProduct.getDescription());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(MainPageActivity.UPDATE_CART,null);
                finish();
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addCart(currentAccount.getId(),currentProduct.getId());
                Toast.makeText(DetailProduct.this,getString(R.string.newCartAdded),Toast.LENGTH_SHORT).show();
            }
        });
        btn_goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProduct.this,CartActivity.class);
                Bundle bundle =new Bundle();
                bundle.putSerializable("account",currentAccount);
                intent.putExtra("MyPackage",bundle);
                startActivity(intent);
            }
        });
    }

    private void getControls(){
        tool_bar = (CollapsingToolbarLayout) findViewById(R.id.tool_bar);
        img_view = (ImageView) findViewById(R.id.img_view);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_description = (TextView) findViewById(R.id.tv_description);
        btn_buy = (Button) findViewById(R.id.btn_buy);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btn_goToCart = (FloatingActionButton) findViewById(R.id.btn_goToCart);

    }
}