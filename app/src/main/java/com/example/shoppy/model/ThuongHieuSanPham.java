package com.example.shoppy.model;

public class ThuongHieuSanPham {
    private int Id;
    private String Tenthuonghieu;
    private String Hinhanhthuonghieu;
    private int Idloaisanpham;

    public ThuongHieuSanPham(int id, String tenthuonghieu, String hinhanhthuonghieu, int idloaisanpham) {
        Id = id;
        Tenthuonghieu = tenthuonghieu;
        Hinhanhthuonghieu = hinhanhthuonghieu;
        Idloaisanpham = idloaisanpham;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenthuonghieu() {
        return Tenthuonghieu;
    }

    public void setTenthuonghieu(String tenthuonghieu) {
        Tenthuonghieu = tenthuonghieu;
    }

    public String getHinhanhthuonghieu() {
        return Hinhanhthuonghieu;
    }

    public void setHinhanhthuonghieu(String hinhanhthuonghieu) {
        Hinhanhthuonghieu = hinhanhthuonghieu;
    }

    public int getIdloaisanpham() {
        return Idloaisanpham;
    }

    public void setIdloaisanpham(int idloaisanpham) {
        Idloaisanpham = idloaisanpham;
    }
}
