package com.example.project.Adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Entity.Category;
import com.example.project.Interface.IClickItemCategory;
import com.example.project.R;

import java.util.ArrayList;

public class StaticRecycleViewAdapter extends RecyclerView.Adapter<StaticRecycleViewAdapter.StaticRecycleViewHolder> {

    private ArrayList<Category> items;
    private IClickItemCategory iClickItemCategory;
    public StaticRecycleViewAdapter(ArrayList<Category> items,IClickItemCategory iClickItemCategory){
        this.items = items;
        this.iClickItemCategory = iClickItemCategory;
    }

    @NonNull
    @Override
    public StaticRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_view,parent,false);

        return new StaticRecycleViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRecycleViewHolder holder, int position) {
        Category currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getContent());
        holder.itemView.getBackground().setColorFilter(Color.parseColor(currentItem.getColor()), PorterDuff.Mode.DARKEN);

        holder.linerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemCategory.onClickItemCategory(currentItem.getContent().toString());
            }
        });
    }

    public  static  class StaticRecycleViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        LinearLayout linerLayout;

        public StaticRecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            linerLayout = itemView.findViewById(R.id.linerLayout);
            imageView = itemView.findViewById(R.id.image_static);
            textView = itemView.findViewById(R.id.tv_static);

        }
    }

}
