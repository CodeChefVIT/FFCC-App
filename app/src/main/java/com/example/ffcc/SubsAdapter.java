package com.example.ffcc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

public class SubsAdapter extends RecyclerView.Adapter<SubsAdapter.ViewHolder> {
    @NonNull
    public ArrayList<Subs> numbers;
    Context con;
    public SubsAdapter(Context context,ArrayList<Subs> s)
    {
        con=context;
        numbers=s;
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        SearchableSpinner spinner;
        TextView num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner=itemView.findViewById(R.id.spinner);
            num=itemView.findViewById(R.id.num);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
    @Override
    public SubsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.spinnerlayout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubsAdapter.ViewHolder holder, final int position) {
        holder.itemView.setTag(numbers.get(position));
        holder.num.setText(numbers.get(position).getNum());
        numbers.get(position).setAdp(con);
        holder.spinner.setAdapter(numbers.get(position).getAdp());
        holder.spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int pos, long id) {
                String selected=numbers.get(position).getAb().get(pos);
                if(Main.k[position]==0)
                {
                    Toast.makeText(con, selected, Toast.LENGTH_SHORT).show();
                    Main.sel.add(selected);
                }
                else
                {

                }

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }
}
