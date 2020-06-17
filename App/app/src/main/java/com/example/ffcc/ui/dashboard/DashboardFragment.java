package com.example.ffcc.ui.dashboard;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ffcc.ForTeachers;
import com.example.ffcc.Main;
import com.example.ffcc.R;
import com.example.ffcc.Subs;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Set;

public class DashboardFragment extends Fragment {
    TextView textView ;
    RecyclerView recyclerView;
    Handler handle;
    Button next;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SearchableSpinner spinner;
    static ProgressDialog progressDoalog ;
    static ArrayList<Subs> numbers;
    static Set<String> codes;
    Set<String>fac;
    int th;
    ArrayList<String> contact;
    String f;

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
//    void PrintToFile()
//    {
//        try{
//
//            SQLiteDatabase my=this.openOrCreateDatabase("TT",MODE_PRIVATE,null);
//            my.execSQL("CREATE TABLE IF NOT EXISTS TT (code TEXT,ch TEXT);");
//            String f1="";
//            if(contact!=null)
//            {
//                if(contact.size()<=4)
//                {
//                    for(int i=0;i<contact.size();i++)
//                    {
//                        f1=f1+contact.get(i).substring(2)+"*";
//                    }
//                    for(int j=contact.size();j<4;j++)
//                    {
//                        f1+="*";
//
//                    }
//                    String z=textView.getText().toString();
//                    ContentValues value=new ContentValues();
//                    value.put("code",z);
//                    value.put("ch",f1);
//                    my.insert("TT"," ",value);
////                    my.execSQL("INSERT INTO TT (code,ch) VALUES ("+z+","+f1+");");
//                }
//                else
//                {
//                    for(int i=0;i<4;i++)
//                    {
//                        f1+=numbers.get(i).getCode()+":"+numbers.get(i).getName();
//
//                    }
//                    String z=textView.getText().toString();
//                    ContentValues value=new ContentValues();
//                    value.put("code",z);
//                    value.put("ch",f1);
//                    my.insert("TT"," ",value);
////                    my.execSQL("INSERT INTO TT (code,ch) VALUES ("+z+","+f1+");");
//                }
//            }
//            else
//            {
//                for(int i=0;i<4;i++)
//                {
//                    f1+="*";
//
//                }
//                String z=textView.getText().toString();
//                ContentValues value=new ContentValues();
//                value.put("code",z);
//                value.put("ch",f1);
//                my.insert("TT"," ",value);
////                my.execSQL("INSERT INTO TT (code,ch) VALUES ("+z+","+f1+");");
//            }
//
////            pr.close();
////            br.close();
////            fr.close();
//
//        }
//        catch (Exception e1)
//        {
//            Log.e("The error is:",e1.getMessage());
//        }
//    }
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        return root;
    }
}