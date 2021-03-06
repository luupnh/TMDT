package com.example.doantmdt.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doantmdt.R;
import com.example.doantmdt.model.Giohang;
import com.example.doantmdt.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSPActitivity extends AppCompatActivity {
    Toolbar toolbarChiTiet;
    ImageView imgViewChiTiet;
    TextView txtTenChiTiet,txtGiaChiTiet,txtMoTaChiTiet,txtView7,txtView8,txtView9,txtView10,txtView11,txtView12,txtView13,txtView14;
    Spinner spinnerChiTiet;
    Button btnThemGioHang;
    int ID = 0;
    String TenSP = "";
    Integer GiaSP = 0;
    String HinhSP = "";
    String MoTaSP = "";
    int IDLoaiSP = 0;
    String ManHinhSP = "";
    String HDH = "";
    Integer BoNho = 0;
    String CamTrc = "";
    String CamSau = "";
    String CPU = "";
    Integer RAM = 0;
    Integer Pin = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_s_p_actitivity);
        AnhXa();
        ActionToolBar();
        GetInfo();
        CatchEventSpinner();
        EventButton();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void EventButton() {
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mangGioHang.size() > 0){
                    int sl1 = Integer.parseInt(spinnerChiTiet.getSelectedItem().toString());
                    boolean exists = false;
                    for(int i = 0;i<MainActivity.mangGioHang.size();i++){
                        if(MainActivity.mangGioHang.get(i).getId() == ID){
                            MainActivity.mangGioHang.get(i).setSl(MainActivity.mangGioHang.get(i).getSl() + sl1);
                            if(MainActivity.mangGioHang.get(i).getSl() >= 10){
                                MainActivity.mangGioHang.get(i).setSl(10);
                            }
                            MainActivity.mangGioHang.get(i).setGia(GiaSP * MainActivity.mangGioHang.get(i).getSl());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int sl = Integer.parseInt(spinnerChiTiet.getSelectedItem().toString());
                        long giaMoi = sl * GiaSP;
                        MainActivity.mangGioHang.add(new Giohang(ID,TenSP,giaMoi,HinhSP,sl));
                    }
                } else {
                    int sl = Integer.parseInt(spinnerChiTiet.getSelectedItem().toString());
                    long giaMoi = sl * GiaSP;
                    MainActivity.mangGioHang.add(new Giohang(ID,TenSP,giaMoi,HinhSP,sl));
                }
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,soluong);
        spinnerChiTiet.setAdapter(arrayAdapter);
    }

    private void GetInfo() {

        sanpham sp = (sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        ID = sp.getId();
        TenSP = sp.getTen();
        GiaSP = sp.getGia();
        HinhSP = sp.getHinh();
        MoTaSP = sp.getMota();
        IDLoaiSP = sp.getIdLoai();
        ManHinhSP = sp.getManhinh();
        HDH = sp.getHdh();
        BoNho = sp.getBonho();
        CamTrc = sp.getCamtrc();
        CamSau = sp.getCamsau();
        CPU = sp.getCpu();
        RAM = sp.getRam();
        Pin = sp.getPin();
        txtTenChiTiet.setText(TenSP);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaChiTiet.setText("Giá: "+decimalFormat.format(GiaSP)+ "Đ");
        txtMoTaChiTiet.setText(MoTaSP);
        //txtView7.setText(ManHinhSP);
        //txtView8.setText(HDH);
        //txtView9.setText(BoNho);
        //txtView10.setText(CamTrc);
        //txtView11.setText(CamSau);
        //txtView12.setText(CPU);
        //txtView13.setText(RAM);
        //txtView14.setText(Pin);


        Picasso.get().load(HinhSP)
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(imgViewChiTiet);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarChiTiet = findViewById(R.id.toolbarChiTietSP);
        imgViewChiTiet = findViewById(R.id.imgChiTietSP);
        txtTenChiTiet = findViewById(R.id.textViewTenChiTietSP);
        txtGiaChiTiet = findViewById(R.id.textViewGiaChiTietSP);
        txtMoTaChiTiet = findViewById(R.id.textMoTaChiTietSP);
        spinnerChiTiet = findViewById(R.id.spinerChiTietSP);
        btnThemGioHang = findViewById(R.id.btnThemGioHang);
        //txtView7 = findViewById(R.id.textView7);
        //txtView8 = findViewById(R.id.textView8);
        //txtView9 = findViewById(R.id.textView9);
        //txtView10 = findViewById(R.id.textView10);
        //txtView11 = findViewById(R.id.textView11);
        //txtView12 = findViewById(R.id.textView12);
        //txtView13 = findViewById(R.id.textView13);
        //txtView14 = findViewById(R.id.textView14);

    }
}