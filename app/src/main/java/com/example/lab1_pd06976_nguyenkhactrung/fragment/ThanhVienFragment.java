package com.example.lab1_pd06976_nguyenkhactrung.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab1_pd06976_nguyenkhactrung.R;
import com.example.lab1_pd06976_nguyenkhactrung.adapter.ThanhVienAdapter;
import com.example.lab1_pd06976_nguyenkhactrung.dao.ThanhVienDAO;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ThanhVienFragment extends Fragment {
    //Khai báo
    ListView lvThanhVien;
    ArrayList<ThanhVien> list;
    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        //ánh xạ
        lvThanhVien = v.findViewById(R.id.lvThanhVien);
        fab = v.findViewById(R.id.fab);
        //Khởi tạo đối tượng
        dao = new ThanhVienDAO(getActivity());
        //gọi hàm cập nhật
        capNhapLv();

        //Thêm thành viên
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0); //show dialog lên
            }
        });

        //Nhấn giữ item để cập nhật
        lvThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               item = list.get(position);
               openDialog(getActivity(), 1); //1 update theo item truyền vào
                return false;
            }
        });
        return v;
    }
    //Hiển thị dữ liệu lên ListView
    void capNhapLv() {
        //gán dữ liệu từ sql vào list
        list = (ArrayList<ThanhVien>) dao.getAll();
        //Khai báo adapter
        adapter = new ThanhVienAdapter(getActivity(), this, list);
        //hiển thi dữ liệu lên ListView
        lvThanhVien.setAdapter(adapter);
    }
    //hàm xoá
    public void xoa(final String Id) {
        //Sử dụng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xoá không?");
        builder.setCancelable(true);
        //Chọn yes
        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Gọi function Delete
                        dao.delete(Id);
                        //Cập nhật listview
                        capNhapLv();
                        dialog.cancel();
                    }
                });
        //Chọn no
        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel(); //Tắt dialog
                    }
                });
        AlertDialog alert = builder.create();
        builder.show(); //Hiển thị builder lên
    }

    //hiển thị Dialog thêm và cập nhật
    protected void openDialog(final Context context, final int type) {
        //custom dialog
        //Khởi tạo dialog
        dialog = new Dialog(context);
        //gọi đến file thanh_vien_diglog
        dialog.setContentView(R.layout.thanh_vien_diglog);
        //Ánh xạ
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        //Kiểm tra type insert 0 hay Update 1
        edMaTV.setEnabled(false); //MaTV tự động tăng
        //đk update
        if(type != 0) {
            //Lấy dữ liệu item hiển thị lên edittext
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
        }

        //Sự kiện thoát
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //Sự kiện lưu
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new ThanhVien();
                //set dữ liệu vào
                item.setHoTen(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                //Đk > 0
                if(validate()>0) {
                    //xét đk kiểu == 0
                    if (type==0) {
                        //type = 0 (insert)
                    if (dao.insert(item)>0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    }else {
                        //type = 1 (update)
                        item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if (dao.update(item)>0) {
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhapLv(); //gọi lại hàm
                    dialog.dismiss(); //Tắt dialog
                }
            }
        });
        dialog.show();
    }

    //validate form cập nhật
    public int validate() {
        int check = 1;
        if(edTenTV.getText().length() == 0 || edNamSinh.getText().length() == 0) {
            Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}