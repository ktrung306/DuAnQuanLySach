package com.example.lab1_pd06976_nguyenkhactrung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab1_pd06976_nguyenkhactrung.dao.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    //Khai báo widget
    EditText edUserName, edPassword;
    Button btnLogin;
    CheckBox chkRememberPass;
    String strUser, strPass;
    ThuThuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        //Ánh xạ
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);

        chkRememberPass = findViewById(R.id.chkRememberPass);
        //Khởi tạo dối tượng
        dao = new ThuThuDAO(this);
        //Đọc user, pass trong SharedPreferences
        SharedPreferences p = getSharedPreferences("DU_LIEU", MODE_PRIVATE);
        String user =  p.getString("USERNAME", "");
        String pass =  p.getString("PASSWORD", "");
        Boolean rem  =  p.getBoolean("REMEMBER", false);

        //lưu user, pass cho lần đn sau
        edUserName.setText(user);
        edPassword.setText(pass);
        chkRememberPass.setChecked(rem);


        //Sự kiện đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }
    //Ghi nhớ user, pass trong SharedPreferences
    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("DU_LIEU", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(!status) {
            //xoá dữ liệu đã lưu trước đó
            editor.clear();
        } else {
            //Lưu dữ liệu
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER",status);
        }
        //Lưu lại toàn bộ
        editor.commit();
    }
    //check login
    public void checkLogin() {
        //Lấy dữ liệu trên ô edittext
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();

        if(strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Chưa nhập Đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            //Kiểm tra đk
            if(dao.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                //ghi nhớ user, pass nếu check
                rememberUser(strUser, strPass,chkRememberPass.isChecked());
                //Chạy qua activity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //Gửi user sang main activity
                i.putExtra("user", strUser);
                startActivity(i);
                //Không quay lại màn hình login
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
}