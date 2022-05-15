package com.example.projectandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddExercice extends AppCompatActivity {

    public String imgUri;
    Spinner bodypart;
    EditText Exercicename, description, nbrerepetition, rest, nbreexercice;
    Button nextexerciceBtn;
    FloatingActionButton addexerciceBtn;
    ImageButton photo;

    ArrayList<String> exercices = new ArrayList<String>();
    ArrayList<String> exercices1 = new ArrayList<String>();

    ArrayAdapter<CharSequence> adapterr;

    DbHelper DB;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercice);

        Intent i = getIntent();
        String bodyPart = i.getStringExtra("bodyPart");
        int PersonId = i.getIntExtra("idPerson",0);
        exercices1 = i.getStringArrayListExtra("exercices1");
        if(exercices1 != null) {
            exercices.addAll(exercices1);
        }
        bodypart = findViewById(R.id.spin2);
        SpinnerAdapter();
        int position = adapterr.getPosition(bodyPart);
        bodypart.setSelection(position);





        Exercicename = findViewById(R.id.exercicename);
        description = findViewById(R.id.exercicedescription);
        nbrerepetition = findViewById(R.id.reps);
        rest = findViewById(R.id.rest1);
        nbreexercice = findViewById(R.id.nubtimes);
        nextexerciceBtn = findViewById(R.id.nextecercice);
        addexerciceBtn = findViewById(R.id.addecercices);






        photo = findViewById(R.id.choseimg1);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check run-time Permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //permission not granded, Request it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for run time permission
                        requestPermissions(permissions, PERMISSION_CODE);

                    } else {
                        //permission already Granded
                        PickImgFromGallery();

                    }
                } else {
                    //System OS id less then MARSHMELLOW
                    PickImgFromGallery();
                }
            }
        });

        nextexerciceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                exercices.add(bodypart.getSelectedItem().toString());
                exercices.add(Exercicename.getText().toString());
                exercices.add(description.getText().toString());
                exercices.add(imgUri);
                exercices.add(nbrerepetition.getText().toString());
                exercices.add(rest.getText().toString());
                exercices.add(nbreexercice.getText().toString());


                Intent i = new Intent(AddExercice.this, AddExercice.class);
                i.putExtra("bodyPart", bodypart.getSelectedItem().toString());
                i.putStringArrayListExtra("exercices1", exercices);
                i.putExtra("idPerson", PersonId);
                startActivity(i);


            }
        });


        addexerciceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB = new DbHelper(AddExercice.this);
                DB.InsertExerciceToDB(PersonId, exercices);
                Intent i = new Intent(AddExercice.this, MainActivity.class);
                startActivity(i);
            }
        });








    }


    public void SpinnerAdapter() {

        adapterr = ArrayAdapter.createFromResource(this,
                R.array.bodyPart, R.layout.spinneritem);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bodypart.setAdapter(adapterr);

    }




    private void PickImgFromGallery() {
        //intenet to pick image
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, IMAGE_PICK_CODE);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permision was granted
                    PickImgFromGallery();
                } else {
                    //permission was denied
                    Toast.makeText(this, "Permission Denied...!", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //affect img uri
            imgUri = data.getData().toString().trim();
        }
    }



}