package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CoachesActivity extends AppCompatActivity {

    RecyclerView RV2;
    CoachesAdapter coachAdapter;
    List<Person> coaches = new ArrayList<>();

    DbHelper DB;
    Cursor cursorEx, cursorCoa, cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coaches);

        Intent i = getIntent();
        String bodypart = i.getStringExtra("bodyPart");
        int PerdonId = i.getIntExtra("idPerson", 0);



        //add this coaches from database with getallperson method;
        //when activity created, should we have two cursor, one for the coaches and other for the exercices of that coach
        //i thnik it gonna be two methods one after one(getcoaches --> getexercices) and we pass the return of
        //getcoach which is name of something else to the getexercices method to find all exercices of that specific coach
        //we returned from getcoach method

        DB = new DbHelper(this);
        cursorEx = DB.getExerciceByBodyPart(bodypart);
        cursorCoa = getAllCoachesWithThisBodyPart();


        /*coaches.add(new Person("David", "David123@gmail.com", "Coach", R.drawable.coach1));
        coaches.add(new Person("Sam", "Sam123@gmail.com", "Coach", R.drawable.coach2));
        coaches.add(new Person("Ahmed", "Ahmed123@gmail.com", "Coach", R.drawable.coach3));
        coaches.add(new Person("Mouhamed", "Mouhamed123@gmail.com", "Coach", R.drawable.coach4));
        coaches.add(new Person("Daniel", "Daniel123@gmail.com", "Coach", R.drawable.coach5));
        coaches.add(new Person("Jhon", "Jhon123@gmail.com", "Coach", R.drawable.coach6));
        coaches.add(new Person("Gerard", "Gerard123@gmail.com", "Coach", R.drawable.coach7));
        coaches.add(new Person("Amine", "Amine123@gmail.com", "Coach", R.drawable.coach8));*/

        RV2 = findViewById(R.id.recyclerView2);
        coachAdapter = new CoachesAdapter(this, cursorCoa, cursorEx, PerdonId, bodypart);

        RV2.setLayoutManager(new GridLayoutManager(this, 2));
        RV2.setAdapter(coachAdapter);





    }




    public Cursor getAllCoachesWithThisBodyPart() {

        int i = 0;
        boolean availble= false;





        while (cursorEx.moveToNext()) {
            int CoachesIds[] = new int[0];
            if(cursorEx.isFirst()) {
                cursorCoa = DB.getPersonById(cursorEx.getInt(8));
                CoachesIds[i]= cursorEx.getInt(8);
            }else {
                for (int j = 0; j < CoachesIds.length; j++) {
                    if (CoachesIds[j] == cursorEx.getInt(8)) {
                        availble = true;
                        break;
                    }
                }
                if (availble= false){
                    cursor = DB.getPersonById(cursorEx.getInt(8));
                    MatrixCursor matrixCursor = new MatrixCursor(new String[] {"id", "name", "mail", "password", "type", "photo"});

                    matrixCursor.addRow(new String[] { String.valueOf(cursor.getInt(0)),
                                                        cursor.getString(1),
                                                            cursor.getString(2),
                                                                cursor.getString(3),
                                                                    cursor.getString(4),
                                                                        cursor.getString(5) });
                    MergeCursor mergeCursor = new MergeCursor(new Cursor[] { matrixCursor, cursorCoa });
                    CoachesIds[i]= cursorEx.getInt(8);
                }
            }
            i++;

        }

        return cursorCoa;
    }






}