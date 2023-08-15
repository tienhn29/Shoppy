package com.example.shoppy.adapter;

import com.example.shoppy.R;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppy.activity.ChiTietSanPhamActivity;
import com.example.shoppy.activity.SanPhamTheoLoaiActivity;
import com.example.shoppy.activity.SanPhamTheoThuongHieuActivity;
import com.example.shoppy.model.ThuongHieuSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThuongHieuSanPhamAdapter extends RecyclerView.Adapter<ThuongHieuSanPhamAdapter.ItemHolerThuongHieu> {
    Context context;
    ArrayList<ThuongHieuSanPham> arraythuonghieusanpham;

    public ThuongHieuSanPhamAdapter(Context context, ArrayList<ThuongHieuSanPham> arraythuonghieusanpham) {
        this.context = context;
        this.arraythuonghieusanpham = arraythuonghieusanpham;
    }

    @NonNull
    @Override
    public ItemHolerThuongHieu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_recyclerview_thuonghieu,null);
        ItemHolerThuongHieu itemHolerThuongHieu = new ItemHolerThuongHieu(view);
        return itemHolerThuongHieu;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolerThuongHieu holder, int position) {
        Picasso.get().load(arraythuonghieusanpham.get(position).getHinhanhthuonghieu()).into(holder.imgthuonghieusanpham);
    }

    @Override
    public int getItemCount() {
        return arraythuonghieusanpham.size();
    }

    public class ItemHolerThuongHieu extends RecyclerView.ViewHolder{
        ImageView imgthuonghieusanpham;

        public ItemHolerThuongHieu(@NonNull View itemView) {
            super(itemView);
            imgthuonghieusanpham = itemView.findViewById(R.id.imageviewhinhanhthuonghieu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SanPhamTheoThuongHieuActivity.class);
                    intent.putExtra("idloaisanpham", SanPhamTheoLoaiActivity.idloaisanpham);
                    intent.putExtra("idthuonghieu",arraythuonghieusanpham.get(getBindingAdapterPosition()).getId());
                    intent.putExtra("hinhanhthuonghieu",arraythuonghieusanpham.get(getBindingAdapterPosition()).getHinhanhthuonghieu());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
