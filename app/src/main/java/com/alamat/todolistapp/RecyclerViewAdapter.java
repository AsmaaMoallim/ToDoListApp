package com.alamat.todolistapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.alamat.todolistapp.databinding.DisplayItemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    List<ToDoModel> toDoModels;

    public RecyclerViewAdapter(List<ToDoModel> toDoModel) {
        this.toDoModels = toDoModel;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DisplayItemBinding displayItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.display_item, parent,false);
        return new ViewHolder(displayItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoModel toDoModel = toDoModels.get(position);
        holder.displayItemBinding.tvTaskTitle.setText(toDoModel.getTodoTitle());
        holder.displayItemBinding.tvTaskContent.setText(toDoModel.getTodoContect());
    }

    @Override
    public int getItemCount() {
        if (toDoModels == null){
            return 0;
        }else {
            return toDoModels.size();
        }    }

    class ViewHolder extends RecyclerView.ViewHolder{

        DisplayItemBinding displayItemBinding;
        public ViewHolder(@NonNull DisplayItemBinding itemView) {
            super(itemView.getRoot());
            this.displayItemBinding = itemView;
        }
    }
}
