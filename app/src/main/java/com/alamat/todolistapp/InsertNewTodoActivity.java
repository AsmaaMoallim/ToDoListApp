package com.alamat.todolistapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alamat.todolistapp.databinding.ActivityInsertNewTodoBinding;

import java.util.List;

public class InsertNewTodoActivity extends AppCompatActivity {

    private ActivityInsertNewTodoBinding activityInsertNewTodoBinding;
    static ToDoModel updateingTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInsertNewTodoBinding = DataBindingUtil.setContentView(InsertNewTodoActivity.this, R.layout.activity_insert_new_todo);

        if (RecyclerViewAdapter.updateElemId != -1){

            updateingTodo = RoDatabase.getInstance(this).todoDao().getOneTodoWhereCategory(RecyclerViewAdapter.updateElemId);

            activityInsertNewTodoBinding.etTaskTitle.setText(updateingTodo.getTodoTitle());
            activityInsertNewTodoBinding.etTaskContent.setText(updateingTodo.getTodoContect());
            activityInsertNewTodoBinding.btnAddTsak.setText("حفظ التعديل");

            activityInsertNewTodoBinding.btnAddTsak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RoDatabase.getInstance(this).todoDao().update(activityInsertNewTodoBinding.etTaskTitle.getText().toString(),
                            activityInsertNewTodoBinding.etTaskContent.getText().toString(),
                            RecyclerViewAdapter.updateElemId);
                    finish();
                    RecyclerViewAdapter.updateElemId = -1;

                }
            });
        }
        else {
            activityInsertNewTodoBinding.btnAddTsak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertTodo();
                    finish();
                }
            });
        }

    }

    private void insertTodo() {
        ToDoModel todoModel = new ToDoModel(activityInsertNewTodoBinding.etTaskTitle.getText().toString(),
                activityInsertNewTodoBinding.etTaskContent.getText().toString(),
                TestFragment.categoryColValue);
        RoDatabase.getInstance(this).todoDao().insertTodo(todoModel);

        activityInsertNewTodoBinding.etTaskTitle.setText("");
        activityInsertNewTodoBinding.etTaskContent.setText("");
    }
}