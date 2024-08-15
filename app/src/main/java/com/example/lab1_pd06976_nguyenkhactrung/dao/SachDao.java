package com.example.lab1_pd06976_nguyenkhactrung.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab1_pd06976_nguyenkhactrung.database.DbHelper;
import com.example.lab1_pd06976_nguyenkhactrung.model.LoaiSach;
import com.example.lab1_pd06976_nguyenkhactrung.model.Sach;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class SachDao {
    //Khai báo
    private SQLiteDatabase db;

    //Tạo hàm Constructor
    public SachDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //Thêm Sách
    public long insert(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.insert("Sach", null, values);
    }

    //Cập nhật Sách
    public int update(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(obj.getMaSach())});
    }
    //Xoá Sách
    public int delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    //Hiển dữ liệu sách
    @SuppressLint("Range")
    public List<Sach> getData(String sql, String... selectionArgs) {
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            obj.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            obj.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(obj);
        }
        return list;
    }

    //get tat ca dữ liệu
    public  List<Sach> getAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }
    //get dữ dữ theo id
    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }
}
