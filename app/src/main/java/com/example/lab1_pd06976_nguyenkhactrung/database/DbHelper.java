package com.example.lab1_pd06976_nguyenkhactrung.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    // Biến final là hằng số
    private static final String DB_NAME = "DuAn";
    private static final int DB_VERSION = 1;

    //Khai báo các Bảng
    static final String CREATE_TABLE_THU_THU =
            "create table ThuThu (maTT TEXT PRIMARY KEY," +
            "hoTen  TEXT NOT NULL, " +
            "matKhau TEXT NOT NULL)";
    //
    static final String CREATE_TABLE_THANH_VIEN =
            "create table ThanhVien (" +
                    "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "hoTen TEXT NOT NULL, " +
                    "namSinh TEXT NOT NULL)";
    //
    static final String CREATE_TABLE_LOAI_SACH =
            "create table LoaiSach (" +
                    "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenLoai TEXT NOT NULL)";
    //
    static final String CREATE_TABLE_SACH =
            "create table Sach (" +
                    "maSach  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenSach TEXT NOT NULL, " +
                    "giaThue INTEGER NOT NULL, " +
                    "maLoai  INTEGER REFERENCES LoaiSach (maLoai))";
    //
    static final String CREATE_TABLE_PHIEU_MUON =
            "create table PhieuMuon (" +
                    "maPM  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "maTT TEXT REFERENCES ThuThu (maTT), " +
                    "maTV INTEGER REFERENCES ThanhVien (maTV), "+
                    "maSach INTEGER REFERENCES Sach (maSach), "+
                    "tienThue INTEGER NOT NULL, " +
                    "ngay DATE NOT NULL," +
                    "traSach INTEGER NOT NULL )";
    //Tạo hàm Constructor
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Tạo các table trong database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang Thu Thu
        db.execSQL(CREATE_TABLE_THU_THU);
        //Tao bang Thanh vien
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        //Tao bang Loai Sach
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        //Tạo bang Sach
        db.execSQL(CREATE_TABLE_SACH);
        //Tao bang Loai Phieu Muon
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        //insert dữ liệu mẫu
        db.execSQL(Data_SQLite.INSERT_THU_THU);
        db.execSQL(Data_SQLite.INSERT_THANH_VIEN);
        db.execSQL(Data_SQLite.INSERT_LOAI_SACH);
        db.execSQL(Data_SQLite.INSERT_SACH);
        db.execSQL(Data_SQLite.INSERT_PHIEU_MUON);
    }

    //gọi hàm onUpgrade khi cập nhật dữ liệu bảng
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableLoaiThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableLoaiThuThu);
        String dropTableLoaiThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableLoaiThanhVien);
        String dropTableLoaiLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTableLoaiPhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTableLoaiPhieuMuon);
        //tạo lại dữ liệu database
        onCreate(db);
    }
}
