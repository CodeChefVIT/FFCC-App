package com.example.ffcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Selector extends AppCompatActivity {
    RelativeLayout chooser;
    ImageView morn,even;
    EditText nodsubs;
    public void click(View v)
    {
        chooser.setVisibility(View.INVISIBLE);
        Main.nod=Integer.parseInt(nodsubs.getText().toString());
        Intent I=new Intent(Selector.this,Main.class);
        startActivity(I);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        chooser=findViewById(R.id.chooser);
        morn=findViewById(R.id.morn);
        even=findViewById(R.id.even);
        nodsubs=findViewById(R.id.nodsub);
    }
}
