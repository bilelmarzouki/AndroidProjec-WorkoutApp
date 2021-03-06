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

import com.squareup.picasso.Picasso;

import java.util.List;

public class ExercicesAdapter extends RecyclerView.Adapter<ExercicesAdapter.MyHolder> {

    private Context context;
    private Cursor exercices;

    public ExercicesAdapter(Context context, Cursor exercices){

        this.context = context;
        this.exercices = exercices;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater minflater = LayoutInflater.from(context);
        view = minflater.inflate(R.layout.exercices, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Log.v("Cursor1 Object Exercice", DatabaseUtils.dumpCursorToString(exercices));
        if(exercices != null && exercices.moveToFirst()) {
            Picasso.with(context)
                    .load(exercices.getString(5))
                    .into(holder.ecerciceImg);
            holder.ecerciceName.setText(exercices.getString(2));
            holder.reps.setText("Repetition: "+exercices.getString(5)+" Time");
            holder.rest1.setText("Rest: "+exercices.getString(6)+"Min");
            holder.rest2.setText("Repitition: "+exercices.getString(7)+"Time");
            holder.description.setText("Description: "+exercices.getString(3));
        }
    }

    @Override
    public int getItemCount() {
        return exercices.getCount();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView ecerciceImg;
        TextView ecerciceName, reps, rest1, rest2, description;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            ecerciceImg = itemView.findViewById(R.id.exerciceImg);
            ecerciceName = itemView.findViewById(R.id.exerciceName);
            reps = itemView.findViewById(R.id.rps);
            rest1 = itemView.findViewById(R.id.rest1);
            rest2 = itemView.findViewById(R.id.respt1);
            description = itemView.findViewById(R.id.description);

        }
    }
}
