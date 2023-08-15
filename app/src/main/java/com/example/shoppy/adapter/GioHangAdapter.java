package com.example.shoppy.adapter;

import static com.example.shoppy.activity.GioHangActivity.Capnhat;
import static com.example.shoppy.activity.GioHangActivity.TongTienAll;

import com.example.shoppy.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppy.activity.HomeScreenActivity;
import com.example.shoppy.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arraygiohang;

    public GioHangAdapter(Context context, ArrayList<GioHang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolderItemGioHang{
        TextView txttenitemgiohang,txtgiaitemgiohang;
        EditText edtitemgiohang;
        ImageView imgitemgiohang;
        Button btntruitemgiohang,btncongitemgiohang;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItemGioHang viewHolderItemGioHang = null;
        if (convertView == null){
            viewHolderItemGioHang = new ViewHolderItemGioHang();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_listview_giohang,null);
            viewHolderItemGioHang.txttenitemgiohang = convertView.findViewById(R.id.textviewtenitemgiohang);
            viewHolderItemGioHang.txtgiaitemgiohang = convertView.findViewById(R.id.textviewgiaitemgiohang);
            viewHolderItemGioHang.edtitemgiohang = convertView.findViewById(R.id.edittextitemgiohang);
            viewHolderItemGioHang.imgitemgiohang = convertView.findViewById(R.id.imageviewitemgiohang);
            viewHolderItemGioHang.btntruitemgiohang = convertView.findViewById(R.id.buttontruitemgiohang);
            viewHolderItemGioHang.btncongitemgiohang = convertView.findViewById(R.id.buttoncongitemgiohang);
            convertView.setTag(viewHolderItemGioHang);
        }else {
            viewHolderItemGioHang = (ViewHolderItemGioHang) convertView.getTag();
        }
        viewHolderItemGioHang.txttenitemgiohang.setText(arraygiohang.get(position).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolderItemGioHang.txtgiaitemgiohang.setText(decimalFormat.format(arraygiohang.get(position).getGiasp())+" D");
        viewHolderItemGioHang.edtitemgiohang.setText(String.valueOf(arraygiohang.get(position).getSoluongsp()));
        Picasso.get().load(arraygiohang.get(position).getHinhsp()).into(viewHolderItemGioHang.imgitemgiohang);

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("xac nhan xoa san pham");
                builder.setMessage("ban chac chan muon xoa san pham ra khoi gio hang?");
                builder.setPositiveButton("co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HomeScreenActivity.manggiohang.remove(position);
                        Capnhat();
                        TongTienAll();
                    }
                });
                builder.setNegativeButton("khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Capnhat();
                    }
                });
                builder.show();
                return true;
            }
        });

        ViewHolderItemGioHang finalViewHolderItemGioHang = viewHolderItemGioHang;
        viewHolderItemGioHang.btncongitemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slcu = Integer.parseInt(finalViewHolderItemGioHang.edtitemgiohang.getText().toString().trim());
                int slmoi = slcu + 1;
                int gia1sp = HomeScreenActivity.manggiohang.get(position).getGiasp() / slcu;
                int giamoi = slmoi*gia1sp;
                finalViewHolderItemGioHang.edtitemgiohang.setText(String.valueOf(slmoi));
                finalViewHolderItemGioHang.txtgiaitemgiohang.setText(String.valueOf(decimalFormat.format(giamoi) + " D"));
                HomeScreenActivity.manggiohang.get(position).setGiasp(giamoi);
                HomeScreenActivity.manggiohang.get(position).setSoluongsp(slmoi);
                TongTienAll();
            }
        });
        viewHolderItemGioHang.btntruitemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int slcu = Integer.parseInt(finalViewHolderItemGioHang.edtitemgiohang.getText().toString().trim());
                final int slmoi = slcu - 1;
                final int gia1sp = HomeScreenActivity.manggiohang.get(position).getGiasp() / slcu;
                final int giamoi = slmoi*gia1sp;
                DecimalFormat decimalFormat1 = new DecimalFormat("###,###,###");
                if (slmoi == 0){
                    finalViewHolderItemGioHang.edtitemgiohang.setText(String.valueOf(1));
                    finalViewHolderItemGioHang.txtgiaitemgiohang.setText(String.valueOf(decimalFormat1.format(gia1sp)) + " D");
                }else {
                    finalViewHolderItemGioHang.edtitemgiohang.setText(String.valueOf(slmoi));
                    finalViewHolderItemGioHang.txtgiaitemgiohang.setText(String.valueOf(decimalFormat1.format(giamoi)) + " D");
                    HomeScreenActivity.manggiohang.get(position).setGiasp(giamoi);
                    HomeScreenActivity.manggiohang.get(position).setSoluongsp(slmoi);
                }

                if (slmoi < 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                    builder.setTitle("xac nhan xoa san pham?");
                    builder.setMessage("Ban chac chan xoa san pham nay ra khoi gio hang?");
                    builder.setPositiveButton("co", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HomeScreenActivity.manggiohang.remove(position);
                            Capnhat();
                            TongTienAll();
                        }
                    });
                    builder.setNegativeButton("khong", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HomeScreenActivity.manggiohang.get(position).setSoluongsp(1);
                            finalViewHolderItemGioHang.txtgiaitemgiohang.setText(String.valueOf(decimalFormat1.format(gia1sp)) + " D");
                            HomeScreenActivity.manggiohang.get(position).setGiasp(gia1sp);
                        }
                    });
                    builder.show();
                }
            }
        });
        return convertView;
    }
}
