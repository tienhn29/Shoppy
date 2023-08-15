package com.example.shoppy.model;

public class GioHang {
    private int Idsp;
    private String Tensp;
    private Integer Giasp;
    private String Hinhsp;
    private int Soluongsp;

    public GioHang(int idsp, String tensp, Integer giasp, String hinhsp, int soluongsp) {
        Idsp = idsp;
        Tensp = tensp;
        Giasp = giasp;
        Hinhsp = hinhsp;
        Soluongsp = soluongsp;
    }

    public int getIdsp() {
        return Idsp;
    }

    public void setIdsp(int idsp) {
        Idsp = idsp;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public Integer getGiasp() {
        return Giasp;
    }

    public void setGiasp(Integer giasp) {
        Giasp = giasp;
    }

    public String getHinhsp() {
        return Hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        Hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return Soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        Soluongsp = soluongsp;
    }
}
