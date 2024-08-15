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

import java.util.ArrayList;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    //Khai báo
    private Context context;
    private ArrayList<Sach> lists;
    TextView tvMaSach, tvTenSach;

    //Hàm constructor
    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> lists) {
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
        //Đk
        if (v == null) {
            //Tạo inflater
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Gọi sach_item_spinner
            v = inflater.inflate(R.layout.sach_item_spinner, null);
        }
        //Khai báo sách vị trí
        final Sach item = lists.get(position);
        //Đk != null
        if(item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSachSp);
            tvMaSach.setText(item.getMaSach() + ". ");
            tvTenSach = v.findViewById(R.id.tvTenSachSp);
            tvTenSach.setText(item.getTenSach());
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
            v = inflater.inflate(R.layout.sach_item_spinner, null);
        }
        final Sach item = lists.get(position);
        if(item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSachSp);
            tvMaSach.setText(item.getMaSach() + ". ");
            tvTenSach = v.findViewById(R.id.tvTenSachSp);
            tvTenSach.setText(item.getTenSach());
        }
        return v;
    }
}
