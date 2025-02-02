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
import com.example.lab1_pd06976_nguyenkhactrung.fragment.ThanhVienFragment;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    //Khai báo
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> lists;
    TextView tvMaTV, tvTenTV, tvNamSinh;
    ImageView imgDel;

    //Tạo hàm constructor
    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    //Tạo hàm getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Khai báo
        View v = convertView;
        //Đk == null
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Lấy item thanh_vien_item
            v = inflater.inflate(R.layout.thanh_vien_item,null);
        }
        //Khai báo thành viên
        final ThanhVien item = lists.get(position);
        //Đk
        if(item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTV);
            //hiển thị dữ liệu lên từng item
            tvMaTV.setText("Mã thành viên: "+item.getMaTV());
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên thành viên: "+item.getHoTen());
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: "+item.getNamSinh());

            imgDel = v.findViewById(R.id.imgDeleteLs);
        }
        //Xữ lý sự kiện xoá
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi phương thức xoá
                fragment.xoa(String.valueOf(item.getMaTV()));
            }
        });
        return v;

    }
}
