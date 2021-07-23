package com.alamat.todolistapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alamat.todolistapp.databinding.ActivityCreateNewMenuItemBinding;


public class Create_new_menu_Item_Activity extends AppCompatActivity {

    private ActivityCreateNewMenuItemBinding activityCreateNewMenuItemBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_menu_item);
        activityCreateNewMenuItemBinding = DataBindingUtil.setContentView(Create_new_menu_Item_Activity.this, R.layout.activity_create_new_menu_item);

        activityCreateNewMenuItemBinding.btnAddNewMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                insertTodoCategory();
                MainActivity.AllToDoCategory = RoDatabase.getInstance(getBaseContext()).todoDao().getAllTodoCategory();

//                MainActivity.menuItemsList.add(activityCreateNewMenuItemBinding.etMenuItemTitle.getText().toString());
//                MainActivity obj = new MainActivity();
//                obj.createmenu();
                MainActivity.createmenu();
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