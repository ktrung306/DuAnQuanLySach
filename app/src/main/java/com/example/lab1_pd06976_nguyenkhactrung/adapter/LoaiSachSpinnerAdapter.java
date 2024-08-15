package com.example.lab1_pd06976_nguyenkhactrung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab1_pd06976_nguyenkhactrung.R;
import com.example.lab1_pd06976_nguyenkhactrung.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach>  {
    //Khai báo
    Context context;
    ArrayList<LoaiSach> lists;
    TextView tvMaLoaiSach, tvTenLoaiSach;

    //Tạo hàm constructor
    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    //Hàm getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Khai báo
        View v = convertView;
        //Đk == null
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Lấy loai_sach_item_spinner
            v = inflater.inflate(R.layout.loai_sach_item_spinner, null);
        }
        //Khai báo LoaiSach vị trí
        final  LoaiSach item = lists.get(position);
        //Đk
        if(item != null) {
            //Gán mals, tênloaisach
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSach.setText(item.getMaLoai() +". ");

            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }
    //hàm getDropDownView
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Khai báo
        View v = convertView;
        //Đk
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Lấy loai_sach_item_spinner
            v = inflater.inflate(R.layout.loai_sach_item_spinner, null);
        }
        //Khai báo LoaiSach
        final LoaiSach item = lists.get(position);
        //Đk
        if(item != null) {
            //Gán mals, tênloaisach
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSach.setText(item.getMaLoai()+". ");

            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }
}
