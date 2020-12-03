package com.example.quanlysinhvien2.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysinhvien2.R;
import com.example.quanlysinhvien2.sinhvien;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterDanhSachSinhVien extends RecyclerView.Adapter<AdapterDanhSachSinhVien.ViewHolder> {
    Context context;
    int layout;
    ArrayList<sinhvien> sinhviensList;
    public AdapterDanhSachSinhVien( Context context, int layout, ArrayList<sinhvien> sinhviensList){
        this.context=context;
        this.layout=layout;
        this.sinhviensList=sinhviensList;

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgeChonHinh1;
        TextView txtHoTenSinhVien,txtNgaySinh,txtHinhAnh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHoTenSinhVien=itemView.findViewById(R.id.txtHoTenSinhVien);
            txtNgaySinh=itemView.findViewById(R.id.txtNgaySinh);
            imgeChonHinh1=itemView.findViewById(R.id.imgeChonHinh1);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sinhvien sinhvienmode=sinhviensList.get(position);

        holder.imgeChonHinh1.setImageBitmap(sinhvienmode.getHinhanh());

       holder.txtHoTenSinhVien.setText("Tên SV: "+sinhvienmode.getTen());
        holder.txtNgaySinh.setText("Ngày Sinh: "+sinhvienmode.getNgaysinh());

    }

    @Override
    public int getItemCount() {
        return sinhviensList.size();
    }


}
