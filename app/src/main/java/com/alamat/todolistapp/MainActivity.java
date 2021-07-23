package com.alamat.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alamat.todolistapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static ActivityMainBinding activityMainBinding;
//    static ArrayList<String> menuItemsList = new ArrayList<String>();
    static List<ToDoCategoryModel> AllToDoCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);


        setSupportActionBar(activityMainBinding.toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activityMainBinding.drawer, activityMainBinding.toolbar, R.string.open, R.string.cloes);


        activityMainBinding.drawer.addDrawerListener(toggle);

        toggle.syncState();

        getSupportFragmentManager().beginTransaction().
                replace(R.id.main_fragment, new HomeFragment()).commit();
        activityMainBinding.nav.setCheckedItem(R.id.home);

        AllToDoCategory = RoDatabase.getInstance(this).todoDao().getAllTodoCategory();
        createmenu();


        activityMainBinding.nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                if (item.getItemId() == R.id.home){
                    fragment = new HomeFragment();
                }
                else {
                    String itemTitle = (String) item.getTitle();
//                Intent intent = new Intent(MainActivity.this, TestFragment.class);
                    Bundle extras = new Bundle();
                    extras.putString("itemTitle", itemTitle);
                    fragment = new TestFragment();
                    fragment.setArguments(extras);
                }

//                startActivity(intent);
//                switch (item.getItemId()) {
//
//                    case R.id.home:
//                        fragment = new HomeFragment();
//
//                        break;
//
//                    case R.id.work:
//                        fragment = new WorkFragment();
//                        break;
//                    case R.id.test:
//                        fragment = new TestFragment();
//                        break;
//                    case 0:
//                        fragment = new HomeFragment();
//                        break;
//
//                }
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_fragment, fragment).commit();

                activityMainBinding.drawer.closeDrawer(GravityCompat.START);

                return true;

            }
        });

        activityMainBinding.navFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Create_new_menu_Item_Activity.class);
                startActivity(intent);
            }
        });


//        activityMainBinding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        activityMainBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "good job", Toast.LENGTH_SHORT);
//
//                insertTodo();
//            }
//        });
    }
//    public void insertTodo(){
//        ToDoModel todoModel = new ToDoModel(activityMainBinding.etTaskTitle.getText().toString(),
//                activityMainBinding.etTaskContent.getText().toString());
//        RoDatabase.getInstance(this).todoDao().insertTodo(todoModel);
//
//        activityMainBinding.etTaskTitle.setText("");
//        activityMainBinding.etTaskContent.setText("");
//    }

//
//    private void insertTodo() {
//        ToDoModel todoModel = new ToDoModel(activityInsertNewTodoBinding.etTaskTitle.getText().toString(),
//                activityInsertNewTodoBinding.etTaskContent.getText().toString(),
//                TestFragment.categoryColValue);
//        RoDatabase.getInstance(this).todoDao().insertTodo(todoModel);
//
//        activityInsertNewTodoBinding.etTaskTitle.setText("");
//        activityInsertNewTodoBinding.etTaskContent.setText("");
//    }
    public static void createmenu() {
//        AllToDoCategory = RoDatabase.getInstance(this).todoDao().getAllTodoCategory();

//        if (MainActivity.menuItemsList.size() == 0) {
//            MainActivity.menuItemsList.add("base");
//        }
//        Menu menu = activityMainBinding.nav.getMenu();
//        menu.removeGroup(1);
//        for (ToDoCategoryModel s : AllToDoCategory) {
//            menu.add(1, i, 0, MainActivity.menuItemsList.get(i));
//        }
        ;
//        List<ToDoCategoryModel> toDoCategoryModels ;
//        insertTodoCategory();
        Menu menu = activityMainBinding.nav.getMenu();
        menu.removeGroup(1);
        for (int i = 0; i < AllToDoCategory.size(); i++) {
            ToDoCategoryModel menuItem = AllToDoCategory.get(i);
            menu.add(1, i, 0, menuItem.getCategoryName());
//            Toast.makeText(this,AllToDoCategory.get(i).getCategoryName(), Toast.LENGTH_SHORT);
        }
    }

//    List<ToDoCategoryModel> AllToDoCategory = RoDatabase.getInstance(this).todoDao().getAllTodoCategory();
//
//    public static void insertTodoCategory(String CategoryName){
//        ToDoCategoryModel toDoCategoryModel = new ToDoCategoryModel();
//        RoDatabase.getInstance(this).todoDao().insertTodoCategory(toDoCategoryModel);
//    }
}