package com.alamat.todolistapp.roomDatabase;


import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alamat.todolistapp.activities_fragments.RecyclerViewAdapter;
import com.alamat.todolistapp.models.ToDoCategoryModel;
import com.alamat.todolistapp.models.ToDoModel;

@Database(entities = {ToDoModel.class, ToDoCategoryModel.class},version = 5,exportSchema = false)
public abstract class RoDatabase extends RoomDatabase {

    private static RoDatabase roomDataBase;
    private static final String DBName = "TodoListDataBase";
    private static Context context;



    public static RoDatabase getInstance(Context context){
        if (roomDataBase == null){
            roomDataBase = Room.databaseBuilder(context , RoDatabase.class,DBName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDataBase;
    }




    public static RoDatabase getInstance(View.OnClickListener onClickListener) {
        if (roomDataBase == null){
            roomDataBase = Room.databaseBuilder(context , RoDatabase.class,DBName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDataBase;
    }

    public static RoDatabase getInstance(DialogInterface.OnClickListener onClickListener) {
        if (roomDataBase == null){
            roomDataBase = Room.databaseBuilder(context , RoDatabase.class,DBName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDataBase;
    }

    public static RoDatabase getInstance(RecyclerViewAdapter recyclerViewAdapter) {
        if (roomDataBase == null){
            roomDataBase = Room.databaseBuilder(context , RoDatabase.class,DBName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDataBase;
    }

    public abstract TodoDao todoDao();


}
