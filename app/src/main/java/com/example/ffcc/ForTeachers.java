package com.example.ffcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForTeachers extends AppCompatActivity {
    TextView creds,textView ;
    RecyclerView recyclerView;
    Handler handle;
    Button next;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SearchableSpinner spinner;
    static ProgressDialog progressDoalog ;
    static ArrayList<Subs> numbers;
    Set<String> codes;
    Set<String>fac;
    public void setAnimationEntry() {
        if (Build.VERSION.SDK_INT > 20) {
            Explode explode = new Explode();
            explode.setDuration(1000);
            explode.setInterpolator(new BounceInterpolator());
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.TOP);
            slide.setDuration(900);
            slide.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(explode);
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
    public void startnext(View v)
    {
        Main.noa++;
        if(Main.noa<Main.codes.size()) {
            Intent I = new Intent(ForTeachers.this, ForTeachers.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            if (true) {
                //Main.choice.put(Main.subcodes.get(Main.noa-1),codes);
                startActivity(I,options.toBundle());
            } else {
                Toast.makeText(this, "Please select at least 4 subjects", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Intent I=new Intent(ForTeachers.this,TimeTable.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            //Main.choice.put(Main.subcodes.get(Main.noa-1),codes);
            startActivity(I,options.toBundle());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimationEntry();
        setContentView(R.layout.activity_for_teachers);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.main2);
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
        creds= findViewById(R.id.count);
        textView=findViewById(R.id.textView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL) // Specify your api here
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        next=findViewById(R.id.next);
        spinner=findViewById(R.id.spinner);
        String subjectCode=Main.subcodes.get(Main.noa);
        textView.setText(subjectCode+"-"+Main.subo.get(subjectCode));
        creds.setText("Page "+(Main.noa+1)+" of "+Main.subcodes.size());
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

        handle.sendMessage(handle.obtainMessage());
        ArrayAdapter<String> adapters =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, contacts);
        adapters.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapters);

        Call<List<Teachers>> call = api.teachers(subjectCode);
        call.enqueue(new Callback<List<Teachers>>() {
            @Override
            public void onResponse(Call<List<Teachers>> call, Response<List<Teachers>> response) {
                if(response.code()==200)
                {
                    Teachers[] resp=response.body().toArray(new Teachers[0]);
                    Set<String> so=new HashSet<>();
                    Toast.makeText(ForTeachers.this, "Let's Go!!", Toast.LENGTH_SHORT).show();
                    for(int i=0;i<resp.length;i++)
                    {
                        String o=resp[i].getFaculty();
                        if(checker1(o)==true) {
                            contacts.add(resp[i].getFaculty());
                        }
                    }
                    progressDoalog.cancel();
                }
            }

            @Override
            public void onFailure(Call<List<Teachers>> call, Throwable t) {

                Log.i("The error is",t.getMessage());
                progressDoalog.cancel();
                System.exit(0);

            }
        });
        numbers=new ArrayList<Subs>();
        adapter=new SubsAdapter(this,numbers);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                String k=contacts.get(position);
                boolean z=checker(k);
                if(z==true)
                {
                    numbers.add(new Subs("", contacts.get(position)));
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(ForTeachers.this, "You have selected the same teacher again!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new SubsAdapter(this,numbers);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "FFCC", Toast.LENGTH_SHORT).show();
    }
}
