package com.example.ffcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

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
        else if(startx==endx)
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
        else
        {
            while (endy != starty) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canvas.drawLine(startx, starty, startx+1, starty+1, paint);
                starty++;
                startx++;
            }

        }
    }

    void maker1(int a,int b,int c,int d)
    {
        // Line
        paint = new Paint();
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setStrokeWidth(10);
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
        else if(startx==endx)
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
        else
        {
            while (endy != starty) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canvas.drawLine(startx, starty, startx+1, starty+1, paint);
                starty++;
                startx++;
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

        {//Frame
            maker(0,0,0,3800);
            maker(0,3800,86*9,3800);
            maker(86*9,0,86*9,3800);
            maker(0,0,86*9,0);
        }
        {//columns
            for(int i=1;i<=13;i++)
            {
                if(i<7)
                {
                    maker(0,95*i*3,86*9,95*i*3);
                }
                else if(i>7)
                {
                    maker(0,95*i*3+95,86*9,95*i*3+95);
                }
                else
                {
                    maker(0,95*(i-1)*3+95,86*9,95*(i-1)*3+95);
                }
            }
        }
        {//rows
            for(int i=1;i<=6;i++)
            {
                if(i<=2)
                {
                    maker(86*2*i,0,86*2*i,3800);
                }
                else
                {
                    maker(86*(i+2),0,86*(i+2),3800);
                }
            }
        }
        LinearLayout linLayout = findViewById(R.id.lin);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = 200;
        int newHeight = 200;

        // calculate the scale - in this case = 0.4f
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // createa matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // rotate the Bitmap
        matrix.postRotate(45);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                width, height, matrix, true);

        // make a Drawable from Bitmap to allow to set the BitMap
        // to the ImageView, ImageButton or what ever
        BitmapDrawable bmd = new BitmapDrawable(resizedBitmap);

        // set the Drawable on the ImageView
        drawingImageView.setImageDrawable(bmd);

        // center the Image
        drawingImageView.setScaleType(ImageView.ScaleType.CENTER);

        // add ImageView to the Layout
        linLayout.addView(drawingImageView,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT
                )
        );

        // set LinearLayout as ContentView
        setContentView(linLayout);
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
