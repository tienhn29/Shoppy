package com.example.shoppy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppy.R;
import com.example.shoppy.model.GioHang;
import com.example.shoppy.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    Toolbar toolbarchitietsp;
    ImageView imgchitietsp;
    TextView txttenchitietsp,txtgiachitietsp,txtmotachitietsp;
    EditText edtchitietsp;
    Button btntruchitietsp,btncongchitietsp,btnthemvaogiohangchitietsp;


    int Id = 0;
    String Tenchitiet = "";
    Integer Giachitiet = 0;
    String MotaChitiet = "";
    String HinhChitiet = "";
    int Idsanpham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        Initial();
        ActionToolbar();
        GetData();
        EventButton();
    }

    private void EventButton() {
        int sl = Integer.parseInt(edtchitietsp.getText().toString().trim());
        if (sl < 2){
            btntruchitietsp.setVisibility(View.INVISIBLE);
        }
        btncongchitietsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slcong = Integer.parseInt(edtchitietsp.getText().toString().trim());
                edtchitietsp.setText(String.valueOf(slcong + 1));
                if (Integer.parseInt(edtchitietsp.getText().toString().trim()) > 1){
                    btntruchitietsp.setVisibility(View.VISIBLE);
                }
            }
        });
        btntruchitietsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sltru = Integer.parseInt(edtchitietsp.getText().toString().trim());
                edtchitietsp.setText(String.valueOf(sltru - 1));
                if (Integer.parseInt(edtchitietsp.getText().toString().trim()) < 2){
                    btntruchitietsp.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnthemvaogiohangchitietsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluongmoi = Integer.parseInt(edtchitietsp.getText().toString().trim());
                int giamoi = Giachitiet * soluongmoi;
                boolean dathuchien = false;
                if (HomeScreenActivity.manggiohang.size() > 0){
                    for (int i = 0 ; i < HomeScreenActivity.manggiohang.size() ; i++){
                        if (HomeScreenActivity.manggiohang.get(i).getIdsp() == Id){
                            int soluongcu = HomeScreenActivity.manggiohang.get(i).getSoluongsp();
                            int soluongmoinhat = soluongcu + soluongmoi;
                            HomeScreenActivity.manggiohang.get(i).setSoluongsp(soluongmoinhat);
                            HomeScreenActivity.manggiohang.get(i).setGiasp(Giachitiet*soluongmoinhat);
                            dathuchien = true;
                        }
                    }
                    if (dathuchien == false){
                        HomeScreenActivity.manggiohang.add(new GioHang(Id,Tenchitiet,giamoi,HinhChitiet,soluongmoi));
                    }
                }else {
                    HomeScreenActivity.manggiohang.add(new GioHang(Id,Tenchitiet,giamoi,HinhChitiet,soluongmoi));
                }
                Intent intent = new Intent(ChiTietSanPhamActivity.this,GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetData() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        Id = sanPham.getId();
        Tenchitiet = sanPham.getTensanpham();
        Giachitiet = sanPham.getGiasanpham();
        MotaChitiet = sanPham.getMotasanpham();
        HinhChitiet = sanPham.getHinhanhsanpham();
        Idsanpham = sanPham.getId();

        txttenchitietsp.setText(Tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgiachitietsp.setText(decimalFormat.format(Giachitiet) + " D");
        txtmotachitietsp.setText(MotaChitiet);
        Picasso.get().load(HinhChitiet).into(imgchitietsp);

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarchitietsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitietsp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Initial() {
        toolbarchitietsp = findViewById(R.id.toolbarchitietsanpham);
        imgchitietsp = findViewById(R.id.imageviewchitietsp);
        txttenchitietsp = findViewById(R.id.textviewtenchitietsp);
        txtgiachitietsp = findViewById(R.id.textviewgiachitietsp);
        txtmotachitietsp = findViewById(R.id.textviewmotachitiet);
        edtchitietsp = findViewById(R.id.edittextchitietsp);
        btntruchitietsp = findViewById(R.id.buttontruchitietsp);
        btncongchitietsp = findViewById(R.id.buttoncongchitietsp);
        btnthemvaogiohangchitietsp = findViewById(R.id.butoonthemvaogiohangchitietsp);
    }
}