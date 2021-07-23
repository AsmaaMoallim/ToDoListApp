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

    @Query("select * from todomodel where todoCategory = :categoryColValue ")
    List<ToDoModel> getAllTodoWhereCategory(String categoryColValue);

    @Query("delete from todomodel where :id")
    void deleteRecordWhere(int id);

    //////////////////////////////////////////////////////////////////////////

    @Insert
    void insertTodoCategory(ToDoCategoryModel toDoCategoryModel);

    @Query("select * from toDoCategoryModel;")
    List<ToDoCategoryModel> getAllTodoCategory();

}

