package com.example.ffcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TimeTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.main2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.forTeachers:
                        Intent I=new Intent(getApplicationContext(),ForTeachers.class);
                        startActivity(I);
                        return true;
                    case R.id.timeTable:return true;
                    case R.id.main2:Intent I2=new Intent(getApplicationContext(),Main.class);
                        startActivity(I2);
                        return true;

                }
                return false;
            }
        });
    }
}
