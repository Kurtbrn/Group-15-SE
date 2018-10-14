package com.plantatree.login.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //call classes for database
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg;
    Button _btnlogin;
    EditText _txtfname;
    EditText _txtpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper = new DatabaseHelper(this);
        _btnreg = (Button)findViewById(R.id.btnreg);
        _btnlogin = (Button)findViewById(R.id.btnlogin);
        _txtfname = (EditText)findViewById(R.id.txtuser);
        _txtpass = (EditText)findViewById(R.id.txtpass);
        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname = _txtfname.getText().toString();
                String pass = _txtpass.getText().toString();
                insertdata(fname, pass);
                Toast.makeText(getApplicationContext(), "Signed up", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void insertdata(String fname, String pass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.C_2, fname);
        contentValues.put(DatabaseHelper.C_3, pass);
        long id = db.insert(DatabaseHelper.LOGIN_TABLE, null, contentValues);
    }
}
