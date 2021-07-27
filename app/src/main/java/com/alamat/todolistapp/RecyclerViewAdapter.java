package com.alamat.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.todolistapp.databinding.DisplayItemBinding;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SwipeViewHolder> {


    List<ToDoModel> toDoModels;
    List<ToDoModel> toDoModelCopy = new ArrayList<>();

    static int updateElemId = -1;
    int pos;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    OnItemClickListener onItemClickListener;

    int itemView;


    public void filter(String text) {
        if (toDoModels != null) {
            toDoModelCopy.clear();
            toDoModelCopy.addAll(toDoModels);

//        toDoModelCopy.removeAll(toDoModels);
//        Log.e("Tag", " 11");
//        toDoModelCopy.addAll(toDoModels);
//        Log.e("Tag", "22 toDoModels " + toDoModels.get(0).todoTitle);
//        Log.e("Tag", "22 toDoModelCopy " + toDoModelCopy.get(0).todoTitle);


//        for(ToDoModel item: toDoModels){
//            toDoModelCopy.add(item);
//            Log.e("Tag", item.todoTitle);
//        }

            //        RecyclerViewAdapter.toDoModelCopy.addAll(toDoModels);

            toDoModels.clear();
            if (text.isEmpty()) {
//            toDoModels.removeAll(toDoModels);
//            for (ToDoModel item : toDoModelCopy) {
//                toDoModels.add(item);
//            }
                toDoModels.addAll(toDoModelCopy);
//            Log.e("Tag", "44 toDoModels " + toDoModels.get(0).todoTitle);
//            Log.e("Tag", "44 toDoModelCopy " + toDoModelCopy.get(0).todoTitle);

            } else {
                text = text.toLowerCase();
                for (ToDoModel item : toDoModelCopy) {
//                Log.e("Tag", "else " + toDoModelCopy.get(0).todoTitle);

                    if (item.todoTitle.toLowerCase().contains(text) || item.todoContect.toLowerCase().contains(text)) {
                        toDoModels.add(item);
                    }
                }
            }
//        toDoModelCopy.clear();
            notifyDataSetChanged();
        }

    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public RecyclerViewAdapter(List<ToDoModel> toDoModel) {
        this.toDoModels = toDoModel;
        this.itemView = itemView;

//        this.toDoModelCopy = toDoModel;
    }


//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        DisplayItemBinding displayItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.display_item, parent,false);
//        return new ViewHolder(displayItemBinding);
//    }

    @NonNull
    @NotNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        DisplayItemBinding displayItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.display_item, parent, false);
        return new SwipeViewHolder(displayItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewAdapter.SwipeViewHolder holder, int position) {
        ToDoModel toDoModel = toDoModels.get(position);
//

        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(holder.displayItemBinding.swipeLayout, String.valueOf(toDoModel.getId()));
//        holder.displayItemBinding.swipeLayout.open(true);
//        DisplayItemBinding displayItemBinding;
//
//        displayItemBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "delete cliked", Toast.LENGTH_SHORT).show();
//
//            }
//        });
        if (onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position, toDoModel);
                }
            });
        }

        holder.displayItemBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "delete cliked" + toDoModel.id, Toast.LENGTH_SHORT).show();
//                deleteRecordWhere(toDoModel.id);
                RoDatabase.getInstance(this).todoDao().deleteRecordWhere(toDoModel.id);

                if (HomeFragment.AllTodo.size() != 0) {
                    HomeFragment.AllTodo.remove(position);
                    HomeFragment.recyclerViewAdapter.notifyDataSetChanged();
                } else if (TestFragment.AllTodoWhereCategory.size() != 0) {
                    TestFragment.AllTodoWhereCategory.remove(position);
                    TestFragment.recyclerViewAdapter.notifyDataSetChanged();
                }
//                pos = position;
            }


        });

        holder.displayItemBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateElemId = toDoModel.getId();
                Intent intent = new Intent(v.getContext(), InsertNewTodoActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("updateElemId", updateElemId);
                v.getContext().startActivity(intent);

//                Toast.makeText(v.getContext(), "Edit Cliked", Toast.LENGTH_SHORT).show();

            }
        });
        viewBinderHelper.closeLayout(String.valueOf(toDoModel.getId()));
        holder.displayItemBinding.tvTaskTitle.setText(toDoModel.getTodoTitle());
        holder.displayItemBinding.tvTaskContent.setText(toDoModel.getTodoContect());

//        viewBinderHelper.bind(holder.displayItemBinding.swipeLayout, toDoModel.getTodoTitle(), toDoModel.getTodoContect());


    }

    @Override
    public int getItemCount() {
        if (toDoModels == null) {
            return 0;
        } else {
            return toDoModels.size();
        }
    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        ToDoModel toDoModel = toDoModels.get(position);
//        holder.displayItemBinding.tvTaskTitle.setText(toDoModel.getTodoTitle());
//        holder.displayItemBinding.tvTaskContent.setText(toDoModel.getTodoContect());
//    }
//
//    @Override
//    public int getItemCount() {
//        if (toDoModels == null){
//            return 0;
//        }else {
//            return toDoModels.size();
//        }    }

//    class ViewHolder extends RecyclerView.ViewHolder{
//
//        DisplayItemBinding displayItemBinding;
//        public ViewHolder(@NonNull DisplayItemBinding itemView) {
//            super(itemView.getRoot());
//            this.displayItemBinding = itemView;
//        }
//    }

    class SwipeViewHolder extends RecyclerView.ViewHolder {
        DisplayItemBinding displayItemBinding;

        public SwipeViewHolder(@NonNull DisplayItemBinding itemView) {
            super(itemView.getRoot());
            this.displayItemBinding = itemView;


            /// events
//            displayItemBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "delete cliked" , Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//            displayItemBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "Edit Cliked", Toast.LENGTH_SHORT).show();
//                }
//            });


        }
//        void bindData(ToDoModel toDoModel){
//            displayItemBinding.tvTaskTitle.setText(toDoModel.getTodoTitle());
//            displayItemBinding.tvTaskContent.setText(toDoModel.getTodoContect());
//        }
    }

    //    private void deleteRecordWhere(int id) {
//        RoDatabase.getInstance(this).todoDao().deleteRecordWhere(id);
//
//        if (HomeFragment.AllTodo != null) {
//            HomeFragment.AllTodo.remove(pos);
//            HomeFragment.recyclerViewAdapter.notifyDataSetChanged();
//        } else if (TestFragment.AllTodoWhereCategory != null) {
//            TestFragment.AllTodoWhereCategory.remove(pos);
//            TestFragment.recyclerViewAdapter.notifyDataSetChanged();
//        }
//
////                TestFragment.recyclerViewAdapter.notifyDataSetChanged();
//    }

    public interface OnItemClickListener {
        void onItemClick(int pos, ToDoModel toDoModel);

    }
}


