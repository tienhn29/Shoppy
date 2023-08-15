package com.example.shoppy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shoppy.R;
import com.example.shoppy.adapter.GioHangAdapter;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    Toolbar toolbargiohang;
    ListView listViewgiohang;
    static TextView txtgiohangdangtrong,txttongtien;
    Button btndatmua,btntieptucmua;

    static GioHangAdapter gioHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        Initial();
        ActionToolbar();
        CatchItemListvView();
        TongTienAll();
        CatchButton();
    }

    private void CatchButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),HomeScreenActivity.class);
                startActivity(intent);
            }
        });

        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void TongTienAll() {
        long Tongtienall = 0;
        for (int i = 0 ; i < HomeScreenActivity.manggiohang.size() ; i++){
            Tongtienall += HomeScreenActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(Tongtienall) + " D");
    }

    private void CatchItemListvView() {
        if (HomeScreenActivity.manggiohang.size() > 0){
            txtgiohangdangtrong.setVisibility(View.INVISIBLE);
        }
//        listViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
//                builder.setTitle("xac nhan xoa san pham");
//                builder.setMessage("ban chac chan muon xoa san pham ra khoi gio hang?");
//                builder.setPositiveButton("co", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        MainActivity.manggiohang.remove(position);
//                        if (MainActivity.manggiohang.size() == 0){
//                            txtgiohangdangtrong.setVisibility(View.VISIBLE);
//                        }
//                        Capnhat();
//                        TongTienAll();
//                    }
//                });
//                builder.setNegativeButton("khong", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Capnhat();
//                    }
//                });
//                builder.show();
//                return true;
//            }
//        });
    }

    public static void Capnhat(){
        gioHangAdapter.notifyDataSetChanged();
        if (HomeScreenActivity.manggiohang.size() == 0){
            txtgiohangdangtrong.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Initial() {
        toolbargiohang = findViewById(R.id.toolbargiohang);
        listViewgiohang = findViewById(R.id.listviewgiohang);
        txtgiohangdangtrong = findViewById(R.id.textviewgiohangdangtrong);
        txttongtien = findViewById(R.id.textviewtongtien);
        btndatmua = findViewById(R.id.buttondatmua);
        btntieptucmua = findViewById(R.id.buttontieptucmua);

        gioHangAdapter = new GioHangAdapter(getApplicationContext(),HomeScreenActivity.manggiohang);
        listViewgiohang.setAdapter(gioHangAdapter);
    }
}