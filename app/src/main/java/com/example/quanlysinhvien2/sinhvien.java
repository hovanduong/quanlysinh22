package com.example.quanlysinhvien2;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class sinhvien {
    private Bitmap hinhanh;
    private String ten;
    private String ngaysinh;

    public sinhvien(Bitmap hinhanh, String ten, String ngaysinh) {
        this.hinhanh = hinhanh;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
    }

    public Bitmap getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(Bitmap hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
