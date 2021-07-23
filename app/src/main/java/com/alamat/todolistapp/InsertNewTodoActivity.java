package com.alamat.todolistapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alamat.todolistapp.databinding.ActivityInsertNewTodoBinding;

public class InsertNewTodoActivity extends AppCompatActivity {

    private ActivityInsertNewTodoBinding activityInsertNewTodoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInsertNewTodoBinding = DataBindingUtil.setContentView(InsertNewTodoActivity.this, R.layout.activity_insert_new_todo);


        activityInsertNewTodoBinding.btnAddTsak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTodo();
                finish();
            }
        });

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