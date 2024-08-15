package com.example.lab1_pd06976_nguyenkhactrung.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab1_pd06976_nguyenkhactrung.database.DbHelper;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThanhVien;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class DemoDB {
    private SQLiteDatabase db;
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;
    static final String TAG = "//==========";
    public DemoDB(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        thanhVienDAO = new ThanhVienDAO(context);
        thuThuDAO = new ThuThuDAO(context);
    }
    public void thanhVien() {
//         List<ThanhVien> ls = new ArrayList<>();
        ThanhVien tv = new ThanhVien(1,"Khac Trung", "2002");
//        if(thanhVienDAO.insert(tv) > 0) {
//            Log.i(TAG,"Them thanh vien thanh cong");
//        }else {
//            Log.i(TAG,"Them thanh vien that bai");
//        }
        Log.i(TAG,"===============================");
        Log.i(TAG, "Tong so thanh vien: "+thanhVienDAO.getAll().size());

        Log.i(TAG,"===============sau khi sua====================");
        tv = new ThanhVien(1,"Khac Trung 22", "2002");
        thanhVienDAO.update(tv);
        Log.i(TAG, "Tong so thanh vien: "+thanhVienDAO.getAll().size());
        thanhVienDAO.delete("1");
        Log.i(TAG,"===============sau khi xoa================");
        Log.i(TAG, "Tong so thanh vien: "+thanhVienDAO.getAll().size());
    }

    public  void thuThu() {
        ThuThu tt = new ThuThu("Trung", "Nguyen Trung", "Trung123");
        thuThuDAO.insert(tt);
        Log.i(TAG,"===============================");
        Log.i(TAG, "Tong so thu thu: "+thuThuDAO.getAll().size());
        tt = new ThuThu("Trung", "Tran Trung", "12345");
        thuThuDAO.update(tt);
        Log.i(TAG,"===============================");
        Log.i(TAG, "Tong so thu thu: "+thuThuDAO.getAll().size());
        if(thuThuDAO.checkLogin("Trung", "12345") > 0) {
            Log.i(TAG, "Dang nhap thanh cong");
        }else  {
            Log.i(TAG, "Dang nhap that bai");
        }
    }
}
