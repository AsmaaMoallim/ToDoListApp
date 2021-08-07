package com.alamat.todolistapp.activities_fragments;

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

import com.alamat.todolistapp.R;
import com.alamat.todolistapp.databinding.ActivityMainBinding;
import com.alamat.todolistapp.models.ToDoCategoryModel;
import com.alamat.todolistapp.models.ToDoModel;
import com.alamat.todolistapp.roomDatabase.RoDatabase;
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
    static List<ToDoCategoryModel> AllToDoCategory;

    static ArrayList<String> list = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    static String updateMenue = null;
    static int currentFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Log.e("TAG", "oncreate log");

        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);


        // set custom action bar
        setSupportActionBar(activityMainBinding.toolbar);

        // ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activityMainBinding.drawer, activityMainBinding.toolbar, R.string.open, R.string.cloes);

        // Drawer listener
        activityMainBinding.drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Default displayed fragment
        getSupportFragmentManager().beginTransaction().
                replace(R.id.main_fragment, new HomeFragment()).commit();
        activityMainBinding.nav.setCheckedItem(R.id.home);

        // Fetching of ToDoCategory from tha data base + call create menu function
        AllToDoCategory = RoDatabase.getInstance(this).todoDao().getAllTodoCategory();
        createmenu();



        // ArrayAdapter for the listview + setting the adapter
        arrayAdapter = new ArrayAdapter(MainActivity.this,
                R.layout.menu_item_style, list);
        activityMainBinding.listView.setAdapter(arrayAdapter);

        // creation of the swipe menu item
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(250);
                deleteItem.setTitle("حذف");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.BLACK);
                // add to menu
                menu.addMenuItem(deleteItem);

                SwipeMenuItem dupdateItem = new SwipeMenuItem(getApplicationContext());
                dupdateItem.setBackground(new ColorDrawable(Color.GREEN));
                dupdateItem.setWidth(250);
                dupdateItem.setTitle("تعديل");
                dupdateItem.setTitleSize(16);
                dupdateItem.setTitleColor(Color.BLACK);
                // add to menu
                menu.addMenuItem(dupdateItem);

            }
        };


        // set creator
        activityMainBinding.listView.setMenuCreator(creator);

        // swipe action direction
        // Left
        // activityMainBinding.listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        // Right
        activityMainBinding.listView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);


        // setting listener of clicking listview items
        activityMainBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", "click ");
                Fragment fragment = null;
                currentFragment = 2;

                String itemTitle = list.get(position);
                Bundle extras = new Bundle();
                extras.putString("itemTitle", itemTitle);
                fragment = new TestFragment();
                fragment.setArguments(extras);

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_fragment, fragment).commit();

                activityMainBinding.drawer.closeDrawer(GravityCompat.START);
            }
        });


        // setting listener of clicking listView_menuItem  (Delete)
        activityMainBinding.listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                if (index == 0) {
                    // delete
                    Log.e("TAG", "delete log " + index);
                    AlertDialog diaBox = ConfirmDeletion(position);
                    diaBox.show();

                }
                else if (index == 1){
                    // update
                    Log.e("TAG", "update log " + index);
                    updateMenue = list.get(position);
                    int listPos =position ;

                    Intent intent = new Intent(getApplicationContext(), Create_new_menu_Item_Activity.class);
//                    Bundle extras = new Bundle();
                    intent.putExtra("updateMenue", updateMenue);
                    intent.putExtra("listPos", listPos);

                    startActivity(intent);
                }
                return false;
            }
        });



        // the main menu item (Main) click listener
        activityMainBinding.nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    currentFragment = 1;
                    fragment = new HomeFragment();
                }
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_fragment, fragment).commit();
                activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });



        // Create new menu Item action
        activityMainBinding.navFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Create_new_menu_Item_Activity.class);
                startActivity(intent);
            }
        });

    }

    // search bar
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
                if (currentFragment == 1) {
                    HomeFragment.recyclerViewAdapter.filter(query);
                } else if (currentFragment == 2) {
                    TestFragment.recyclerViewAdapter.filter(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("TAG", "onQueryTextChange log");
                if (currentFragment == 1) {
                    HomeFragment.recyclerViewAdapter.filter(newText);
                } else if (currentFragment == 2) {
                    Log.e("TAG", "TestFragment log");

                    TestFragment.recyclerViewAdapter.filter(newText);
                }

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    // function to create menu from the database Categories
    public static void createmenu() {

        MainActivity.list.clear();
        for (int i = 0; i < AllToDoCategory.size(); i++) {
            String newMenuItem = AllToDoCategory.get(i).getCategoryName();
            MainActivity.list.add(newMenuItem);

        }


    }


    // confirm deletion of listView_menu items
    private AlertDialog ConfirmDeletion(int pos) {

        AlertDialog alert = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom))
                .setTitle("حذف قسم")
                .setMessage("هل تريد حذف هذا القسم؟")

                // delete event
                .setPositiveButton("حذف", new DatePickerDialog.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        // delete all record of specific category
                        RoDatabase.getInstance(this).todoDao().deleteAllRecordsWhere(list.get(pos));

                        // delete the category itself
                        RoDatabase.getInstance(this).todoDao().deleteTodoCategoryWhere(list.get(pos));

                        // delete this category form the listview
                        MainActivity.list.remove(pos);
                        arrayAdapter.notifyDataSetChanged();

                        // Main fragment / update recycler view
                        if (currentFragment == 1) {
                            HomeFragment.AllTodo.clear();
                            List<ToDoModel> AllTodo= RoDatabase.getInstance(this).todoDao().getAllTodo();
                            HomeFragment.AllTodo.addAll(AllTodo);
                            HomeFragment.recyclerViewAdapter.notifyDataSetChanged();


                        }
                        // General fragment / update recycler view
                        else if (currentFragment == 2) {
                            TestFragment.AllTodoWhereCategory.clear();
                            List<ToDoModel> AllTodoWhereCategory= RoDatabase.getInstance(this).todoDao().getAllTodo();
                            TestFragment.AllTodoWhereCategory.addAll(AllTodoWhereCategory);
                            TestFragment.recyclerViewAdapter.notifyDataSetChanged();
                        }

                        dialog.dismiss();
                        activityMainBinding.drawer.closeDrawer(GravityCompat.START);

                    }

                })

                // cancel event
                .setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        activityMainBinding.drawer.closeDrawer(GravityCompat.START);


                    }
                })
                .create();


        return alert;


    }

}