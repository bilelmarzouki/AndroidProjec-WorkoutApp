package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ExercicesActivity extends AppCompatActivity {

    RecyclerView RV3;
    ExercicesAdapter exercicesAdapter;
    List<Exercices> exercices;

    DbHelper DB;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices);

        Intent i = getIntent();
        String bodypart = i.getStringExtra("bodypart");
        int coacheId = i.getIntExtra("Idcoache", 0);



        DB = new DbHelper(this);
        cursor = DB.getExerciceByBodyPartAndCoache(bodypart, coacheId);
        Log.v("Cursor1 Object Exercice", DatabaseUtils.dumpCursorToString(cursor));


        /*exercices = new ArrayList<>();
        exercices.add(new Exercices("Push-up", "15 Reps", "Rest 1 Min", "5 Min Rest", R.drawable.declinepushups));
        exercices.add(new Exercices("Diamond Push-up", "15 Reps", "Rest 1 Min", "5 Min Rest", R.drawable.triceppushups));
        exercices.add(new Exercices("Incline Push-up", "15 Reps", "Rest 1 Min", "5 Min Rest", R.drawable.clinepushup));
        exercices.add(new Exercices("Decline Push-up", "15 Reps", "Rest 1 Min", "5 Min Rest", R.drawable.classicpushup));
*/
        RV3 = findViewById(R.id.recyclerView3);
        exercicesAdapter = new ExercicesAdapter(this, cursor);

        RV3.setLayoutManager(new LinearLayoutManager(this));
        RV3.setAdapter(exercicesAdapter);

    }
}