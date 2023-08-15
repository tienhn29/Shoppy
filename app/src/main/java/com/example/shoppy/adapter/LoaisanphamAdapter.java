package com.example.shoppy.adapter;

import com.example.shoppy.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppy.activity.SanPhamTheoLoaiActivity;
import com.example.shoppy.model.LoaiSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaisanphamAdapter extends RecyclerView.Adapter<LoaisanphamAdapter.ItemHoler> {
    Context context;
    ArrayList<LoaiSanPham> arrayloaisanpham;

    public LoaisanphamAdapter(Context context, ArrayList<LoaiSanPham> arrayloaisanpham) {
        this.context = context;
        this.arrayloaisanpham = arrayloaisanpham;
    }

    @NonNull
    @Override
    public ItemHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_recyclerview_loaisanpham,null);
        ItemHoler itemHoler = new ItemHoler(view);
        return itemHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHoler holder, int position) {
        LoaiSanPham loaiSanPham = arrayloaisanpham.get(position);
        holder.txttenloaisanpham.setText(loaiSanPham.getTenloaisanpham());
        Picasso.get().load(loaiSanPham.getHinhanhloaisanpham()).into(holder.imgloaisanpham);
    }

    @Override
    public int getItemCount() {
        return arrayloaisanpham.size();
    }

    public class ItemHoler extends RecyclerView.ViewHolder{
        public TextView txttenloaisanpham;
        public ImageView imgloaisanpham;
        public ItemHoler(@NonNull View itemView) {
            super(itemView);

            txttenloaisanpham = itemView.findViewById(R.id.textviewtenloaisanpham);
            imgloaisanpham = itemView.findViewById(R.id.imageviewhinhanhloaisanpham);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SanPhamTheoLoaiActivity.class);
                    intent.putExtra("idloaisanpham",arrayloaisanpham.get(getBindingAdapterPosition()).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
