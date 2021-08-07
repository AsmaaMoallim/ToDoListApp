package com.alamat.todolistapp.activities_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.todolistapp.R;
import com.alamat.todolistapp.databinding.FragmentHomeBinding;
import com.alamat.todolistapp.models.ToDoModel;
import com.alamat.todolistapp.roomDatabase.RoDatabase;

import java.util.List;


public class HomeFragment extends Fragment {

    static FragmentHomeBinding fragmentHomeBinding;
    View view;

    static List<ToDoModel> AllTodo;

    RecyclerView.LayoutManager layoutManager;
    static RecyclerViewAdapter recyclerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAdapter = new RecyclerViewAdapter(null);
        fragmentHomeBinding.recyclerView.setLayoutManager(layoutManager);
        fragmentHomeBinding.recyclerView.setAdapter(recyclerViewAdapter);


        view = fragmentHomeBinding.getRoot();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        AllTodo = RoDatabase.getInstance(getContext()).todoDao().getAllTodo();
        recyclerViewAdapter = new RecyclerViewAdapter(AllTodo);
        fragmentHomeBinding.recyclerView.setAdapter(recyclerViewAdapter);


    }

}