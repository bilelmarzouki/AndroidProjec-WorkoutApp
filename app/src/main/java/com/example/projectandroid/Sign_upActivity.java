package com.example.projectandroid;

import androidx.activity.result.contract.ActivityResultContracts;
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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Sign_upActivity extends AppCompatActivity {


    public String imgUri;
    EditText name, mail, password;
    FloatingActionButton signupBtn;
    Spinner type;
    ImageButton photo;

    DbHelper DB;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(
          //      WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
           //     WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
       // );
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);

        mail = findViewById(R.id.mailup);

        password = findViewById(R.id.passwordup);

        type = findViewById(R.id.spin1);
        SpinnerAdapter();

        photo = findViewById(R.id.choseimg);
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

        signupBtn = findViewById(R.id.sign_ipBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB = new DbHelper(Sign_upActivity.this);
                //Add Person To DataBase
                InsertPersonToDB();
                ToSignIn(view);
            }
        });

    }

    public void ToSignIn(View view) {
        Intent i = new Intent(this, Sign_inActivity.class);
        startActivity(i);
    }

    public void SpinnerAdapter() {

        ArrayAdapter<CharSequence> adapterr = ArrayAdapter.createFromResource(this,
                R.array.TypesOfPerson, R.layout.spinneritem);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type.setAdapter(adapterr);

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

    public void  InsertPersonToDB() {
        boolean mailAvailbe = false;
        Cursor cursor = DB.getAllPerson();
        Log.d("aaaaa", String.valueOf(cursor.getCount()));
        if (cursor.getCount() == 0) {
            mailAvailbe = true;
            DB.InsertPersonToDB(name.getText().toString(),
                    mail.getText().toString(),
                    password.getText().toString(),
                    type.getSelectedItem().toString(),
                    imgUri
            );
        } else {
            while (cursor.moveToNext()) {
                Log.d("aaaaa", cursor.getString(2));
                Log.d("aaaaa", mail.getText().toString());
                if (cursor.getString(2).equals(mail.getText().toString())) {
                    mailAvailbe = true;
                    Toast.makeText(this, "You've Already Have An Acount", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
        if (mailAvailbe == false) {
            DB.InsertPersonToDB(name.getText().toString(),
                    mail.getText().toString(),
                    password.getText().toString(),
                    type.getSelectedItem().toString(),
                    imgUri
            );
        }
    }
}