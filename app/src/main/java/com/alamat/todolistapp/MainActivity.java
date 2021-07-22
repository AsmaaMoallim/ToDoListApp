package com.alamat.todolistapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alamat.todolistapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

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

        activityMainBinding.nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.work:
                        fragment =new WorkFragment();
                        break;
                    case R.id.test:
                        fragment =new TestFragment();
                        break;


                }
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_fragment,fragment).commit();

                activityMainBinding.drawer.closeDrawer(GravityCompat.START);

                return true;

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


}