package com.example.projectandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private Context context;

    public DbHelper( Context context) {
        super(context, "AndroidProjectDB", null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create table Person (id integer primary key AUTOINCREMENT, name text, mail text, password text, type text, photo text)");
        DB.execSQL("create table Exercices (id integer primary key AUTOINCREMENT, bodypart text, name text, description text, photo text, reps text, rest1 text, nubtimes text, personId integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("drop table if exists Person");
        DB.execSQL("drop table if exists Exercices");

        onCreate(DB);

    }

    public void InsertPersonToDB(String name, String mail, String password, String type, String photo) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues value =new ContentValues();
        value.put("name", name);
        value.put("mail", mail);
        value.put("password", password);
        value.put("type", type);
        value.put("photo", photo);

        long result = DB.insert("Person", null, value);
        if(result==-1){
            Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "added Succsesfully", Toast.LENGTH_LONG).show();
        }

    }

    public Cursor getAllPerson() {

        String query = "select * from Person";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = null;
        if (DB != null) {
            cursor = DB.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getPersonById(int id) {

        String query = "SELECT * FROM Person WHERE TRIM(id) = '"+String.valueOf(id).trim()+"'";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = null;
        if (DB != null) {
            cursor = DB.rawQuery(query, null);
        }

        return cursor;
    }


    public void InsertExerciceToDB(int idPerson, List<String> exercices) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues value =new ContentValues();
        for(int i =1; i< exercices.size(); i++) {
            value.put("bodypart", exercices.get(0));
            exercices.remove(0);
            value.put("name", exercices.get(0));
            exercices.remove(0);
            value.put("description", exercices.get(0));
            exercices.remove(0);
            value.put("photo", exercices.get(0));
            exercices.remove(0);
            value.put("reps", exercices.get(0));
            exercices.remove(0);
            value.put("rest1", exercices.get(0));
            exercices.remove(0);
            value.put("nubtimes", exercices.get(0));
            exercices.remove(0);
            value.put("personId", idPerson);
            long result = DB.insert("Exercices", null, value);
            if(result==-1){
                Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context, "added Succsesfully", Toast.LENGTH_LONG).show();
            }
        }
    }

    public Cursor getAllExercices() {

        String query = "select * from Exercices";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = null;
        if (DB != null) {
            cursor = DB.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getExerciceByBodyPart(String bodypart) {

        String query = "SELECT * FROM Exercices WHERE TRIM(bodypart) = '"+bodypart.trim()+"'";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = null;
        if (DB != null) {
            cursor = DB.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getExerciceByBodyPartAndCoache(String bodypart, int id) {

        String query = "SELECT * FROM Exercices WHERE TRIM(bodypart) = '"+bodypart.trim()+"' AND TRIM(id) = '"+id+"'";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = null;
        if (DB != null) {
            cursor = DB.rawQuery(query, null);
        }

        return cursor;
    }

}
