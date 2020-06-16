package com.example.ffcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;

public class List extends AppCompatActivity {
    TextView te;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        te=findViewById(R.id.te);
        String z="";

        try {
//            BufferedReader br = new BufferedReader(new FileReader("Teac.txt"));
//            String line=br.readLine();
//            if(line!=null)
//            {
//               z+=line+"\n";
//            }
            SQLiteDatabase my=this.openOrCreateDatabase("TT",MODE_PRIVATE,null);
            Cursor c=my.rawQuery("SELECT * FROM TT",null);
            c.moveToFirst();
            int i=0;
            int size=c.getCount();
            while(c!=null) {
                Log.e("The count is:", String.valueOf(size));
                int nameindex = c.getColumnIndex("code");
                Log.e("Code:", c.getString(nameindex));
                nameindex = c.getColumnIndex("ch");
                Log.e("Choice:", c.getString(nameindex));
            }
        }
        catch (Exception e)
        {
            Log.e("The error in list class",e.getMessage());
        }
        Intent I=getIntent();

//        String k=I.getStringExtra("List");
        if(z!=null)
        {
            Toast.makeText(this, "the size is:"+z.length(), Toast.LENGTH_SHORT).show();
        }
        te.setText(z);
    }
}