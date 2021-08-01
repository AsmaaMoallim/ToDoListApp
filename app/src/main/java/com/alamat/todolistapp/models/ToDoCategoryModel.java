package com.alamat.todolistapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"CategoryName"}, unique = true)})
public class ToDoCategoryModel {

    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "CategoryName")
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
