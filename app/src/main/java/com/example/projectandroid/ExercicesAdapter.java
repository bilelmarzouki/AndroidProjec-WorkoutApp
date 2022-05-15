package com.example.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExercicesAdapter extends RecyclerView.Adapter<ExercicesAdapter.MyHolder> {

    private Context context;
    private List<Exercices> exercices;

    public ExercicesAdapter(Context context, List<Exercices> exercices){

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

        holder.ecerciceImg.setImageResource(exercices.get(position).getExerciceimg());
        holder.ecerciceName.setText(exercices.get(position).getExerciename());
        holder.reps.setText(exercices.get(position).getReps());
        holder.rest1.setText(exercices.get(position).getRest1());
        holder.rest2.setText(exercices.get(position).getRest2());

    }

    @Override
    public int getItemCount() {
        return exercices.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView ecerciceImg;
        TextView ecerciceName, reps, rest1, rest2;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            ecerciceImg = itemView.findViewById(R.id.exerciceImg);
            ecerciceName = itemView.findViewById(R.id.exerciceName);
            reps = itemView.findViewById(R.id.rps);
            rest1 = itemView.findViewById(R.id.rest1);
            rest2 = itemView.findViewById(R.id.rest2);

        }
    }
}
