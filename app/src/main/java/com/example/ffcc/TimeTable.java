package com.example.ffcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TimeTable extends AppCompatActivity {
    ImageView drawingImageView;
    Canvas canvas;
    Paint paint;
    void maker(int a,int b,int c,int d)
    {
        // Line
        paint = new Paint();
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStrokeWidth(5);
        int startx = a;
        int starty = b;
        int endx = c;
        int endy = d;
        if(starty==endy) {
            while (endx >= startx) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canvas.drawLine(startx, starty, startx + 1, endy, paint);
                startx++;
            }
        }
        else
        {
            while (endy >= starty) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canvas.drawLine(startx, starty, endx, starty+1, paint);
                starty++;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        drawingImageView = (ImageView) this.findViewById(R.id.imageView3);
        Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        drawingImageView.setImageBitmap(bitmap);
        maker(1,10,500,10);
        maker(10,1,10,500);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.timeTable);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.forTeachers:if(Main.codes.size()<4) {
                        Toast.makeText(TimeTable.this, "You have not selected at least four subjects,Please go to subject selection", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent I = new Intent(getApplicationContext(), ForTeachers.class);
                        startActivity(I);
                        return true;
                    }
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
