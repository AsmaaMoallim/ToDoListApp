package com.alamat.todolistapp.y;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;


import java.util.List;

public class DisplayActivity extends AppCompatActivity {
//
//    ActivityDisplayBinding activityDisplayBinding;
//    RecyclerView.LayoutManager layoutManager;
//    RecyclerViewAdapter recyclerViewAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        getSupportActionBar().hide();
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//
//
//        activityDisplayBinding = DataBindingUtil.setContentView(DisplayActivity.this,R.layout.activity_display);
//
//        layoutManager = new LinearLayoutManager(this);
//        recyclerViewAdapter = new RecyclerViewAdapter(null);
//        activityDisplayBinding.recyclerView.setLayoutManager(layoutManager);
//        activityDisplayBinding.recyclerView.setAdapter(recyclerViewAdapter);
//
//
//
//
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        List<ToDoModel> allTodo = RoDatabase.getInstance(this).todoDao().getAllTodo();
//        recyclerViewAdapter = new RecyclerViewAdapter(allTodo);
//        activityDisplayBinding.recyclerView.setAdapter(recyclerViewAdapter);
//    }
}