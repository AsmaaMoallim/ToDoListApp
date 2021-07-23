package com.alamat.todolistapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ToDoCategoryModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String CategoryName;

    public ToDoCategoryModel() {
    }


    @Ignore
    public ToDoCategoryModel(String CategoryName){
        this.CategoryName = CategoryName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
