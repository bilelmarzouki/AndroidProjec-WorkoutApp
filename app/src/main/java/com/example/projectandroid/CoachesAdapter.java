package com.example.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class CoachesAdapter extends RecyclerView.Adapter<CoachesAdapter.MyHolder> {

    private Context context;
    private Cursor coaches;
    private Cursor exercices;
    private int personId;
    private String bodypart;

    public CoachesAdapter(Context context, Cursor coaches, Cursor exercices, int personId, String bodypart){

        this.context = context;
        this.coaches = coaches;
        this.exercices = exercices;
        this.personId = personId;
        this.bodypart = bodypart;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater minflater = LayoutInflater.from(context);
        view = minflater.inflate(R.layout.coaches, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {



        // add this line because without it i have error index -1 requested and size 1
        if(coaches != null && coaches.moveToFirst()) {
            Picasso.with(context)
                    .load(coaches.getString(5))
                    .into(holder.coachImg);
            holder.coachName.setText(coaches.getString(1));
            holder.coachEmail.setText(coaches.getString(2));
            holder.bodyPart.setText(bodypart);
            holder.coachImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ExercicesActivity.class);
                    i.putExtra("bodypart", bodypart);
                    i.putExtra("Idcoache", coaches.getInt(0));
                    context.startActivity(i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return coaches.getCount();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView coachImg;
        TextView coachName, coachEmail, bodyPart, difficulty;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            coachImg = itemView.findViewById(R.id.coachImg);
            coachName = itemView.findViewById(R.id.coachName);
            coachEmail = itemView.findViewById(R.id.coachEmail);
            bodyPart = itemView.findViewById(R.id.bodypart1);
            difficulty = itemView.findViewById(R.id.complexity);

        }
    }
}
