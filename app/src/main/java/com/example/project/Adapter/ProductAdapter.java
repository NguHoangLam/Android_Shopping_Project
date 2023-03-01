package com.example.project.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Entity.Product;
import com.example.project.Interface.IClickItemProduct;
import com.example.project.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> items;
    private IClickItemProduct iClickItemProduct;
    public ProductAdapter(ArrayList<Product> items,IClickItemProduct iClickItemProduct) {
        this.items = items;
        this.iClickItemProduct = iClickItemProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ProductViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = items.get(position);
        if (product == null) {
            return;
        }
        holder.img.setImageResource(product.getImage());
        holder.tv_name.setText(product.getName());
        holder.tv_price.setText(product.getPrice()+" VND");
        holder.cardview_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemProduct.onClickItemProduct(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else
            return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv_name;
        private TextView    tv_price;
        private CardView cardview_product;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_product);
            tv_name = itemView.findViewById(R.id.tv_product_name);
            tv_price = itemView.findViewById(R.id.tv_product_price);
            cardview_product = itemView.findViewById(R.id.cardview_product);

        }
    }
}
