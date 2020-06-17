package com.example.ffcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import io.reactivex.Observable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForTeachers extends AppCompatActivity {
    TextView textView ;
    RecyclerView recyclerView;
    Handler handle;
    Button next;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SearchableSpinner spinner;
    static ProgressDialog progressDoalog ;
    static ArrayList<Teac> numbers;
    static Set<String> codes;
    static HashMap<String , ArrayList<String>> collec;
    static HashMap<String , ArrayList<String>> collec1;
    Set<String>fac;
    int th;
    ArrayList<String> contact;
    String f;
    public void setAnimationEntry() {
        if (Build.VERSION.SDK_INT > 20) {

            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.TOP);
            slide.setDuration(900);
            slide.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(new Explode().setDuration(1000));
            getWindow().setEnterTransition(new Explode().setDuration(1000));
        }
    }
    boolean checker(String k)
    {
        int a=codes.size();
        codes.add(k);
        if(codes.size()>a)
        {
            return true;
        }
        return false;
    }
    boolean checker2(String k)
    {
        int a=codes.size();
        if(codes.size()<4)
        {
            return true;
        }
        return false;
    }
    void PrintToFile()
    {
        try{

            SQLiteDatabase my=this.openOrCreateDatabase("TT",MODE_PRIVATE,null);
            my.execSQL("CREATE TABLE IF NOT EXISTS TT (code TEXT,ch TEXT);");
            String f1="";
            if(contact!=null)
            {
                if(contact.size()<=4)
                {
                    for(int i=0;i<contact.size();i++)
                    {
                        f1=f1+contact.get(i).substring(2)+"*";
                    }
                    for(int j=contact.size();j<4;j++)
                    {
                        f1+="*";

                    }
                    String z=textView.getText().toString();
                    ContentValues value=new ContentValues();
                    value.put("code",z);
                    value.put("ch",f1);
                    my.insert("TT"," ",value);
//                    my.execSQL("INSERT INTO TT (code,ch) VALUES ("+z+","+f1+");");
                }
                else
                {
                    for(int i=0;i<4;i++)
                    {


                    }
                    String z=textView.getText().toString();
                    ContentValues value=new ContentValues();
                    value.put("code",z);
                    value.put("ch",f1);
                    my.insert("TT"," ",value);
//                    my.execSQL("INSERT INTO TT (code,ch) VALUES ("+z+","+f1+");");
                }
            }
            else
            {
                for(int i=0;i<4;i++)
                {
                    f1+="*";

                }
                String z=textView.getText().toString();
                ContentValues value=new ContentValues();
                value.put("code",z);
                value.put("ch",f1);
                my.insert("TT"," ",value);
//                my.execSQL("INSERT INTO TT (code,ch) VALUES ("+z+","+f1+");");
            }

//            pr.close();
//            br.close();
//            fr.close();

        }
        catch (Exception e1)
        {
            Log.e("The error is:",e1.getMessage());
        }
    }
    boolean checker1(String k)
    {
        int a=fac.size();
        fac.add(k);
        if(fac.size()>a)
        {
            return true;
        }
        return false;
    }
//    public void startnext(View v)
//    {
//        Main.noa++;//counter
//        if(Main.noa<Main.codes.size()) {
//            Intent I = new Intent(ForTeachers.this, ForTeachers.class);
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
//            if (true) {
//                if(codes.size()>4)
//                {
//                    Toast.makeText(this, "Select At most 4 teacher preference", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    //Main.choice.put(Main.subcodes.get(Main.noa-1),codes);
//                    PrintToFile();
//                    I.putExtra("Cho", th);
//                    I.putExtra("List",f);
//                    startActivity(I, options.toBundle());
//                }
//            } else {
//                Toast.makeText(this, "Please select at least 4 subjects", Toast.LENGTH_SHORT).show();
//            }
//        }
//        else{
//            Intent I=new Intent(ForTeachers.this, com.example.ffcc.TimeTable.class);
//            I.putExtra("Cho", th);
//            I.putExtra("List",f);
//            PrintToFile();
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
//            //Main.choice.put(Main.subcodes.get(Main.noa-1),codes);
//            startActivity(I,options.toBundle());
//        }
//    }
//    public void starter()
//    {
//        Main.noa++;
//        if(Main.noa<Main.codes.size()) {
//            Intent I = new Intent(ForTeachers.this, ForTeachers.class);
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
//            if (true) {
//                if(codes.size()>4)
//                {
//                    Toast.makeText(this, "Select At most 4 teacher preference", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    //Main.choice.put(Main.subcodes.get(Main.noa-1),codes);
//                    PrintToFile();
//                    I.putExtra("Cho", th);
//                    I.putExtra("List",f);
//                    startActivity(I, options.toBundle());
//                }
//            } else {
//                Toast.makeText(this, "Please select at least 4 subjects", Toast.LENGTH_SHORT).show();
//            }
//        }
//        else{
//            Intent I=new Intent(ForTeachers.this,com.example.ffcc.TimeTable.class);
//            I.putExtra("Cho", th);
//            I.putExtra("List",f);
//            PrintToFile();
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
//            //Main.choice.put(Main.subcodes.get(Main.noa-1),codes);
//            startActivity(I,options.toBundle());
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimationEntry();
        setContentView(R.layout.activity_for_teachers);
        collec=new HashMap<>();
        Intent I=getIntent();
            th = I.getIntExtra("Cho", 4);
            f=I.getStringExtra("List");
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.forTeachers);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.main2:Intent I=new Intent(getApplicationContext(),Main.class);
                        startActivity(I);
                        return true;
                    case R.id.forTeachers:return true;
                    case R.id.timeTable:Intent I2=new Intent(getApplicationContext(),TimeTable.class);
                        startActivity(I2);
                        return true;

                }
                return false;
            }
        });

        codes=new HashSet<String>();
        fac=new HashSet<>();
        textView=findViewById(R.id.textView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL) // Specify your api here
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        spinner=findViewById(R.id.spinner);

        collec1=new HashMap<>();

        recyclerView=findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        spinner = (SearchableSpinner) findViewById(R.id.spinner);

        final ArrayList<String> contacts = new ArrayList<>();
        handle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressDoalog.incrementProgressBy(1);
            }
        };
        progressDoalog = new ProgressDialog(ForTeachers.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("Downloading data ");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDoalog.getProgress() <= progressDoalog
                            .getMax()) {
                        Thread.sleep(200);
                        handle.sendMessage(handle.obtainMessage());
                        if (progressDoalog.getProgress() == progressDoalog
                                .getMax()) {
                            progressDoalog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        ArrayList<String> Array_codes=new ArrayList<>(Main.codes);
        final ArrayList<String> withchoice=new ArrayList<>();
        handle.sendMessage(handle.obtainMessage());
        List<Observable<?>> requests=new ArrayList<>();
//        for(int i=0;i<Main.codes.size();i++) {//Adding multiple api calls
//            final String subjectCode=Array_codes.get(i);
//            requests.add(api.teachers(subjectCode));
//        }

//       Observable.zip(requests,
//                new Function<Object[], Object>//Kind of inputs
//               () {
//                    @Override
//                    public Object apply(Object[] objects) {
//                        // Objects[] is an array of combined results of completed requests
//                        // do something with those results and emit new event
//                        return new Object();
//                    }
//                }
//                )
//                .subscribe(
//                        // Will be triggered if all requests will end successfully (4xx and 5xx also are successful requests too)
//                        new io.reactivex.functions.Consumer<Object>() {
//                            @Override
//                            public void accept(Object o)  {
//                                Log.e("The object part",o.toString());
//                            }
//                        },
//
//                        // Will be triggered if any error during requests will happen
//
//                        new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable e)  {
//                                //Do something on error completion of requests
//                            }
//                        }
//                );


//            final ArrayList<String> theory=new ArrayList<>();
//            final ArrayList<String> lab=new ArrayList<>();
//            call.enqueue(new Callback<List<Teachers>>() {
//                @Override
//                public void onResponse(Call<List<Teachers>> call, Response<List<Teachers>> response) {
//                    if (response.code() == 200) {
//                        Teachers[] resp = response.body().toArray(new Teachers[0]);
//                        Set<String> so = new HashSet<>();
//                        Toast.makeText(ForTeachers.this, "Let's Go!!", Toast.LENGTH_SHORT).show();
//                        if (resp != null) {
//                            Intent I = getIntent();
//                            th = I.getIntExtra("Cho", 4);
//                            int k1 = Integer.parseInt((resp[0].getFlag()));
//                            for (int i = 0; i < resp.length; i++)//For theory part
//                            {
//                                if (resp[i].getFlag() == null) {
//                                    Log.e("The error is:", "There is no flag value");
//                                    break;
//                                }
//                                int r=Integer.parseInt(resp[i].getFlag());
//                            if(r==0 || r==1) {
//                                try {
//                                    if ((resp[i].getFlag().equals(String.valueOf(th)))) {
//                                        theory.add(resp[i].getReview() + "," + resp[i].getSlot() + ":" + resp[i].getFaculty());
//                                    }
//                                } catch (Exception e) {
//                                    Toast.makeText(ForTeachers.this, e.getMessage() + " 1", Toast.LENGTH_SHORT).show();
//                                    theory.add(resp[i].getReview() + "," + resp[i].getSlot() + ":" + resp[i].getFaculty());
//                                }
//
//                            }
//                            else {
//                                try {
//                                    if ((resp[i].getFlag().equals(String.valueOf("2")))) {
//                                        lab.add(resp[i].getReview() + "," + resp[i].getSlot() + ":" + resp[i].getFaculty());
//                                    }
//                                } catch (Exception e) {
//                                    Toast.makeText(ForTeachers.this, e.getMessage() + " 1", Toast.LENGTH_SHORT).show();
//                                    lab.add(resp[i].getReview() + "," + resp[i].getSlot() + ":" + resp[i].getFaculty());
//                                }
//                            }
//                            }
//                                if(theory!=null && lab!=null)
//                                {
//                                    if ((theory.size()+lab.size()) <= 4)
//                                    {
//                                        int f=4-theory.size()-lab.size();
//                                        theory.addAll(lab);
//                                        for(int j=0;j<f;j++)
//                                        {
//                                            theory.add("");
//                                        }
//                                    }
//                                    else {
//                                        theory.addAll(lab);
//                                        try {
//                                            collec1.put(subjectCode, theory);
//                                            ArrayList<String> ac=new ArrayList<>();
//                                            ac.add("");
//                                            ac.add("");
//                                            ac.add("");
//                                            ac.add("");
//                                            collec.put(subjectCode,ac);
//                                        }
//                                        catch (Exception e)
//                                        {
//                                            Log.e("The code:",subjectCode);
//                                            Log.e("The array size is:",String.valueOf(theory.size()));
//                                        }
//                                        withchoice.add(subjectCode);
//                                    }
//                                }
//
//                        } else {
//                            theory.add("");
//                            theory.add("");
//                            theory.add("");
//                            theory.add("");
//                            collec.put(subjectCode, theory);
//                        }
//                    }
//                    if(j==Main.codes.size()-1)
//                    {
//                        progressDoalog.cancel();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Teachers>> call, Throwable t) {
//
//                    Log.i("The error is", t.getMessage());
//                    progressDoalog.cancel();
//                    finish();
//                    System.exit(0);
//
//                }
//            });
        ArrayList<String> dk=new ArrayList<>();
        dk.add("A");
        dk.add("A1");
        dk.add("A2");
        dk.add("A3");
        withchoice.add("CSE1002");
        collec1.put("CSE1002",dk);
        collec=new HashMap<>();
        collec.put("CSE1002",dk);
        numbers=new ArrayList<Teac>();
        for(int i=0;i<withchoice.size();i++)
        {
            numbers.add(new Teac( collec1.get(withchoice.get(i)),withchoice.get(i)+" "+Main.subo.get(withchoice.get(i))));
        }
        if(numbers!=null)
        {
            Log.e("the size is:",String.valueOf(numbers.size()));
        }
        adapter=new TeacAdapter(this,numbers);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
