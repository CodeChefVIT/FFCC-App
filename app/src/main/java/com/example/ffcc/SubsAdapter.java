package com.example.ffcc;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
        TextView code;
        TextView name;
        ImageButton img;
        ImageView cros;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            code=itemView.findViewById(R.id.code);
            img=itemView.findViewById(R.id.img);
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
        holder.name.setText(numbers.get(position).getName());
        holder.code.setText(numbers.get(position).getCode());
        holder.img.setImageResource(R.drawable.ic_cross);
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }
}
