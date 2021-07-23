package com.alamat.todolistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.todolistapp.databinding.DisplayItemBinding;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SwipeViewHolder> {


    List<ToDoModel> toDoModels;

    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public RecyclerViewAdapter(List<ToDoModel> toDoModel) {
        this.toDoModels = toDoModel;
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
        holder.displayItemBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "delete cliked" + toDoModel.id , Toast.LENGTH_SHORT).show();
                deleteRecordWhere(toDoModel.id);
            }

            private void deleteRecordWhere(int id) {
                RoDatabase.getInstance(this).todoDao().deleteRecordWhere(id);

                if (HomeFragment.recyclerViewAdapter != null) {
                    HomeFragment.AllTodo.remove(position);
                    HomeFragment.recyclerViewAdapter.notifyDataSetChanged();
                }
                if (TestFragment.recyclerViewAdapter != null) {
                    TestFragment.AllTodoWhereCategory.remove(position);
                    TestFragment.recyclerViewAdapter.notifyDataSetChanged();
                }

//                TestFragment.recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        holder.displayItemBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Edit Cliked", Toast.LENGTH_SHORT).show();
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
}
