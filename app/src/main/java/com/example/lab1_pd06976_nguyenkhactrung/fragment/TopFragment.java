package com.example.lab1_pd06976_nguyenkhactrung.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lab1_pd06976_nguyenkhactrung.R;
import com.example.lab1_pd06976_nguyenkhactrung.adapter.TopAdapter;
import com.example.lab1_pd06976_nguyenkhactrung.dao.PhieuMuonDAO;
import com.example.lab1_pd06976_nguyenkhactrung.model.Top;

import java.util.ArrayList;

public class TopFragment extends Fragment {
    View view;
    ListView listViewTop;
    ArrayList<Top> list;
    TopAdapter topAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top, container, false);
        listViewTop = view.findViewById(R.id.listViewTop);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
        list =(ArrayList<Top>) phieuMuonDAO.getTop();
        topAdapter = new TopAdapter(getActivity(), this, list);
        listViewTop.setAdapter(topAdapter);
        return view;
    }
}