package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Accounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table auth_user(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, first_name TEXT, middle_name TEXT, last_name TEXT, email TEXT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public Boolean insertuserdata(String first_name, String middle_name, String last_name, String email, String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", first_name);
        contentValues.put("middle_name", middle_name);
        contentValues.put("last_name",last_name );
        contentValues.put("email", email);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert("auth_user", null, contentValues);
        System.out.println("result>>> : " + result );
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean checkusername (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from auth_user where username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from auth_user where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
