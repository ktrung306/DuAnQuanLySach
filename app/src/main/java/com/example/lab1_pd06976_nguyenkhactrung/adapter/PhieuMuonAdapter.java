package com.example.lab1_pd06976_nguyenkhactrung.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab1_pd06976_nguyenkhactrung.R;
import com.example.lab1_pd06976_nguyenkhactrung.dao.SachDao;
import com.example.lab1_pd06976_nguyenkhactrung.dao.ThanhVienDAO;
import com.example.lab1_pd06976_nguyenkhactrung.fragment.PhieuMuonFragment;
import com.example.lab1_pd06976_nguyenkhactrung.model.PhieuMuon;
import com.example.lab1_pd06976_nguyenkhactrung.model.Sach;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    //Khai báo
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgDel;
    SachDao sachDao;
    ThanhVienDAO thanhVienDAO;
    //Định dạng ngày tháng năm
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //Tạo constructor
    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
        super(context, 0, lists);
        this.context =context;
        this.lists = lists;
        this.fragment = fragment;
    }

    //Hàm getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Khai báo
        View v = convertView;
        //Đk
        if(v == null) {
            //Tạo inflater
            LayoutInflater inflater = (LayoutInflater)
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Gọi phieu_muon_item
            v = inflater.inflate(R.layout.phieu_muon_item, null);
        }
        //Khai báo PhieuMuon vị trí
        final PhieuMuon item = lists.get(position);
        //Đk != null
        if(item != null) {
            //Gọi phiếu mượn
            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã Phiếu: "+item.getMaPM());

            sachDao = new SachDao(context);
            //Tạo dao theo ID
            Sach sach = sachDao.getID(String.valueOf(item.getMaSach()));
            //Gán tên sách
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.getTenSach());
            thanhVienDAO = new ThanhVienDAO(context);
            //Tham chiếu qua thanhVienDAO
            //Lấy dữ liệu hiển thị lên item
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaTV()));
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành viên: "+thanhVien.getHoTen());
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            tvNgay = v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            tvTraSach = v.findViewById(R.id.tvTraSach);
            //đk Đã trả
            if(item.getTraSach() == 1) {
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else{
                //đk Chưa trả
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgDel = v.findViewById(R.id.imgDel);
        }
        //Sự kiện xoá
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi phương thức xoá
                fragment.xoa(String.valueOf(item.getMaPM()));
            }
        });
        return v;
    }
}
