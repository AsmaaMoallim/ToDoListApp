package com.alamat.todolistapp.roomDatabase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alamat.todolistapp.models.ToDoCategoryModel;
import com.alamat.todolistapp.models.ToDoModel;

import java.util.List;

@Dao
public interface TodoDao {

    ///////// ToDoModel operations
    @Insert
    void insertTodo(ToDoModel toDoModel);

    @Query("select * from todomodel;")
    List<ToDoModel> getAllTodo();

    @Query("select * from todomodel where todoCategory = :categoryColValue ")
    List<ToDoModel> getAllTodoWhereCategory(String categoryColValue);

    @Query("delete from todomodel where id = :id")
    void deleteRecordWhere(int id);

    @Query("select * from todomodel where id = :id ")
    ToDoModel getOneTodoWhereCategory(int id);

    @Query("update todomodel set todoTitle = :newTitle , todoContect = :newContent where id = :id ")
    void update(String newTitle, String newContent, int id);

    @Query("delete from todomodel where todoCategory = :todoCategory")
    void deleteAllRecordsWhere(String todoCategory);

    @Query("update todomodel set todoCategory = :NewCategoryName where todoCategory = :OldCategoryName")
    void updateAllRecordsWhere(String OldCategoryName, String NewCategoryName);

    @Query("select * from todomodel where todoTitle like '%' || :key || '%' or todoContect like '%' || :key || '%'")
    List<ToDoModel> search(String key);

    ///////// ToDoCategoryModel operations

    @Insert
    void insertTodoCategory(ToDoCategoryModel toDoCategoryModel);

    @Query("select * from toDoCategoryModel;")
    List<ToDoCategoryModel> getAllTodoCategory();

    @Query("select * from toDoCategoryModel where CategoryName = :CategoryName")
    ToDoCategoryModel getTodoCategoryWhere(String CategoryName);

    @Query("update toDoCategoryModel set CategoryName = :NewCategoryName where CategoryName = :OldCategoryName")
    void updateTodoCategory(String OldCategoryName, String NewCategoryName);

    @Query("delete from toDoCategoryModel where CategoryName = :CategoryName")
    void deleteTodoCategoryWhere(String CategoryName);

}

