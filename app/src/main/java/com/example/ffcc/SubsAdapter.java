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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class SubsAdapter extends RecyclerView.Adapter<SubsAdapter.ViewHolder> {
    @NonNull
    public ArrayList<Subs> numbers;
    private View.OnClickListener onItemClickListener;
    Context con;
    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            code=itemView.findViewById(R.id.code);
            img=itemView.findViewById(R.id.img);
//            itemView.setTag(this);
//            itemView.setOnClickListener(onItemClickListener);

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

        holder.img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // remove your item from data base
                String o=numbers.get(position).getCode();
                Toast.makeText(con, String.valueOf(position), Toast.LENGTH_SHORT).show();
                int k=Main.credit.get(o);
                Main.tot-=k;
                Main.creds.setText("Credits Taken:"+Main.tot);
                Main.codes.remove(o);
                numbers.remove(position);  // remove the item from list
                notifyDataSetChanged(); // notify the adapter about the removed item
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
