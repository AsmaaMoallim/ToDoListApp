package com.alamat.todolistapp.activities_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.todolistapp.R;
import com.alamat.todolistapp.databinding.DisplayItemBinding;
import com.alamat.todolistapp.models.ToDoModel;
import com.alamat.todolistapp.roomDatabase.RoDatabase;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SwipeViewHolder> {


    List<ToDoModel> toDoModels;

    static int updateElemId = -1;

    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();



    // search recycler view
    public void filter(String text) {
        toDoModels.clear();
        toDoModels = RoDatabase.getInstance(this).todoDao().search(text);
        notifyDataSetChanged();
    }


    public RecyclerViewAdapter(List<ToDoModel> toDoModel) {
        this.toDoModels = toDoModel;
    }


    @NonNull
    @NotNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        DisplayItemBinding displayItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.display_item, parent, false);
        return new SwipeViewHolder(displayItemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewAdapter.SwipeViewHolder holder, int position) {
        ToDoModel toDoModel = toDoModels.get(position);

        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(holder.displayItemBinding.swipeLayout, String.valueOf(toDoModel.getId()));


        holder.displayItemBinding.tvTaskTitle.setText(toDoModel.getTodoTitle());
        holder.displayItemBinding.tvTaskContent.setText(toDoModel.getTodoContect());

//        holder.displayItemBinding.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("TAG", "onClick: " + position);
//
//            }
//        });


        //Events

        holder.displayItemBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: " + position);

                RoDatabase.getInstance(this).todoDao().deleteRecordWhere(toDoModel.getId());

                if (HomeFragment.AllTodo.size() != 0) {
                    HomeFragment.AllTodo.remove(position);
                    HomeFragment.recyclerViewAdapter.notifyDataSetChanged();

                } else if (TestFragment.AllTodoWhereCategory.size() != 0) {
                    TestFragment.AllTodoWhereCategory.remove(position);
                    TestFragment.recyclerViewAdapter.notifyDataSetChanged();
                }
            }


        });

        holder.displayItemBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: " + position);

                updateElemId = toDoModel.getId();
                Intent intent = new Intent(v.getContext(), InsertNewTodoActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("updateElemId", updateElemId);
                v.getContext().startActivity(intent);

//                Toast.makeText(v.getContext(), "Edit Cliked", Toast.LENGTH_SHORT).show();

            }
        });



        viewBinderHelper.closeLayout(String.valueOf(toDoModel.getId()));


    }

    @Override
    public int getItemCount() {
        if (toDoModels == null) {
            return 0;
        } else {
            return toDoModels.size();
        }
    }


    class SwipeViewHolder extends RecyclerView.ViewHolder {
        DisplayItemBinding displayItemBinding;

        public SwipeViewHolder(@NonNull DisplayItemBinding displayItemBinding) {
            super(displayItemBinding.getRoot());
            this.displayItemBinding = displayItemBinding;

        }

    }
}


