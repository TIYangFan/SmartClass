package com.example.smartclass.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartclass.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/17
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class UnfocusedDetailStudentRecyclerViewAdapter extends RecyclerView.Adapter<UnfocusedDetailStudentRecyclerViewAdapter.holder> {

    private String[][] datas;

    public static class holder extends RecyclerView.ViewHolder{

        @BindView(R.id.time1)
        TextView time1;
        @BindView(R.id.time2)
        TextView time2;
        @BindView(R.id.detail_name)
        TextView detail_name;
        @BindView(R.id.detail_class)
        TextView detail_class;
        @BindView(R.id.detail_id)
        TextView detail_id;

        public holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String data[]){
            time1.setText(data[0]);
            time2.setText(data[1]);
            detail_name.setText(data[2]);
            detail_class.setText(data[3]);
            detail_id.setText(data[4]);
        }
    }

    public UnfocusedDetailStudentRecyclerViewAdapter(String[][] data){
        datas = data;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_unfocused_student_details, viewGroup, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        holder.bind(datas[i]);
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

}
