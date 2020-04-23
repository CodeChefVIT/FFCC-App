package com.example.ffcc;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main extends AppCompatActivity {
    TextView creds ;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    static ArrayList<Subs> numbers;
    static ArrayList<String> subjects;
    static ArrayList<String> sel;
    static int k[]={0,0,0,0,0,0,0};
    static ArrayList<String> checker(ArrayList<String> ac)
    {
        Set<String> a=new HashSet<String>(ac);
        Set<String> b=new HashSet<String>(sel);
        a.removeAll(b);
        ac =new ArrayList<String>(a);
        return ac;
    }
    static void notifier(int z)
    {
        for(int i=0;i<7;i++)
        {
            if(i!=z) {
                Subs j = numbers.get(i);
                j.setAb(checker(j.getAb()));
                j.adp.notifyDataSetChanged();
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        creds=(TextView)findViewById(R.id.creds);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL) // Specify your api here
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        creds.setText("Credits Taken:0");
        sel=new ArrayList<String>();
        recyclerView=findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        subjects=new ArrayList<>();
        Call<List<Teachers>> call = api.teachers("PHY1701");
        call.enqueue(new Callback<List<Teachers>>() {
            @Override
            public void onResponse(Call<List<Teachers>> call, Response<List<Teachers>> response) {
                Toast.makeText(Main.this, response.code(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Teachers>> call, Throwable t) {

                Log.i("The error is",t.getMessage());

            }
        });


        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        subjects.add("Aditya");
        subjects.add("Aditya");
        subjects.add("Aditya");subjects.add("Aditya");


        numbers=new ArrayList<Subs>();
        numbers.add(new Subs(subjects,"Enter subject1:"));
        numbers.add(new Subs(subjects,"Enter subject2:"));
        numbers.add(new Subs(subjects,"Enter subject3:"));
        numbers.add(new Subs(subjects,"Enter subject4:"));
        numbers.add(new Subs(subjects,"Enter subject5:"));
        numbers.add(new Subs(subjects,"Enter subject6:"));
        numbers.add(new Subs(subjects,"Enter subject7:"));
        adapter=new SubsAdapter(this,numbers);
        recyclerView.setAdapter(adapter);

    }


}
