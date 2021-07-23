package com.alamat.todolistapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ToDoModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    String todoTitle;

    @ColumnInfo
    String todoContect;

    @ColumnInfo
    String todoCategory;

    public ToDoModel() {
    }

    @Ignore
    public ToDoModel(String todoTitle, String todoContect, String todoCategory) {
        this.todoTitle = todoTitle;
        this.todoContect = todoContect;
        this.todoCategory = todoCategory;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoContect() {
        return todoContect;
    }

    public void setTodoContect(String todoContect) {
        this.todoContect = todoContect;
    }

    public String getTodoCategory() {
        return todoCategory;
    }

    public void setTodoCategory(String todoCategory) {
        this.todoCategory = todoCategory;
    }
}
