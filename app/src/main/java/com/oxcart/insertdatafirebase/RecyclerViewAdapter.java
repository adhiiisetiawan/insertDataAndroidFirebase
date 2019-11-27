package com.oxcart.insertdatafirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<StudentDetails> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<StudentDetails> mainImageUploadInfoList) {
        this.context = context;
        MainImageUploadInfoList = mainImageUploadInfoList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        StudentDetails studentDetails = MainImageUploadInfoList.get(position);
        holder.StudentNameTextView.setText(studentDetails.getName());
        holder.StudentNumberTextView.setText(studentDetails.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView StudentNameTextView;
        public TextView StudentNumberTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            StudentNameTextView = itemView.findViewById(R.id.ShowStudentNameTextView);
            StudentNumberTextView = itemView.findViewById(R.id.ShowStudentNumberTextView);
        }
    }
}