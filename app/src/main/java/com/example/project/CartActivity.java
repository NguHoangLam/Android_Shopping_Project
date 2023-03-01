package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.project.Adapter.ProductAdapter;
import com.example.project.Database.DBHelper;
import com.example.project.Entity.Account;
import com.example.project.Entity.Cart;
import com.example.project.Entity.Product;
import com.example.project.Interface.IClickItemProduct;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    TextView tv_money;
    Button btnPay;
    ImageButton btn_cartBack;
    DBHelper db;
    Account currentAccount;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    ImageView img_logo,slashImg;
    LottieAnimationView lottieAnimationView;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);
        getControls();
        db = new DBHelper(CartActivity.this);
        // get Intent
        Intent callerIntent = getIntent();
        Bundle packageCaller = callerIntent.getBundleExtra("MyPackage");
        currentAccount = (Account)  packageCaller.getSerializable("account");
        btnPay.setEnabled(false);
        ArrayList<Cart> cartList = db.getAllUnFinishCart(currentAccount.getId());
        ArrayList<Product> listProducts = new ArrayList<>();
        int totalMoney =0;
        for (int i = 0; i < cartList.size(); i++) {
            Product product = db.getProduct(cartList.get(i).getProductId());
            totalMoney+= product.getPrice();
            listProducts.add(product);
        }
        if (totalMoney>0){
            btnPay.setEnabled(true);
        }

        slashImg.setY(5000);
        img_logo.setY(5000);
        lottieAnimationView.setY(5000);
        tv_title.setY(5000);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView_product);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(gridLayoutManager);
        productAdapter = new ProductAdapter(listProducts, new IClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {

            }
        });
        recyclerView.setAdapter(productAdapter);
        tv_money.setText(totalMoney+" VND");

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < cartList.size(); i++) {
                    db.updateCart(cartList.get(i));
                }
                btnPay.setEnabled(false);
                tv_money.setText("0 VND");
                ArrayList<Product> listProducts = new ArrayList<>();
                //reset product
                productAdapter = new ProductAdapter(listProducts, new IClickItemProduct() {
                    @Override
                    public void onClickItemProduct(Product product) {

                    }
                });
                recyclerView.setAdapter(productAdapter);

                slashImg.animate().translationY(0).setDuration(1000).setStartDelay(0);
                img_logo.animate().translationY(0).setDuration(1000).setStartDelay(0);
                lottieAnimationView.animate().translationY(0).setDuration(1000).setStartDelay(0);
                tv_title.animate().translationY(0).setDuration(1000).setStartDelay(0);



            }
        });
        btn_cartBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        slashImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slashImg.animate().translationY(-7000).setDuration(1000).setStartDelay(500);
                img_logo.animate().translationX(6000).setDuration(1000).setStartDelay(500);
                lottieAnimationView.animate().translationX(-5000).setDuration(1000).setStartDelay(500);
                tv_title.animate().translationY(7000).setDuration(1000).setStartDelay(500);
            }
        });
    }

    private void getControls(){
        tv_money = (TextView) findViewById(R.id.tv_money);
        btnPay = (Button) findViewById(R.id.btnPay);
        btn_cartBack = (ImageButton) findViewById(R.id.btn_cartBack);
        img_logo = findViewById(R.id.img_logo);
        slashImg = findViewById(R.id.img_bg);
        lottieAnimationView = findViewById(R.id.lottie);
        tv_title = findViewById(R.id.tv_title);

    }
}