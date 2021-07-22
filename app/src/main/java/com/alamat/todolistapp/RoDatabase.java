package com.alamat.todolistapp;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ToDoModel.class},version = 1,exportSchema = false)
public abstract class RoDatabase extends RoomDatabase {

    private static RoDatabase roomDataBase;
    private static final String DBName = "TodoListDataBase";
    public abstract TodoDao todoDao();

    public static RoDatabase getInstance(Context context){
        if (roomDataBase == null){
            roomDataBase = Room.databaseBuilder(context , RoDatabase.class,DBName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDataBase;
    }
}
