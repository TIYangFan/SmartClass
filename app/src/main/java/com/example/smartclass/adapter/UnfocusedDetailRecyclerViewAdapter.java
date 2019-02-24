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
public class UnfocusedDetailRecyclerViewAdapter extends RecyclerView.Adapter<UnfocusedDetailRecyclerViewAdapter.holder> {

    private String[][] datas;

    public static class holder extends RecyclerView.ViewHolder{

        @BindView(R.id.className)
        TextView className;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.studentId)
        TextView studentId;

        public holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String[] data){
            className.setText(data[0]);
            name.setText(data[1]);
            studentId.setText(data[2]);
        }
    }

    public UnfocusedDetailRecyclerViewAdapter(String[][] data){
        datas = data;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_unfocused_detail, viewGroup, false);
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
