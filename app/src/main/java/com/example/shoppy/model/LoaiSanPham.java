package com.example.shoppy.model;

public class LoaiSanPham {
    private int Id;
    private String Tenloaisanpham;
    private String Hinhanhloaisanpham;

    public LoaiSanPham(int id, String tenloaisanpham, String hinhanhloaisanpham) {
        Id = id;
        Tenloaisanpham = tenloaisanpham;
        Hinhanhloaisanpham = hinhanhloaisanpham;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenloaisanpham() {
        return Tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        Tenloaisanpham = tenloaisanpham;
    }

    public String getHinhanhloaisanpham() {
        return Hinhanhloaisanpham;
    }

    public void setHinhanhloaisanpham(String hinhanhloaisanpham) {
        Hinhanhloaisanpham = hinhanhloaisanpham;
    }
}
