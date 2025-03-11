package com.example.reading_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class subjectAdapter extends RecyclerView.Adapter<subjectAdapter.viewHolder> {

    Context context;
    ArrayList<subjectModel> list;
    OnItemClickListener listener;

    public subjectAdapter(ArrayList<subjectModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // Interface cho sự kiện click
    public interface OnItemClickListener {
        void onItemClick(subjectModel item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        subjectModel model = list.get(position);
        holder.subjectName.setText(model.getSubjectName());

        // Sử dụng Glide để tải ảnh từ đường dẫn
        Glide.with(context)
                .load(model.getPathImage()) // Đường dẫn ảnh
                .placeholder(R.drawable.place) // Ảnh mặc định khi đang tải
                .error(R.drawable.place) // Ảnh mặc định khi lỗi
                .into(holder.bookImage);

        // Xử lý sự kiện click vào item
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView subjectName;
        ImageView bookImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.txtBookName);
            bookImage = itemView.findViewById(R.id.imgBackgroudBook);
        }
    }

    public void updateList(ArrayList<subjectModel> newList) {
        list = newList;
        notifyDataSetChanged();
    }

}
