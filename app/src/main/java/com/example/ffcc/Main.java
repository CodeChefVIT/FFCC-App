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
    static String choice;
    static ArrayList<Subs> numbers;
    static ArrayList<String> subjects;
    static ArrayList<String> sel;
    static int nod;
    static int[] k = {0, 0, 0, 0, 0, 0, 0};
    static ArrayList<String> checker(ArrayList<String> ac)
    {
        Set<String> a=new HashSet<String>(ac);
        Set<String> b=new HashSet<String>(sel);
        a.removeAll(b);
        ac =new ArrayList<String>(a);
        return ac;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        creds= findViewById(R.id.creds);
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
        Call<List<Subjects>> call = api.makel();
        call.enqueue(new Callback<List<Subjects>>() {
            @Override
            public void onResponse(Call<List<Subjects>> call, Response<List<Subjects>> response) {
                if(response.code()==200)
                {
                    Subjects[] resp=response.body().toArray(new Subjects[0]);
                        Toast.makeText(Main.this, "Response captured", Toast.LENGTH_SHORT).show();
                        for(int i=0;i<resp.length;i++)
                        {
                            Toast.makeText(Main.this, resp[i].getCode()+" "+resp[i].getTitle(), Toast.LENGTH_SHORT).show();
                        }
                }
            }

            @Override
            public void onFailure(Call<List<Subjects>> call, Throwable t) {

                Log.i("The error is",t.getMessage());

            }
        });


        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        numbers=new ArrayList<Subs>();
        for(int i=1;i<=nod;i++) {
            numbers.add(new Subs( "Select subject "+i+":"));
        }
        adapter=new SubsAdapter(this,numbers);
        recyclerView.setAdapter(adapter);

    }


}
