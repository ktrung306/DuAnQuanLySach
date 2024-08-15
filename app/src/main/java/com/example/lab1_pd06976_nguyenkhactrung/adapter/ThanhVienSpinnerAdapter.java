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
import com.example.lab1_pd06976_nguyenkhactrung.model.Sach;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienSpinnerAdapter extends ArrayAdapter <ThanhVien>{
    //Khai báo
    private Context context;
    private ArrayList<ThanhVien> lists;
    TextView tvMaTV, tvTenTV;

    //Hàm constructor
    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    //Hàm getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Khai báo convertView
        View v = convertView;
        //Đk
        if (v == null) {
            //Tạo inflater
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Gọi thanh_vien_item_spinner
            v = inflater.inflate(R.layout.thanh_vien_item_spinner, null);
        }
        //Khai báo sách vị trí
        final ThanhVien item = lists.get(position);
        //Đk != null
        if(item != null) {
            //Hiển thị dữ liệu lên adapter
            tvMaTV = v.findViewById(R.id.tvMaTVSp);
            tvMaTV.setText(item.getMaTV() + ". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }
    //Hàm getDropDownView
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spinner, null);
        }
        final ThanhVien item = lists.get(position);
        if(item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTVSp);
            tvMaTV.setText(item.getMaTV() + ". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }
}
