package com.example.lab1_pd06976_nguyenkhactrung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab1_pd06976_nguyenkhactrung.R;
import com.example.lab1_pd06976_nguyenkhactrung.fragment.TopFragment;
import com.example.lab1_pd06976_nguyenkhactrung.model.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {

    //Khai báo
    private Context context;
    TopFragment topFragment;
    ArrayList<Top> lists;
    TextView tvSach, tvSoLuong;
    ImageView imgDel;

    //Hàm constructor
    public TopAdapter(@NonNull Context context, TopFragment topFragment, ArrayList<Top> lists) {
        super(context, 0, lists);
        this.context = context;
        this.topFragment = topFragment;
        this.lists = lists;
    }

    //Hàm getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Khai báo
        View v =convertView;
        //Kt đk
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Gọi top_item
            v = inflater.inflate(R.layout.top_item, null);
        }
        //Khai Báo Top item
        final Top item = lists.get(position);
        if(item != null) {
            //Gán Sách, Số lượng cho item
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: "+item.getTenSach());

            tvSoLuong = v.findViewById(R.id.tvSL);
            tvSoLuong.setText("Số lượng: "+item.getSoLuong());
        }
        return v;
    }
}
