package com.example.shoppy.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoppy.R;
import com.example.shoppy.adapter.SanphammoinhatAdapter;
import com.example.shoppy.model.SanPham;
import com.example.shoppy.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamTheoThuongHieuActivity extends AppCompatActivity {

    Toolbar toolbarsptheothuonghieu;
    ImageView imageviewquaylaisptheothuonghieu,imageviewhinhanhsptheothuonghieu;
    RecyclerView recyclerviewsptheothuonghieu;

    ArrayList<SanPham> mangsanphammoinhat;
    SanphammoinhatAdapter sanphammoinhatAdapter;

    int idloaisanpham = 0;
    int idthuonghieu = 0;
    String hinhanhthuonghieu = "";

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham_theo_thuong_hieu);
        Initial();
        ActionToolBar();
        GetDataIntent();
        GetDataSanPhamMoiNhat(page);
    }

    private void GetDataSanPhamMoiNhat(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansanphamtheothuonghieu + String.valueOf(Page), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length() != 2){
                    int Id;
                    String Tendt;
                    Integer Giadt;
                    String Motadt;
                    String Hinhdt;
                    int Idloaisp;
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(response);
                        for (int i = 0 ; i <jsonArray.length() ; i++){
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Id = jsonObject.getInt("id");
                                Tendt = jsonObject.getString("tensp");
                                Giadt = jsonObject.getInt("giasp");
                                Motadt = jsonObject.getString("motasp");
                                Hinhdt = jsonObject.getString("hinhanhsp");
                                Idloaisp = jsonObject.getInt("idloaisanpham");
                                mangsanphammoinhat.add(new SanPham(Id,Tendt,Giadt,Motadt,Hinhdt,Idloaisp));
                                sanphammoinhatAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Hết",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("idloaisanpham",String.valueOf(idloaisanpham));
                param.put("idthuonghieu",String.valueOf(idthuonghieu));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void GetDataIntent() {
        idloaisanpham = getIntent().getIntExtra("idloaisanpham",-1);
        idthuonghieu = getIntent().getIntExtra("idthuonghieu",-1);
        hinhanhthuonghieu = getIntent().getStringExtra("hinhanhthuonghieu");
        Picasso.get().load(hinhanhthuonghieu).into(imageviewhinhanhsptheothuonghieu);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarsptheothuonghieu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarsptheothuonghieu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Initial() {
        toolbarsptheothuonghieu = findViewById(R.id.toolbarsptheothuonghieu);
        recyclerviewsptheothuonghieu = findViewById(R.id.recyclerviewsptheothuonghieu);
        imageviewhinhanhsptheothuonghieu = findViewById(R.id.imageviewhinhanhsptheothuonghieu);


        mangsanphammoinhat = new ArrayList<>();
        sanphammoinhatAdapter = new SanphammoinhatAdapter(getApplicationContext(),mangsanphammoinhat);
        recyclerviewsptheothuonghieu.setHasFixedSize(true);
        recyclerviewsptheothuonghieu.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerviewsptheothuonghieu.setNestedScrollingEnabled(false);
        recyclerviewsptheothuonghieu.setAdapter(sanphammoinhatAdapter);
    }
}