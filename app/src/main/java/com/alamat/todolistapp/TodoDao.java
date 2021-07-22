package com.alamat.todolistapp;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insertTodo(ToDoModel toDoModel);

    @Query("select * from todomodel;")
    List<ToDoModel> getAllTodo();
}
