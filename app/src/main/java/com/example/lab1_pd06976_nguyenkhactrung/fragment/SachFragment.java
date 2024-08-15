package com.example.lab1_pd06976_nguyenkhactrung.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab1_pd06976_nguyenkhactrung.R;
import com.example.lab1_pd06976_nguyenkhactrung.adapter.LoaiSachSpinnerAdapter;
import com.example.lab1_pd06976_nguyenkhactrung.adapter.SachAdapter;
import com.example.lab1_pd06976_nguyenkhactrung.dao.LoaiSachDAO;
import com.example.lab1_pd06976_nguyenkhactrung.dao.SachDao;
import com.example.lab1_pd06976_nguyenkhactrung.model.LoaiSach;
import com.example.lab1_pd06976_nguyenkhactrung.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SachFragment extends Fragment {
    //Khai báo
    ListView lvSach;
    SachDao sachDao;

    //Khai báo thêm và cập nhật
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spinner;
    Button btnSave, btnCancel;
    //
    SachAdapter adapter;
    Sach item;
    List<Sach> list;

    //Khai báo loại sách spinnerAdapter
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sach, container, false);
        //Ánh xạ
        lvSach = v.findViewById(R.id.lvSach);
        //new Dao
        sachDao = new SachDao(getActivity());
        //gọi hàm cập nhật
        capNhatLv();

        //Thêm thành viên
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0); //show dialog lên
            }
        });
        //Nhấn giữ item để cập nhật
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1); // update theo item truyền vào
                return false;
            }
        });
        return v;
    }
    //Hàm câp nhật đỗ dữ liệu lên ListView
    void capNhatLv() {
        //gán dữ liệu từ sql vào list
        list = (List<Sach>) sachDao.getAll();
        //Khai báo adapter
        adapter = new SachAdapter(getActivity(),this,list);
        //hiển thi dữ liệu lên ListView
        lvSach.setAdapter(adapter);
    }
    //hàm xoá
    public void xoa(final String Id) {
        //Sử dụng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Ban có muốn xoá không?");
        builder.setCancelable(true);
        //Chọn yes
        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Gọi vi trí iD Delete
                        sachDao.delete(Id);
                        //Cập nhật listview
                        capNhatLv();
                        dialog.cancel(); //Tắt hộp thoại
                        }
                    });
        //Chọn no
        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        builder.show(); //hiển thị lên
    }
    //hiển thị Dialog cập nhật, thêm
    protected void openDialog(final Context context, final int type) {
        //custom dialog
        dialog = new Dialog(context);
        //gọi đến file sach_diglog
        dialog.setContentView(R.layout.sach_dialog_xml);
        //ánh xạ các thành phần
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);
        //Khai báo các list
        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        //Gọi spinnerAdapter
        spinnerAdapter = new LoaiSachSpinnerAdapter(context, listLoaiSach);
        //Hiển thị dữ liệu lên listView
        spinner.setAdapter(spinnerAdapter);

        //Bấm vào spinner Lấy maLoaiSach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Lấy mã loại sách
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Kiểm tra type insert 0 hay Update 1
        edMaSach.setEnabled(false); //MaSach tự động tăng
        //Cập nhật
        if(type != 0) {
            //lấy dữ liệu từ item lên dialog
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            //vòng lâp for
            for (int i = 0; i < listLoaiSach.size(); i++)
            if(item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())){
                position = i;
            }
            //log ra vị trí số bao nhiêu
            Log.i("demo", "posSach"+position);
            spinner.setSelection(position);
        }
        //Sự kiện huỷ
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
                //gán item cho sách
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                item.setMaLoai(maLoaiSach);
                //KT đk > 0
                if(validate()>0) {
                    if(type == 0) {
                        //type = 0(insert)
                        if(sachDao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        //type = 1(update)
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if (sachDao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv(); //Cập nhật lại
                    dialog.dismiss(); //Tắt dialog
                }
            }
        });
        dialog.show(); //hiển thị
    }
    //validate form khi lưu
    public int validate() {
        int check = 1;
        if(edTenSach.getText().length() == 0 || edGiaThue.getText().length() == 0) {
            Toast.makeText(getContext(), "Chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    }