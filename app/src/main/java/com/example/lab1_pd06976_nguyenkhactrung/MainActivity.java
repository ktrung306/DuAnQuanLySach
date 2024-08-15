package com.example.lab1_pd06976_nguyenkhactrung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1_pd06976_nguyenkhactrung.dao.PhieuMuonDAO;
import com.example.lab1_pd06976_nguyenkhactrung.dao.ThuThuDAO;
import com.example.lab1_pd06976_nguyenkhactrung.fragment.ChangePassFragment;
import com.example.lab1_pd06976_nguyenkhactrung.fragment.DoanhThuFragment;
import com.example.lab1_pd06976_nguyenkhactrung.fragment.PhieuMuonFragment;
import com.example.lab1_pd06976_nguyenkhactrung.fragment.SachFragment;
import com.example.lab1_pd06976_nguyenkhactrung.fragment.ThanhVienFragment;
import com.example.lab1_pd06976_nguyenkhactrung.fragment.TopFragment;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    //Khai báo
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;

    PhieuMuonDAO dao;
    ThuThuDAO thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        //set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Quản lý Phiếu mượn");

        //Set FragmentManager hiển thị lên đầu tiên
        FragmentManager manager =getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, phieuMuonFragment)
                .commit();

        //Ánh xạ
        NavigationView nv = findViewById(R.id.nvView);
        //Show user in header
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();

        //Lấy tên hiển thị lên header
        String user = i.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.getHoTen();
        edUser.setText("" +username);

        //Nhấn vào NavigationView
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager =getSupportFragmentManager();
                //Lấy ra id của từng item
                if(item.getItemId() == R.id.nav_PhieuMuon) {
                    PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, phieuMuonFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_LoaiSach ) {
                    Toast.makeText(getApplicationContext(), "Quản lý loại sách", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_Sach ) {
                    SachFragment sachFragment = new SachFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, sachFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_ThanhVien) {
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, thanhVienFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_top ) {
                } else if (item.getItemId() == R.id.sub_DoanhThu ) {
                    DoanhThuFragment doanhThuFragment= new DoanhThuFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, doanhThuFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_AddUser ) {
                    Toast.makeText(getApplicationContext(), "Thêm người dùng", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.sub_Pass) {
                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, changePassFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_logout) {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }
                // Lấy title của menu khi nhấn vô từng item để hiển thị lên toolbar
                getSupportActionBar().setTitle(item.getTitle());
                drawer.closeDrawers();
                return false;
            }
        });
    }

    //Xử lý sự kiện cho nút icon menu trên toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}