package com.example.ffcc;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main extends AppCompatActivity {
    static TextView creds ;
    static int noa;
    Button next;
    static int tot;
    static RecyclerView recyclerView;
    Handler handle;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SearchableSpinner spinner;
    ProgressDialog progressDoalog ;
    static HashMap<String,String> subo;
    static int theory_choice;//Choice of slots
    ArrayList<String> contacts;//Subjects list for the spinner
    static ArrayList<Subs> numbers;//Subjects in the form of class
    static Set<String> codes;//set to store all the courses selected
    static HashMap<String,Set<String>> choice;//For teacher choice with subject code as our key
    static ArrayList<String> subcodes;//Array form of codes
    static HashMap<String,Integer> credit;//credit as value and code as key
    HashMap<String,ArrayList<String>> spin;//Code alphabet part as key and the list of subjects with the same code as the value
    TextView textView2,editText;
    ImageView imageView,imageView2;
    AlertDialog.Builder builde;
    AlertDialog h;
    //Response of our backend
    public void setAnimationEntry() {
        if (Build.VERSION.SDK_INT > 20) {
            Explode explode = new Explode();
            explode.setDuration(1000);
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.TOP);
            slide.setDuration(900);
            slide.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
        }
    }
    boolean checker(String k)
    {
        int a=codes.size();
        codes.add(k);
        return codes.size() > a;
    }
    public void nextAc()
    {
        if((tot>=16)&&(tot<=27)){
        noa=0;
        subcodes=new ArrayList<>(codes);
        Intent I=new Intent(Main.this,ForTeachers.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        if(codes.size()>=4) {
            I.putExtra("Cho",theory_choice);
            startActivity(I,options.toBundle());
        }
        else{
            Toast.makeText(this, "Please select at least 4 subjects", Toast.LENGTH_SHORT).show();
        }}
        else if(tot<16)
        {
            Toast.makeText(this, "Total credits is below 16.Please add more!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Total credits is above 27.Please remove some!!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected  void onStop() {

        super.onStop();
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
        SharedPreferences settings = getSharedPreferences("Data",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.putInt("Credits",tot);
        editor.putInt("Choice",theory_choice);
//        Gson gson = new Gson();
//        String json = gson.toJson(numbers);
//        editor.putString("MyObject", json);
        if(contacts.size()==0)
        {
            editor.putStringSet("Subject List",new HashSet<String>());
        }
        else {
            editor.putStringSet("Subject List", new HashSet<String>(contacts));
        }

        if(codes!=null) {
            editor.putStringSet("Codes", codes);
            Toast.makeText(this, String.valueOf(codes.size()), Toast.LENGTH_SHORT).show();
        }
        else
        {
            editor.putStringSet("Codes", new HashSet<String>());
        }
        editor.commit();

    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        if(isChangingConfigurations())
        {
        }
        else {
            SharedPreferences preferences = getSharedPreferences("Data", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        }
        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show();
    }
    int n=1;
    @Override
    protected  void onResume() {

        super.onResume();
        if((progressDoalog!=null)&&(n==0))
        {
            progressDoalog.dismiss();
        }

        
        SharedPreferences settings = getSharedPreferences("Data",MODE_PRIVATE);
        tot=settings.getInt("Credits",0);
        theory_choice=settings.getInt("Choice",0);
        Set<String> strings = new HashSet<>();
        contacts=new ArrayList<>(settings.getStringSet("Subjects list",strings));
        subcodes=new ArrayList<>(settings.getStringSet("Codes",strings));
        codes=new HashSet<String>(subcodes) ;
        if(contacts!=null){
        for(int i=0;i<contacts.size();i++)
        {
           subo.put(contacts.get(i).substring(0,7),contacts.get(i).substring(8));
        }}
        creds.setText("Credits Taken:"+tot);
        credit=MainActivity.cre;
        ArrayAdapter<String> adapters =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, contacts);
        adapters.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapters);
        numbers.clear();
        Toast.makeText(this, String.valueOf(subo.size()), Toast.LENGTH_SHORT).show();
        for(int i=0;i<codes.size();i++)
        {
            numbers.add(new Subs(subcodes.get(i),subo.get(subcodes.get(i))));
        }
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new SubsAdapter(this,numbers);
        recyclerView.setAdapter(adapter);
        adapters.notifyDataSetChanged();


    }
    ArrayAdapter<String> adapters;
    public void ase(View V)
    {
        theory_choice=0;
        h.cancel();
    }
    public void asm(View V)
    {
        theory_choice=1;
        h.cancel();
    }
//    protected void onSaveInstanceState (Bundle outState) {
//
//        super.onSaveInstanceState(outState);
//        outState.putInt("Credits",tot);
//        outState.putInt("Choice",theory_choice);
//        outState.putStringArrayList("Subjects list",contacts);
//        outState.putStringArrayList("Codes",subcodes);
//
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimationEntry();
        theory_choice=4;
        setContentView(R.layout.activity_main2);
        textView2=findViewById(R.id.textView2);
        editText=findViewById(R.id.editText);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.main2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.forTeachers:if((tot<16 || tot >27)||(codes.size()<4))
                    {
                        Toast.makeText(Main.this, "Please fill the subjects first", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                        else{
                        nextAc();
                    return true;}
                    case R.id.main2:return true;
                    case R.id.timeTable:Intent I2=new Intent(getApplicationContext(),TimeTable.class);
                        startActivity(I2);
                        return true;

                }
                return false;
            }
        });

        // create an alert builder
        builde = new AlertDialog.Builder(this);
//        builde.setTitle("Select your choice for theory subjects");
//        builde.setCancelable(false);
//        builde.setPositiveButton("MORNING", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                theory_choice="MORN";
//            }
//        }).setNegativeButton("EVENING", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                theory_choice="EVEN";
//            }
//        });
//        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        builde.setView(customLayout);
        h=builde.create();
        h.show();


        codes=new HashSet<String>();
        subo=new HashMap<>();
        creds= findViewById(R.id.creds);
        spin =new HashMap<>();
        recyclerView=findViewById(R.id.rec);
        spinner=findViewById(R.id.spinner);
        contacts = new ArrayList<>();
        next=findViewById(R.id.send);
        recyclerView.setHasFixedSize(true);
        credit=new HashMap<>();

        ArrayAdapter<String> adapters =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, contacts);
        adapters.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapters);
        numbers=new ArrayList<Subs>();
        adapter=new SubsAdapter(this,numbers);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new SubsAdapter(this,numbers);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int id=v.getId();
                Toast.makeText(Main.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


//        if(savedInstanceState==null)
//        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL) // Specify your api here
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api api = retrofit.create(Api.class);

            creds.setText("Credits Taken:0");


            handle = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progressDoalog.incrementProgressBy(1);
                }
            };
            progressDoalog = new ProgressDialog(Main.this);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Its loading....");
            progressDoalog.setTitle("Downloading data ");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.setCancelable(false);
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
            Call<List<Subjects>> call = api.makel();
            call.enqueue(new Callback<List<Subjects>>() {
                @Override
                public void onResponse(Call<List<Subjects>> call, Response<List<Subjects>> response) {
                    if(response.code()==200)
                    {
                        Subjects[] resp=response.body().toArray(new Subjects[0]);
                        MainActivity.respo=resp;
                        String f="ARC";
                        ArrayList<String> ff=new ArrayList<>();
                        for(int i=0;i<resp.length;i++)
                        {
                            contacts.add(resp[i].getCode()+"  "+resp[i].getTitle());
                            subo.put(resp[i].getCode(),resp[i].getTitle());
                            credit.put(resp[i].getCode(),resp[i].getCredits());
                            MainActivity.cre.put(resp[i].getCode(),resp[i].getCredits());
                            if(resp[i].getCode().substring(0,3).equals(f)==false)
                            {
                                spin.put(f,ff);
                                ff.clear();
                                f=resp[i].getCode().substring(0,3);
                            }
                            else
                            {
                                ff.add(resp[i].getCode()+"  "+resp[i].getTitle());
                            }

                        }
                        final Handler hand= new Handler();hand.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDoalog.cancel();
                            Toast.makeText(Main.this, "Let's Go!!", Toast.LENGTH_SHORT).show();
                            n=0;
                            //Do something after 100ms
                        }
                    }, 3000);

                    }
                }

                @Override
                public void onFailure(Call<List<Subjects>> call, Throwable t) {

                    Log.i("The error is",t.getMessage());
                    progressDoalog.cancel();
                    System.exit(0);

                }
            });
            handle.sendMessage(handle.obtainMessage());
//        }
//        else{
//            Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();
//            tot=savedInstanceState.getInt("Credits");
//            if(progressDoalog!=null) {
//                progressDoalog.dismiss();
//            }
//        theory_choice=savedInstanceState.getInt("Choice");
//        contacts=savedInstanceState.getStringArrayList("Subjects list");
//        subcodes=savedInstanceState.getStringArrayList("Codes");
//        codes=new HashSet<String>(subcodes) ;
//        creds.setText("Credits Taken:"+tot);
//        credit=MainActivity.cre;
//        adapter.notifyDataSetChanged();
//        }


        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                String k=contacts.get(position).substring(0, contacts.get(position).indexOf(' '));
                boolean z=checker(k);
                if(z==true) {
                    numbers.add(new Subs(contacts.get(position).substring(0, contacts.get(position).indexOf(' ')), contacts.get(position).substring(contacts.get(position).indexOf(' ') + 1)));
                    String l=creds.getText().toString().substring(14);
                    try {
                        int a = Integer.parseInt(l) + credit.get(contacts.get(position).substring(0, contacts.get(position).indexOf(' ')));
                        tot=a;
                        creds.setText("Credits Taken:"+ a);
                    }
                    catch (Exception e)
                    {

                    }

                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(Main.this, "You have selected the same subject again!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

  }


}
