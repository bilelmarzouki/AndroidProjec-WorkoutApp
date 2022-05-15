package com.example.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        holder.coachImg.setImageURI(Uri.parse(coaches.getString(5)));
        holder.coachName.setText(coaches.getString(1));
        holder.coachEmail.setText(coaches.getString(2));
        holder.bodyPart.setText(bodypart);
        holder.coachImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ExercicesActivity.class);
                i.putExtra("PersonId", bodypart);
                i.putExtra("Idperson", coaches.getInt(0));
                context.startActivity(i);
            }
        });

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
