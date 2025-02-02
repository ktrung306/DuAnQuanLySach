package com.example.lab1_pd06976_nguyenkhactrung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab1_pd06976_nguyenkhactrung.R;
import com.example.lab1_pd06976_nguyenkhactrung.dao.LoaiSachDAO;
import com.example.lab1_pd06976_nguyenkhactrung.fragment.SachFragment;
import com.example.lab1_pd06976_nguyenkhactrung.model.LoaiSach;
import com.example.lab1_pd06976_nguyenkhactrung.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    //Khai báo
    Context context;
    SachFragment fragment;
    List<Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;

    //Tạo hàm constructor
    public SachAdapter(@NonNull Context context, SachFragment fragment, List<Sach> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    //hàm getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        //dk convertView == null
        if (v == null) {
            LayoutInflater inflater =(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Gọi sach_item
            v = inflater.inflate(R.layout.sach_item, null);
        }
        //Khai báo sach item vị trí hiển thị
        final Sach item = list.get(position);
        //Đk != null
        if(item != null) {
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            //hiển thị dữ liệu lên từng item
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã Sách: "+item.getMaSach());
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: "+item.getTenSach());
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá Thuê: "+item.getGiaThue());
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại sách: "+loaiSach.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        //Sự kiện xoá
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gọi phương thức xoá
                fragment.xoa(String.valueOf(item.getMaSach()));
            }
        });
        return v;
    }
}
