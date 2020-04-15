package com.example.ffccapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    TextView creds ;
    ArrayList<String> PC;
    ArrayList<String> PE;
    ArrayList<String> UC;
    ArrayList<String> UE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        creds=(TextView)findViewById(R.id.creds);
        creds.setText("Credits Taken:0");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.BCE:
                Toast.makeText(this, "BCE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.MCB:
                Toast.makeText(this, "MCB", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BBT:
                Toast.makeText(this, "BBT", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BDS:
                Toast.makeText(this, "BDS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BEE:
                Toast.makeText(this, "BEE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BIT:
                Toast.makeText(this, "BIT", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BME:
                Toast.makeText(this, "BME", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
