package com.alamat.todolistapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alamat.todolistapp.databinding.ActivityMainBinding;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    static ActivityMainBinding activityMainBinding;
    //    static ArrayList<String> menuItemsList = new ArrayList<String>();
    static List<ToDoCategoryModel> AllToDoCategory;

    ArrayList<String> tes;
    static List<ToDoModel> searchedList;

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("TAG", "onQueryTextSubmit log");

                searchedList = RoDatabase.getInstance(this).todoDao().search(query);
                for (ToDoModel item : searchedList) {
                    Log.e("TAG", "here"+item.todoTitle);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("TAG", "onQueryTextChange log");
                HomeFragment.recyclerViewAdapter.filter(newText);
//                TestFragment.recyclerViewAdapter.filter(newText);

//                searchedList = RoDatabase.getInstance(this).todoDao().search(newText);
//                for (ToDoModel item : searchedList) {
//                    Log.e("TAG", "here"+item.todoTitle);
//                }
//                if (searchedList != null){
//                    Log.e("TAG", searchedList.todoTitle );
//                }

//                HomeFragment.recyclerViewAdapter = new RecyclerViewAdapter(searchedList);
//                HomeFragment.fragmentHomeBinding.recyclerView.setAdapter(HomeFragment.recyclerViewAdapter);
//                HomeFragment.recyclerViewAdapter.
//                tes.fil
//                tes.getfillter
//                    tes.get(Integer.parseInt(newText));
//                HomeFragment.AllTodo.stream().filter();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

//        Log.e("TAG", "oncreate log");

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

//        activityMainBinding.nav.getMenu().getItem(1).setActionView();

//
//        activityMainBinding.nav.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                AlertDialog diaBox = ConfirmDeletion();
//                diaBox.show();
//                return false;
//            }
//        });

//\

        // Left
//        activityMainBinding.listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        // Right
        activityMainBinding.listView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        ArrayList<String> list = new ArrayList<>();
//        list.add("swipe");
//        list.add("swipe");
//        list.add("swipe");
//        list.add("swipe");
//        list.add("swipe");
        for (int i =0 ; i<activityMainBinding.nav.getMenu().size(); i++){
            list.add(activityMainBinding.nav.getMenu().getItem(i).getTitle().toString());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,  list);
        activityMainBinding.listView.setAdapter(arrayAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
//                SwipeMenu m = (SwipeMenu) activityMainBinding.nav.getMenu();
//                m.addMenuItem(openItem);
//                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(100);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_launcher_background);
                // add to menu
                menu.addMenuItem(deleteItem);
//                 m = (SwipeMenu) activityMainBinding.nav.getMenu();
//                m.addMenuItem(deleteItem);
            }
        };
//
//        activityMainBinding.listView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("TAG", "click ") ;
//
//            }
//        });
        activityMainBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Log.e("TAG", "click ") ;

            }
        });
        activityMainBinding.listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Log.e("TAG", "open log "+ index) ;

                        break;
                    case 1:
                        // delete
                        Log.e("TAG", "delete log "+ index) ;

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
// set creator
        activityMainBinding.listView.setMenuCreator(creator);


        activityMainBinding.nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                if (item.getItemId() == R.id.home) {
                    fragment = new HomeFragment();
                } else {
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

//


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

    private AlertDialog ConfirmDeletion() {

        AlertDialog alert = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom))
                .setTitle("حذف قسم")
                .setMessage("هل تريد حذف هذا القسم؟")
                .setPositiveButton("حذف", new DatePickerDialog.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        dialog.dismiss();
                    }

                })

                .setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();


        return alert;


    }



}