package com.example.lab1_pd06976_nguyenkhactrung.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1_pd06976_nguyenkhactrung.R;
import com.example.lab1_pd06976_nguyenkhactrung.adapter.PhieuMuonAdapter;
import com.example.lab1_pd06976_nguyenkhactrung.adapter.SachSpinnerAdapter;
import com.example.lab1_pd06976_nguyenkhactrung.adapter.ThanhVienSpinnerAdapter;
import com.example.lab1_pd06976_nguyenkhactrung.dao.PhieuMuonDAO;
import com.example.lab1_pd06976_nguyenkhactrung.dao.SachDao;
import com.example.lab1_pd06976_nguyenkhactrung.dao.ThanhVienDAO;
import com.example.lab1_pd06976_nguyenkhactrung.model.PhieuMuon;
import com.example.lab1_pd06976_nguyenkhactrung.model.Sach;
import com.example.lab1_pd06976_nguyenkhactrung.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PhieuMuonFragment extends Fragment {
    //Khai báo
    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;

    //Khai báo thanhVienSpinnerAdapter
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;

    //Khai báo sachSpinnerAdapter
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDao sachDao;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;
    int maThanhVien;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        //ánh xạ
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fab);
        //Khởi tạo đối tượng
        dao = new PhieuMuonDAO(getActivity());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0); //show dialog lên
            }
        });

        //Nhấn giữ để cập nhật
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Lấy vị trí position
                item = list.get(position);
                openDialog(getActivity(), 1); //update
                return false;
            }
        });
        //gọi hàm cập nhật
        capNhatLv();
        return v;
    }
    //Hiển thị dữ liệu cập nhật lên ListView
    void capNhatLv(){
        //gán dữ liệu từ sql vào list
        list =(ArrayList<PhieuMuon>) dao.getAll();
        //Khai báo adapter
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        //hiển thi dữ liệu lên listview
        lvPhieuMuon.setAdapter(adapter);
    }

    //hiển thị Dialog thêm và cập nhật
    protected void openDialog(final Context context, final int type) {
        //custom dialog
         dialog = new Dialog(context);
        //gọi đến file phieu_muon_dialog
         dialog.setContentView(R.layout.phieu_muon_dialog);
        //Ánh xạ
         edMaPM = dialog.findViewById(R.id.edMaPM);
         spTV = dialog.findViewById(R.id.spMaTV);
         spSach = dialog.findViewById(R.id.spMaSach);
         tvNgay = dialog.findViewById(R.id.tvNgay);
         tvTienThue = dialog.findViewById(R.id.tvTienThue);
         chkTraSach = dialog.findViewById(R.id.chkTraSach);
         btnCancel = dialog.findViewById(R.id.btnCancelPM);
         btnSave = dialog.findViewById(R.id.btnSavePM);
         edMaPM.setEnabled(false); //MaPM tự động tăng
         tvNgay.setText("Ngày thuê: "+simpleDateFormat.format(new Date()));

        //Đổ dữ liệu thành viên
         thanhVienDAO = new ThanhVienDAO(context);
         listThanhVien = new ArrayList<ThanhVien>();
         //Đổ dữ liệu vào list thành viên
         listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
         //gọi thanhVienSpinnerAdapter
         thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
         //Đổ dữ liệu lên vào trong spinner
         spTV.setAdapter(thanhVienSpinnerAdapter);

         //Chọn tên thành viên nào trong spinner
         spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 maThanhVien = listThanhVien.get(position).getMaTV();
              //   Toast.makeText(context, "chọn " +listThanhVien.get(position).getHoTen(), Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });

         //Đổ dữ liệu sách
         sachDao = new SachDao(context);
         listSach = new ArrayList<Sach>();
         //Đỗ sách vào trong list
         listSach = (ArrayList<Sach>) sachDao.getAll();
         //đỗ vào spinner sách
         sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        //Đổ dữ liệu lên vào trong spinner
         spSach.setAdapter(sachSpinnerAdapter);

        //Chọn sách nào trong spinner
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                //set tiền thuê lên dialog
                tvTienThue.setText("Tiền thuê: "+tienThue);
          //      Toast.makeText(context, "Chon "+listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Cập nhật lại spinner
        if(type != 0) {
            //Lấy mã phiếu mượn trên item
            edMaPM.setText(String.valueOf(item.getMaPM()));
            //Lấy mã thành viên
            for (int i = 0; i < listThanhVien.size(); i++)
                if(item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                    positionTV = i;
                }
            //set mã thành viên vào trong spinner
            spTV.setSelection(positionTV);
            //Lấy mã sách
            for (int i = 0; i < listSach.size(); i++)
                if(item.getMaSach() == (listSach.get(i).getMaSach())){
                    positionSach = i;
            }
            spSach.setSelection(positionSach);
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            if(item.getTraSach() == 1){
                chkTraSach.setChecked(true); //Đã trả
            }else {
                chkTraSach.setChecked(false); //Chưa trả
            }
        }
        //Sự kiện huỷ
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //tắt dialog
            }
        });

        //Sự kiện lưu
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khai báo phiếu mượn
                item = new PhieuMuon();
                //Lấy thông tin của phiếu mượn
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                //Kiểm tra ngày trả
                if(chkTraSach.isChecked()) {
                    item.setTraSach(1); // true trả r
                }else {
                    item.setTraSach(0); //chưa trả
                }

                if(type == 0) {
                    //type = 0 (insert)
                    if(dao.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //type = 1 (update)
                    //set item mã phiếu mượn
                    item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                    if(dao.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv(); // cập nhật lại
                dialog.dismiss(); //Tắt dialog
            }
        });
         dialog.show();
    }

    //Hàm xoá
    public void xoa(final  String Id) {
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
                    public void onClick(DialogInterface dialog, int which) {
                        //Gọi function Delete
                        dao.delete(Id);
                        //cap nhật listview
                        capNhatLv();
                        dialog.cancel(); //Tắt dialog
                    }
                });
        //Chọn no
        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); //Tắt dialog
                    }
                });

        AlertDialog alertDialog = builder.create();
        builder.show();
    }

}