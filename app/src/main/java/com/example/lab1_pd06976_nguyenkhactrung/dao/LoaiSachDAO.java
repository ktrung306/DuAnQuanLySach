package com.example.lab1_pd06976_nguyenkhactrung.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab1_pd06976_nguyenkhactrung.database.DbHelper;
import com.example.lab1_pd06976_nguyenkhactrung.model.LoaiSach;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    //Khai báo
    private SQLiteDatabase db;

    //Tạo hàm Constructor
    public LoaiSachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //Thêm Loại Sách
    public long insert(LoaiSach obj) {
        ContentValues values = new ContentValues();
        // values.put("maLoai", obj.getMaLoai());
        values.put("tenLoai", obj.getTenLoai());
        return db.insert("LoaiSach", null, values);
    }

    //Cập nhật loại sách
    public int update(LoaiSach obj) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.update("LoaiSach", values, "maLoai=?", new String[]{String.valueOf(obj.getMaLoai())});
    }

    //Xoá loại sách
    public int delete(String id) {
        return db.delete("LoaiSach", "maLoai=?", new String[]{id});
    }

    //Hiển dữ liệu Loại sách
    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String... selectionArgs) {
        List<LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            LoaiSach obj = new LoaiSach();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

    //get tat ca dữ liệu
    public  List<LoaiSach> getAll() {
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }
    //get dữ liệu theo id
    public LoaiSach getID(String id) {
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql, id);
        return list.get(0);
    }

}
