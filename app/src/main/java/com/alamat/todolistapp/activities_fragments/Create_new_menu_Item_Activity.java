package com.alamat.todolistapp.activities_fragments;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alamat.todolistapp.R;
import com.alamat.todolistapp.databinding.ActivityCreateNewMenuItemBinding;
import com.alamat.todolistapp.models.ToDoCategoryModel;
import com.alamat.todolistapp.roomDatabase.RoDatabase;


public class Create_new_menu_Item_Activity extends AppCompatActivity {

    private ActivityCreateNewMenuItemBinding activityCreateNewMenuItemBinding;

    // go back on action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_menu_item);
        activityCreateNewMenuItemBinding = DataBindingUtil.setContentView(Create_new_menu_Item_Activity.this, R.layout.activity_create_new_menu_item);


        // action bar
        setSupportActionBar(activityCreateNewMenuItemBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityCreateNewMenuItemBinding.btnAddNewMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // insert and fetch new database ToDoCategory data
                insertTodoCategory();
                MainActivity.AllToDoCategory = RoDatabase.getInstance(getBaseContext()).todoDao().getAllTodoCategory();

                // recreate menu and notify data changed
                MainActivity.createmenu();
                MainActivity.arrayAdapter.notifyDataSetChanged();
                finish();
            }
        });

    }


    public void insertTodoCategory() {
        try {
            ToDoCategoryModel toDoCategoryModel = new
                    ToDoCategoryModel(activityCreateNewMenuItemBinding.etMenuItemTitle.getText().toString());
            RoDatabase.getInstance(this).todoDao().insertTodoCategory(toDoCategoryModel);

        } catch (SQLiteConstraintException e) {
//            Log.e("TAG", "Catch");

            Toast.makeText(getApplicationContext(),
                    "لديك قسم آخر بنفس الاسم مسبقًا، حاول مرة اخرى",Toast.LENGTH_SHORT)
                    .show();;
        }
    }
}