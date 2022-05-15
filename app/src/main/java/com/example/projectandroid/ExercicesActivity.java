package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ExercicesActivity extends AppCompatActivity {

    RecyclerView RV3;
    ExercicesAdapter exercicesAdapter;
    List<Exercices> exercices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices);


        exercices = new ArrayList<>();
        exercices.add(new Exercices("Push-up", "15 Reps", "Rest 1 Min", "5 Min Rest", R.drawable.declinepushups));
        exercices.add(new Exercices("Diamond Push-up", "15 Reps", "Rest 1 Min", "5 Min Rest", R.drawable.triceppushups));
        exercices.add(new Exercices("Incline Push-up", "15 Reps", "Rest 1 Min", "5 Min Rest", R.drawable.clinepushup));
        exercices.add(new Exercices("Decline Push-up", "15 Reps", "Rest 1 Min", "5 Min Rest", R.drawable.classicpushup));

        RV3 = findViewById(R.id.recyclerView3);
        exercicesAdapter = new ExercicesAdapter(this, exercices);

        RV3.setLayoutManager(new LinearLayoutManager(this));
        RV3.setAdapter(exercicesAdapter);

    }
}