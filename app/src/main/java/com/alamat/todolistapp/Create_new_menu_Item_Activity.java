package com.alamat.todolistapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alamat.todolistapp.databinding.ActivityCreateNewMenuItemBinding;


public class Create_new_menu_Item_Activity extends AppCompatActivity {

    private ActivityCreateNewMenuItemBinding activityCreateNewMenuItemBinding;
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


        setSupportActionBar(activityCreateNewMenuItemBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityCreateNewMenuItemBinding.btnAddNewMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                insertTodoCategory();
                MainActivity.AllToDoCategory = RoDatabase.getInstance(getBaseContext()).todoDao().getAllTodoCategory();

//                MainActivity.menuItemsList.add(activityCreateNewMenuItemBinding.etMenuItemTitle.getText().toString());
//                MainActivity obj = new MainActivity();
//                obj.createmenu();
                MainActivity.createmenu();
                MainActivity.arrayAdapter.notifyDataSetChanged();

                //                Menu newMenu= activityMainBinding.nav.getMenu();
//                newMenu.add(0, 0, 0, activityCreateNewMenuItemBinding.etMenuItemTitle.getText());
                finish();
            }
        });

    }

    public void insertTodoCategory() {
        ToDoCategoryModel toDoCategoryModel = new ToDoCategoryModel(activityCreateNewMenuItemBinding.etMenuItemTitle.getText().toString());
        RoDatabase.getInstance(this).todoDao().insertTodoCategory(toDoCategoryModel);
    }
}