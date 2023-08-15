package com.example.shoppy.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoppy.R;
import com.example.shoppy.adapter.LoaisanphamAdapter;
import com.example.shoppy.adapter.SanphammoinhatAdapter;
import com.example.shoppy.model.GioHang;
import com.example.shoppy.model.LoaiSanPham;
import com.example.shoppy.model.SanPham;
import com.example.shoppy.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeScreenActivity extends AppCompatActivity {
    ViewFlipper viewFlipperHomeScreen;
    RecyclerView recyclerViewhomeloaisanpham,recyclerViewhomspmoinhat;
    ImageView imageViewhomegiohang;

    ProgressBar progressBarHome;

    LinearLayout linearLayouthomehome,linearlayouttoihome;

    ArrayList<LoaiSanPham> mangloaisanpham;
    LoaisanphamAdapter loaisanphamAdapter;

    ArrayList<SanPham> mangsanphammoinhat;
    SanphammoinhatAdapter sanphammoinhatAdapter;

    NestedScrollView nestedScrollViewhome;

    View footerview;

    int page = 1;

    boolean isLoading = false;

    boolean limitData = false;

    int firstVisibleItem,visibleItemCount,totalItemCount;

    MHandler mHandler;


    public static ArrayList<GioHang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Initial();
        ActionButton();
        ActionViewFlipper();
        GetDataLoaisanpham();
        GetDataSanPhamMoiNhat(page);
        //LoadMoreData();
    }

    private void ActionButton() {
        imageViewhomegiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, GioHangActivity.class));
            }
        });

        linearLayouthomehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(HomeScreenActivity.this,HomeScreenActivity.class));
            }
        });

        linearlayouttoihome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this,ToiActivity.class));
            }
        });
    }

    private void LoadMoreData() {

        recyclerViewhomspmoinhat.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerViewhomspmoinhat.getChildCount();
                totalItemCount = recyclerViewhomspmoinhat.getAdapter().getItemCount();


                LinearLayoutManager mLayoutManager = (LinearLayoutManager) recyclerViewhomspmoinhat.getLayoutManager();

                int latVisible = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (latVisible + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading == false && limitData == false){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                    //recyclerViewhomspmoinhat.removeView(footerview);
                    progressBarHome.setVisibility(View.GONE);
                }
            }
        });
    }

    private void GetDataSanPhamMoiNhat(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansanphammoinhat + String.valueOf(Page), new Response.Listener<String>() {
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
                    limitData = true;
                    //recyclerViewhomspmoinhat.removeView(footerview);
                    progressBarHome.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Hết",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }

    private void GetDataLoaisanpham() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdan, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int id = 0;
                    String tenloaisp = "";
                    String hinhanhloaisp = "";
                    for (int i = 0; i<response.length() ; i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaisp = jsonObject.getString("tenloaisanpham");
                            hinhanhloaisp = jsonObject.getString("hinhanhloaisanpham");
                            mangloaisanpham.add(new LoaiSanPham(id,tenloaisp,hinhanhloaisp));
                            loaisanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn.sforum.vn/sforum/wp-content/uploads/2020/08/OPPO-F17-1.jpg");
        mangquangcao.add("http://genk.mediacdn.vn/2019/6/22/promotiondoublestorage-1561186618199280355933.jpg");
        mangquangcao.add("https://cdn.tgdd.vn/Files/2021/03/09/1333890/lisa3_800x450.jpg");
        mangquangcao.add("https://i.ytimg.com/vi/v8tvXnQiFMo/maxresdefault.jpg");
        for (int i = 0; i<mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperHomeScreen.addView(imageView);
        }
        viewFlipperHomeScreen.setFlipInterval(5000);
        viewFlipperHomeScreen.setAutoStart(true);
    }

    private void    Initial() {
        viewFlipperHomeScreen = findViewById(R.id.viewflipperhomescreen);
        recyclerViewhomeloaisanpham = findViewById(R.id.recyclerviewhomeloaisanpham);
        recyclerViewhomspmoinhat = findViewById(R.id.recyclerviewhomespmoinhat);
        imageViewhomegiohang = findViewById(R.id.imageviewhomegiohang);
        linearLayouthomehome = findViewById(R.id.linearlayouthomehome);
        nestedScrollViewhome = findViewById(R.id.nestedScrollViewhome);
        progressBarHome =findViewById(R.id.progressbarhome);
        linearlayouttoihome =findViewById(R.id.linearlayouttoihome);


        mangloaisanpham = new ArrayList<>();
        loaisanphamAdapter = new LoaisanphamAdapter(getApplicationContext(),mangloaisanpham);
        recyclerViewhomeloaisanpham.setHasFixedSize(true);
        recyclerViewhomeloaisanpham.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,RecyclerView.HORIZONTAL,false));
        recyclerViewhomeloaisanpham.setAdapter(loaisanphamAdapter);

        mangsanphammoinhat = new ArrayList<>();
        sanphammoinhatAdapter = new SanphammoinhatAdapter(getApplicationContext(),mangsanphammoinhat);
        recyclerViewhomspmoinhat.setHasFixedSize(true);
        recyclerViewhomspmoinhat.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewhomspmoinhat.setNestedScrollingEnabled(false);
        recyclerViewhomspmoinhat.setAdapter(sanphammoinhatAdapter);

        footerview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.progressbar,null);

        mHandler = new MHandler();


        if (manggiohang == null){
            manggiohang = new ArrayList<>();
        }

    }

    public class MHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    //recyclerViewhomspmoinhat.addView(footerview);
                    progressBarHome.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    GetDataSanPhamMoiNhat(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}