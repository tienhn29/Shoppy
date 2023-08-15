package com.example.shoppy.adapter;

import com.example.shoppy.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppy.activity.ChiTietSanPhamActivity;
import com.example.shoppy.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphammoinhatAdapter extends RecyclerView.Adapter<SanphammoinhatAdapter.ItemHolerspnew> {
    Context context;
    ArrayList<SanPham> arraysanphammoinhat;

    public SanphammoinhatAdapter(Context context, ArrayList<SanPham> arraysanphammoinhat) {
        this.context = context;
        this.arraysanphammoinhat = arraysanphammoinhat;
    }

    @NonNull
    @Override
    public ItemHolerspnew onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_recyclerview_sanphammoinhat,null);
        ItemHolerspnew itemHolerspnew = new ItemHolerspnew(view);
        return itemHolerspnew;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolerspnew holder, int position) {
        holder.txttenspmoinhat.setText(arraysanphammoinhat.get(position).getTensanpham());
        holder.txttenspmoinhat.setMaxLines(1);
        holder.txttenspmoinhat.setEllipsize(TextUtils.TruncateAt.END);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiaspmoinhat.setText("Giá: " + decimalFormat.format(arraysanphammoinhat.get(position).getGiasanpham()) + " Đ");
        Picasso.get().load(arraysanphammoinhat.get(position).getHinhanhsanpham()).into(holder.imgspmoinhat);
    }

    @Override
    public int getItemCount() {
        return arraysanphammoinhat.size();
    }

    public class ItemHolerspnew extends RecyclerView.ViewHolder{
        TextView txttenspmoinhat,txtgiaspmoinhat;
        ImageView imgspmoinhat;

        public ItemHolerspnew(@NonNull View itemView) {
            super(itemView);

            txttenspmoinhat = itemView.findViewById(R.id.textviewtenspmoinhat);
            txtgiaspmoinhat = itemView.findViewById(R.id.textviewgiaspmoinhat);
            imgspmoinhat = itemView.findViewById(R.id.imageviewspmoinhat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    intent.putExtra("thongtinsanpham",arraysanphammoinhat.get(getBindingAdapterPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
