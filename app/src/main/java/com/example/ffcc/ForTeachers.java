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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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
    TextView textView ;
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
    int th;
    ArrayList<String> contact;
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
    void PrintToFile()
    {
        try{

            File file = new File("Teac.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println(Main.subcodes.get(Main.noa));
            if(contact!=null)
            {
                if(contact.size()<=4)
                {
                    for(int i=0;i<contact.size();i++)
                    {
                        pr.println(contact.get(i).substring(2));
                    }
                    for(int j=contact.size();j<4;j++)
                    {
                        pr.println("");
                    }
                }
                else
                {
                    for(int i=0;i<4;i++)
                    {
                        pr.println(numbers.get(i).getCode()+":"+numbers.get(i).getName());
                    }
                }
            }
            else
            {
                for(int i=0;i<4;i++)
                {
                    pr.println("");
                }
            }

            pr.close();
            br.close();
            fr.close();

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void startnext(View v)
    {
        Main.noa++;
        if(Main.noa<Main.codes.size()) {
            Intent I = new Intent(ForTeachers.this, ForTeachers.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            if (true) {
                if(codes.size()>4)
                {
                    Toast.makeText(this, "Select At most 4 teacher preference", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Main.choice.put(Main.subcodes.get(Main.noa-1),codes);
                    PrintToFile();
                    I.putExtra("Cho", th);
                    startActivity(I, options.toBundle());
                }
            } else {
                Toast.makeText(this, "Please select at least 4 subjects", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Intent I=new Intent(ForTeachers.this, com.example.ffcc.List.class);
            PrintToFile();
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            //Main.choice.put(Main.subcodes.get(Main.noa-1),codes);
            startActivity(I,options.toBundle());
        }
    }
    public void starter()
    {
        Main.noa++;
        if(Main.noa<Main.codes.size()) {
            Intent I = new Intent(ForTeachers.this, ForTeachers.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            if (true) {
                if(codes.size()>4)
                {
                    Toast.makeText(this, "Select At most 4 teacher preference", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Main.choice.put(Main.subcodes.get(Main.noa-1),codes);
                    PrintToFile();
                    I.putExtra("Cho", th);
                    startActivity(I, options.toBundle());
                }
            } else {
                Toast.makeText(this, "Please select at least 4 subjects", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Intent I=new Intent(ForTeachers.this,com.example.ffcc.List.class);
            PrintToFile();
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

        Intent I=getIntent();
            th = I.getIntExtra("Cho", 4);

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
                    case R.id.timeTable:Intent I2=new Intent(getApplicationContext(),com.example.ffcc.List.class);
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
        next=findViewById(R.id.next);
        spinner=findViewById(R.id.spinner);
        String subjectCode=Main.subcodes.get(Main.noa);
        textView.setText(subjectCode+"-"+Main.subo.get(subjectCode));
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
                    if(resp!=null) {
                        if(resp.length<=4)
                        {
                            for (int i = 0; i < resp.length; i++)
                            {
                                String o = resp[i].getFaculty();
                                if (checker1(o) == true) {
                                    try {
                                        if (resp[i].getFlag().equals(String.valueOf(th)) || (th == 4)) {
                                            contacts.add(resp[i].getReview() + "," + resp[i].getSlot() + ":" + resp[i].getFaculty());
                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(ForTeachers.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        contacts.add(resp[i].getReview() + "," + resp[i].getSlot() + ":" + resp[i].getFaculty());
                                    }

                                }
                            }
                            starter();
                            progressDoalog.cancel();
                        }
                        else{
                            th=1;
                        for (int i = 0; i < resp.length; i++)
                        {
                            String o = resp[i].getFaculty();
                            if (checker1(o) == true) {
                                try {
                                    if (resp[i].getFlag().equals("1") || (th == 4)) {
                                        contacts.add(resp[i].getReview() + "," + resp[i].getSlot() + ":" + resp[i].getFaculty());
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(ForTeachers.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    contacts.add(resp[i].getReview() + "," + resp[i].getSlot() + ":" + resp[i].getFaculty());
                                }

                            }
                        }
                        progressDoalog.cancel();
                        }
                    }
                    else{
                            starter();
                            progressDoalog.cancel();
                        }
                }
            }

            @Override
            public void onFailure(Call<List<Teachers>> call, Throwable t) {

                Log.i("The error is",t.getMessage());
                progressDoalog.cancel();
                System.exit(0);

            }
        });
        if(contacts==null)
        {
            contact=new ArrayList<>();
        }
        else {
            contact = contacts;
        }
        numbers=new ArrayList<Subs>();
        adapter=new SubsAdapter(this,numbers);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                String k=contacts.get(position);
                boolean z=checker(k);
                if(z==true)
                {
                    numbers.add(new Subs(contacts.get(position).substring(2,contacts.get(position).indexOf(':')), contacts.get(position).substring(contacts.get(position).indexOf(':')+1)));
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
    }
}
