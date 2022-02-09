package com.example.triviagame;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Members> members;

    public CustomAdapter(Context context, ArrayList members) {
        this.context = context;
        this.members = members;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        int image = Integer.parseInt(String.valueOf(members.get(position).image));

        if(image == 1 )
            holder.imageView.setBackgroundResource(R.drawable.people4);
        else if (image == 2)
            holder.imageView.setBackgroundResource(R.drawable.people1);
        else if (image == 3)
            holder.imageView.setBackgroundResource(R.drawable.people2);
        else if (image == 4)
            holder.imageView.setBackgroundResource(R.drawable.people3);
        else if (image == 5)
            holder.imageView.setBackgroundResource(R.drawable.people5);
        else if (image == 6)
            holder.imageView.setBackgroundResource(R.drawable.people6);

        holder.name.setText(String.valueOf(members.get(position).name));
        holder.username.setText(String.valueOf(members.get(position).username));
        holder.score.setText(String.valueOf(members.get(position).score));
        if (position < 3)
            holder.score.setTextColor(Color.GREEN);
        else
            holder.score.setTextColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, score, username;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.avatar_image_recyclerView);
            name = itemView.findViewById(R.id.name_recyclerview_layout);
            score = itemView.findViewById(R.id.score_recyclerview_layout);
            username = itemView.findViewById(R.id.username_recyclerview_layout);
        }
    }
}
