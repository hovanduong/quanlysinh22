package com.example.quanlysinhvien2;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quanlysinhvien2.Adapter.AdapterDanhSachSinhVien;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<sinhvien> sinhviens;

    AdapterDanhSachSinhVien adapterDanhSachSinhVien;
    RecyclerView recyclerDanhsachsinhvien;
    // các biến để lấy ảnh
    int chonanh=1;
    Uri uri;
    ImageView imgeChonHinh;
    Bitmap bitmap;
    EditText edhoten, edngaysinh;

    EditText editTextDate;
    Button buttonDate,btnThem,btnChonanh;
    CheckBox checkBoxIsSpinnerMode;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextDate =  findViewById(R.id.editText_date);
        editTextDate = findViewById(R.id.editText_date);
        buttonDate =  findViewById(R.id.button_date);
        checkBoxIsSpinnerMode = findViewById(R.id.checkBox_isSpinnerMode);

        imgeChonHinh=findViewById(R.id.imgeChonHinh);
        btnChonanh=findViewById(R.id.btnChonAnh);
        btnChonanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,chonanh);
            }
        });
        this.buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectDate();
            }
        });
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        addControls();
    }

    private void addControls() {
        edhoten = findViewById(R.id.edhotensinhvien);
        edngaysinh = findViewById(R.id.editText_date);
        btnThem = findViewById(R.id.btnThem);

        recyclerDanhsachsinhvien = findViewById(R.id.recyclerDanhsachsinhvien);

        sinhviens = new ArrayList<>();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String hoten = edhoten.getText().toString();
                String ngaysinh = edngaysinh.getText().toString();

                if (hoten.equals("") || ngaysinh.equals("")) {
                    Toast.makeText(MainActivity.this, "Thông tin nhập chưa đầy đủ", Toast.LENGTH_LONG).show();
                } else {

                    sinhviens.add(new sinhvien(bitmap, hoten, ngaysinh));
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerDanhsachsinhvien.setLayoutManager(layoutManager);
                    adapterDanhSachSinhVien = new AdapterDanhSachSinhVien(MainActivity.this, R.layout.custom_layout_danhsachsinhvien, sinhviens);
                    recyclerDanhsachsinhvien.setAdapter(adapterDanhSachSinhVien);
                    adapterDanhSachSinhVien.notifyDataSetChanged();

                }
            }


        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==chonanh && resultCode == RESULT_OK && data !=null && data.getData() != null){
            uri=data.getData();

            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgeChonHinh.setImageBitmap(bitmap);



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    private void buttonSelectDate() {
        final boolean isSpinnerMode = this.checkBoxIsSpinnerMode.isChecked();

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                editTextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };
        DatePickerDialog datePickerDialog = null;

        if(isSpinnerMode)  {
            // Create DatePickerDialog:
            datePickerDialog = new DatePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }
        // Calendar Mode (Default):
        else {
            datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }

        // Show
        datePickerDialog.show();
    }
}
