package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView RV1;
    FloatingActionButton addExercicesBTN;
    Body_Part_RV_Adapter adapter;
    List<Integer> images;
    List<String> bodyParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(
               // WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
              //  WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
       // );
        //getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

        //handle data coming from Sign_in activity
        Intent i = getIntent();
        int idPerson = i.getIntExtra("idPerson", 0);

        images = new ArrayList<>();
        images.add(R.drawable.shoulderspart);
        images.add(R.drawable.backparts);
        images.add(R.drawable.abspart);
        images.add(R.drawable.armpart);
        images.add(R.drawable.cardio);
        images.add(R.drawable.chestpart);
        images.add(R.drawable.fullbody);
        images.add(R.drawable.legpart);
        bodyParts = new ArrayList<>();
        bodyParts.add("SHOULDERS");
        bodyParts.add("BACK");
        bodyParts.add("ABS");
        bodyParts.add("ARMS");
        bodyParts.add("CARDIO");
        bodyParts.add("CHEST");
        bodyParts.add("FULL BODY");
        bodyParts.add("LEGS");

        addExercicesBTN = findViewById(R.id.addecercices);
        RV1 = findViewById(R.id.recyclerView1);

        adapter = new Body_Part_RV_Adapter(this, images, bodyParts, idPerson);

        RV1.setLayoutManager(new GridLayoutManager(this, 2));
        RV1.setAdapter(adapter);

        addExercicesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddExercice.class);
                i.putExtra("idPerson", idPerson);
                startActivity(i);
            }
        });






    }
}