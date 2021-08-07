package com.alamat.todolistapp.activities_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.todolistapp.R;
import com.alamat.todolistapp.databinding.FragmentTestBinding;
import com.alamat.todolistapp.models.ToDoModel;
import com.alamat.todolistapp.roomDatabase.RoDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class TestFragment extends Fragment {

    private FragmentTestBinding fragmentTestBinding;
    View view;

    static List<ToDoModel> AllTodoWhereCategory ;
    RecyclerView.LayoutManager layoutManager;
    static RecyclerViewAdapter recyclerViewAdapter;

    public static String categoryColValue = "";


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTestBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);

        assert getArguments() != null;
        categoryColValue = getArguments().getString("itemTitle");

        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAdapter = new RecyclerViewAdapter(null);
        fragmentTestBinding.recyclerView.setLayoutManager(layoutManager);
        fragmentTestBinding.recyclerView.setAdapter(recyclerViewAdapter);

        // new insert to_do
        fragmentTestBinding.fabAddNewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InsertNewTodoActivity.class);
                startActivity(intent);
            }
        });

        view = fragmentTestBinding.getRoot();
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        AllTodoWhereCategory = RoDatabase.getInstance(getContext()).todoDao().getAllTodoWhereCategory(categoryColValue);
        recyclerViewAdapter = new RecyclerViewAdapter(AllTodoWhereCategory);
        fragmentTestBinding.recyclerView.setAdapter(recyclerViewAdapter);
    }

}