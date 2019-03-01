package com.example.smartclass.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartclass.R;
import com.example.smartclass.bean.StudentStatusDetailsBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/17
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class UnfocusedStudentDetailsRecyclerViewAdapter extends RecyclerView.Adapter<UnfocusedStudentDetailsRecyclerViewAdapter.holder> {

    private ArrayList<StudentStatusDetailsBean> listData;

    public static class holder extends RecyclerView.ViewHolder{

        @BindView(R.id.unfocusedTime)
        TextView unfocusedTime;
        @BindView(R.id.totalTime)
        TextView totalTime;
        @BindView(R.id.studentName)
        TextView studentName;
        @BindView(R.id.studentClass)
        TextView studentClass;
        @BindView(R.id.studentId)
        TextView studentId;

        private holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(StudentStatusDetailsBean itemData){

            setText(itemData);
        }

        private void setText(StudentStatusDetailsBean itemData){

            unfocusedTime.setText(itemData.getDuring());
            totalTime.setText(itemData.getLesson_duration());
            studentName.setText(itemData.getName());
            studentClass.setText(itemData.getClass_no());
            studentId.setText(itemData.getStu_id());
        }
    }

    public UnfocusedStudentDetailsRecyclerViewAdapter(ArrayList<StudentStatusDetailsBean> listData){
        this.listData = listData;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_unfocused_student_details, viewGroup, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        holder.bind(listData.get(i));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setListData(ArrayList<StudentStatusDetailsBean> listData){
        this.listData = listData;
    }
}
