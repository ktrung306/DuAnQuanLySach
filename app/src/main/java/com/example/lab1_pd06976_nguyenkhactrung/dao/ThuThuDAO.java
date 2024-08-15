package com.example.lab1_pd06976_nguyenkhactrung.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab1_pd06976_nguyenkhactrung.database.DbHelper;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThanhVien;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    //Khai báo
    private SQLiteDatabase db;

    //Tạo hàm Constructor
    public ThuThuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //Thêm Thu thu
    public long insert(ThuThu obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.insert("ThuThu", null, values);
    }
    //Cập nhật Thu thu
    public int update(ThuThu obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("ThuThu", values, "maTT=?", new String[]{obj.getMaTT()});
    }

    //Xoá thu thu
    public int delete(String id) {
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }

    //hiển thị dữ liệu thu thu
    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String... selectionArgs) {
        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThuThu obj = new ThuThu();
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            Log.i("//=========", obj.toString());
            list.add(obj);
        }
        return list;
    }
    //get tat ca data
    public  List<ThuThu> getAll() {
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }
    //get data theo id
    public ThuThu getID(String id) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }
    //check login
    public  int checkLogin(String id, String password) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id, password);
        if(list.size() == 0)
            return -1; //K tìm thấy
        return 1; //Tìm thấy
    }
}
