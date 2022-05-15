package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Sign_inActivity extends AppCompatActivity {

    EditText mail, password;
    FloatingActionButton signinBtn;

    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(
          //      WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
         //       WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
      //  );
        setContentView(R.layout.activity_sign_in);

        mail = findViewById(R.id.email);

        password = findViewById(R.id.password);

        signinBtn = findViewById(R.id.sign_inBtn);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB = new DbHelper(Sign_inActivity.this);
                sign_in();
            }
        });

    }

    public void sign_in() {
        boolean userAvailbe = false;
        Cursor cursor = DB.getAllPerson();
        Log.d("bbbbb", String.valueOf(cursor.getCount()));
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "E-mail Or Password Worg, Or You Don't Have An Acount", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                if(cursor.getString(2).equals(mail.getText().toString()) &&
                                            cursor.getString(3).equals(password.getText().toString())) {
                    userAvailbe = true;
                    Intent i = new Intent(this, MainActivity.class);
                    i.putExtra("idPerson", cursor.getInt(0));
                    startActivity(i);
                    break;
                }
            }
        }
        if (!userAvailbe) {
            mail.setText("");
            password.setText("");
            Toast.makeText(this, "E-mail Or Password Worg, Or You Don't Have An Acount", Toast.LENGTH_SHORT).show();
        }

    }

    public void ToSignUp(View view){
        Intent i = new Intent(this, Sign_upActivity.class);
        startActivity(i);
    }
}