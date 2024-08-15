package com.example.lab1_pd06976_nguyenkhactrung.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab1_pd06976_nguyenkhactrung.R;
import com.example.lab1_pd06976_nguyenkhactrung.dao.ThuThuDAO;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;

public class ChangePassFragment extends Fragment {
    //Khai báo widget
    TextInputEditText edPassOld, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
        //Ánh xạ
        edPassOld = v.findViewById(R.id.edPassOld);
        //Khởi tạo đối tượng
        dao = new ThuThuDAO(getActivity());
        edPass = v.findViewById(R.id.edPassChange);
        edRePass = v.findViewById(R.id.edRePassChange);
        btnSave = v.findViewById(R.id.btnSaveUserChange);
        btnCancel = v.findViewById(R.id.btnCancelUserChange);
        //Sử kiện huỷ
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xoá trắng thông tin
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        //Sự kiện lưu
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khai báo SharedPreferences lấy dữ liệu trong
                SharedPreferences pref = getActivity().getSharedPreferences("DU_LIEU", Context.MODE_PRIVATE);
                //truyền vào username
                String user = pref.getString("USERNAME", "");
                //KT đk > 0
                if(validate() > 0) {
                    //khai báo thuthu và setmatkhau
                    ThuThu thuThu = dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());
                    //kt đk > 0
                    if(dao.update(thuThu) > 0) {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        return v;
    }
    // validate form
    public int validate() {
        int check = 1;
        //Kt đk chưa nhập
        if(edPassOld.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn Phải nhập đầy đủ thông", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            // doc user, pass trong SharedPreferences
            SharedPreferences pref = getActivity().getSharedPreferences("DU_LIEU", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD", "");
            //Lấy dữ liệu của 2 ô
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            //Kt đk passOld khác edPassOld
            if(!passOld.equals(edPassOld.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            //kt đk không trùng nhau
            if(!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}