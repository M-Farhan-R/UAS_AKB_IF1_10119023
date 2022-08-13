package com.example.uasakbif110119023.db;
/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
tanggal : 12/08/2022
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    //Database
    public static final String DB_NAME = "CatatanHarian";
    public static final int DB_VERSION = 1;

    //Table name
    public static final String TABLE_NAME = "Catatan";

    //Table columns
    public static final String ID = "id";
    public static final String SUBJECT = "judul";
    public static final String DESC = "deskripsi";
    public static final String DATE = "date";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SUBJECT + " TEXT, "
                + DESC + " TEXT, "+ DATE +" TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;

        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public void insertData(String title, String desc, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SUBJECT, title);
        cv.put(DESC, desc);
        cv.put(DATE, date);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(String id, String title, String desc, String date){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SUBJECT, title);
        cv.put(DESC, desc);
        cv.put(DATE, date);
        long result = db.update(TABLE_NAME, cv, "id=?",new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(String id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, "id=?", new String[]{id});
    }
}
