package com.plantatree.login.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String LOGIN_TABLE="register";
    public static final String TREE_TABLE="tree";
    public static final String C_1="ID";
    public static final String C_2="username";
    public static final String C_3="password";
    public static final String C_4="vip"; //added vip column for user to be seen as a vip.

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +LOGIN_TABLE+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+LOGIN_TABLE);
        onCreate(db);
    }
}
