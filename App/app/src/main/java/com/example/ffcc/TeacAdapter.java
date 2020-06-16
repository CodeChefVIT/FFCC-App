package com.example.ffcc;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Set;

import static com.example.ffcc.Main.credit;

public class TeacAdapter extends RecyclerView.Adapter<TeacAdapter.ViewHolder> {
    @NonNull
    public ArrayList<Teac> numbers;

    private ArrayList<String> list;
    Context con;

    public TeacAdapter(Context context,ArrayList<Teac> s)
    {
        con=context;
        numbers=s;;
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView code;
        SearchableSpinner s1;
        SearchableSpinner s2;
        SearchableSpinner s3;
        SearchableSpinner s4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            s1=itemView.findViewById(R.id.spinner1);
            code=itemView.findViewById(R.id.title);
            s2=itemView.findViewById(R.id.spinner2);
            s3=itemView.findViewById(R.id.spinner3);
            s4=itemView.findViewById(R.id.spinner4);

//            itemView.setTag(this);
//            itemView.setOnClickListener(onItemClickListener);

        }
    }

    @Override
    public TeacAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacAdapter.ViewHolder holder, final int position) {
        holder.itemView.setTag(numbers.get(position));
        holder.code.setText(numbers.get(position).getName());
        final String k=numbers.get(position).getName().substring(0,7);
        list=numbers.get(position).getN();
        ArrayAdapter<String> adapters =
                new ArrayAdapter<String>(con,  android.R.layout.simple_spinner_dropdown_item, list);
        adapters.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        holder.s1.setTitle("Select your Teacher choice");
        holder.s1.setPositiveButton("OK");
        holder.s2.setTitle("Select your Teacher choice");
        holder.s2.setPositiveButton("OK");
        holder.s3.setTitle("Select your Teacher choice");
        holder.s3.setPositiveButton("OK");
        holder.s4.setTitle("Select your Teacher choice");
        holder.s4.setPositiveButton("OK");

        holder.s1.setAdapter(adapters);
        holder.s2.setAdapter(adapters);
        holder.s3.setAdapter(adapters);
        holder.s4.setAdapter(adapters);
        holder.s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> ab=ForTeachers.collec.get(k);
                String z=list.get(position);
                ab.add("a");
                ab.add("a");
                ab.add("a");
                ab.add("a");
                if(!((ab.get(0).equals(z)) && (ab.get(1).equals(z)) && (ab.get(2).equals(z)) && (ab.get(3).equals(z))))
                {
                    ab.set(0,z);
                }
                else
                {
                    Log.e("the message is:","You have selected the same teacher again");
                    Toast.makeText(con, "You have selected the same teacher again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        holder.s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> ab=ForTeachers.collec.get(k);
                String z=list.get(position);
                if(!((ab.get(0).equals(z)) && (ab.get(1).equals(z)) && (ab.get(2).equals(z)) && (ab.get(3).equals(z))))
                {
                    ab.set(0,z);
                }
                else
                {
                    Toast.makeText(con, "You have selected the same teacher again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        holder.s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> ab=ForTeachers.collec.get(k);
                String z=list.get(position);
                if(!((ab.get(0).equals(z)) && (ab.get(1).equals(z)) && (ab.get(2).equals(z)) && (ab.get(3).equals(z))))
                {
                    ab.set(0,z);
                }
                else
                {
                    Toast.makeText(con, "You have selected the same teacher again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        holder.s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> ab=ForTeachers.collec.get(k);
                String z=list.get(position);
                if(!((ab.get(0).equals(z)) && (ab.get(1).equals(z)) && (ab.get(2).equals(z)) && (ab.get(3).equals(z))))
                {
                    ab.set(0,z);
                }
                else
                {
                    Toast.makeText(con, "You have selected the same teacher again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    @Override
    public int getItemCount() {

        if(numbers!=null){return numbers.size();}
        else{
            return 0;
        }
    }
}