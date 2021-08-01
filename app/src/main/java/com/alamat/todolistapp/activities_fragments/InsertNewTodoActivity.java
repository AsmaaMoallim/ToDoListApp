package com.alamat.todolistapp.activities_fragments;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alamat.todolistapp.R;
import com.alamat.todolistapp.databinding.ActivityInsertNewTodoBinding;
import com.alamat.todolistapp.models.ToDoModel;
import com.alamat.todolistapp.roomDatabase.RoDatabase;


public class InsertNewTodoActivity extends AppCompatActivity {


    private ActivityInsertNewTodoBinding activityInsertNewTodoBinding;
    static ToDoModel updateingTodo;

    // go back on action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInsertNewTodoBinding = DataBindingUtil.setContentView(InsertNewTodoActivity.this, R.layout.activity_insert_new_todo);


        // action bar
        setSupportActionBar(activityInsertNewTodoBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // updating
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

        // inserting new
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