package com.example.shoppy.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int Id;
    private String Tensanpham;
    private Integer Giasanpham;
    private String Motasanpham;
    private String Hinhanhsanpham;
    private int IdLoaisanpham;

    public SanPham(int id, String tensanpham, Integer giasanpham, String motasanpham, String hinhanhsanpham, int idLoaisanpham) {
        Id = id;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Motasanpham = motasanpham;
        Hinhanhsanpham = hinhanhsanpham;
        IdLoaisanpham = idLoaisanpham;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public Integer getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        Giasanpham = giasanpham;
    }

    public String getMotasanpham() {
        return Motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        Motasanpham = motasanpham;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }

    public int getIdLoaisanpham() {
        return IdLoaisanpham;
    }

    public void setIdLoaisanpham(int idLoaisanpham) {
        IdLoaisanpham = idLoaisanpham;
    }
}
