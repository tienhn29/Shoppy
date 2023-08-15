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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoppy.R;
import com.example.shoppy.adapter.SanphammoinhatAdapter;
import com.example.shoppy.adapter.ThuongHieuSanPhamAdapter;
import com.example.shoppy.model.SanPham;
import com.example.shoppy.model.ThuongHieuSanPham;
import com.example.shoppy.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamTheoLoaiActivity extends AppCompatActivity {
    Toolbar toolbarsptheoloai;
    RecyclerView recyclerviewthuonghieusptheoloai,recyclerviewspmoinhattheoloaisp;

    ArrayList<SanPham> mangsanphammoinhat;
    SanphammoinhatAdapter sanphammoinhatAdapter;
    
    ArrayList<ThuongHieuSanPham> mangthuonghieusanpham;
    ThuongHieuSanPhamAdapter thuongHieuSanPhamAdapter;

    public static int idloaisanpham = 0;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham_theo_loai);
        
        Initial();
        ActionToolcBar();
        GetIDLoaisp();
        GetDataSanPhamMoiNhat(page);
        GetDataThuongHieuSanPham();
    }

    private void GetDataThuongHieuSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanthuonghieu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length() != 2){
                    int Id = 0;
                    String Tenthuonghieu = "";
                    String Hinhanhthuonghieu = "";
                    int Idloaisanpham = 0;
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(response);
                        for (int i = 0 ; i <jsonArray.length() ; i++){
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Id = jsonObject.getInt("id");
                                Tenthuonghieu = jsonObject.getString("tenthuonghieu");
                                Hinhanhthuonghieu = jsonObject.getString("hinhanhthuonghieu");
                                Idloaisanpham = jsonObject.getInt("idloaisanpham");
                                mangthuonghieusanpham.add(new ThuongHieuSanPham(Id,Tenthuonghieu,Hinhanhthuonghieu,Idloaisanpham));
                                thuongHieuSanPhamAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
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
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void GetDataSanPhamMoiNhat(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getDuongdansanphammoinhat1 + String.valueOf(Page), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length() != 2){
                    Log.d("tien",response);
                    Log.d("tien",String.valueOf(idloaisanpham));
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
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void GetIDLoaisp() {
        idloaisanpham = getIntent().getIntExtra("idloaisanpham",-1);
    }

    private void ActionToolcBar() {
        setSupportActionBar(toolbarsptheoloai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarsptheoloai.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Initial() {
        toolbarsptheoloai = findViewById(R.id.toolbarsptheoloai);
        recyclerviewthuonghieusptheoloai = findViewById(R.id.recyclerviewthuonghieusptheoloai);
        recyclerviewspmoinhattheoloaisp = findViewById(R.id.recyclerviewspmoinhattheoloaisp);

        mangsanphammoinhat = new ArrayList<>();
        sanphammoinhatAdapter = new SanphammoinhatAdapter(getApplicationContext(),mangsanphammoinhat);
        recyclerviewspmoinhattheoloaisp.setHasFixedSize(true);
        recyclerviewspmoinhattheoloaisp.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerviewspmoinhattheoloaisp.setNestedScrollingEnabled(false);
        recyclerviewspmoinhattheoloaisp.setAdapter(sanphammoinhatAdapter);
        
        mangthuonghieusanpham = new ArrayList<>();
        thuongHieuSanPhamAdapter = new ThuongHieuSanPhamAdapter(getApplicationContext(),mangthuonghieusanpham);
        recyclerviewthuonghieusptheoloai.setHasFixedSize(true);
        recyclerviewthuonghieusptheoloai.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,RecyclerView.HORIZONTAL,false));
        recyclerviewthuonghieusptheoloai.setNestedScrollingEnabled(false);
        recyclerviewthuonghieusptheoloai.setAdapter(thuongHieuSanPhamAdapter);

    }
}