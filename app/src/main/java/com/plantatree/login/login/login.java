package com.plantatree.login.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    SQLiteDatabase db;
    DatabaseHelper openHelper;
    Button _btnlogin;
    Button _btnsign;
    EditText _txtuser;
    EditText _txtpass;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        _btnsign = (Button)findViewById(R.id.btnsign);
        _btnlogin = (Button)findViewById(R.id.btnlogin);
        _txtuser = (EditText)findViewById(R.id.txtuser);
        _txtpass = (EditText)findViewById(R.id.txtpass);
        _btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = _txtuser.getText().toString();
                String pass = _txtpass.getText().toString();
                cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.LOGIN_TABLE+ " WHERE " + DatabaseHelper.C_2+ " =? AND "+ DatabaseHelper.C_3+" =? ", new String[]{user, pass});
                if (cursor != null){
                    if (cursor.getCount() > 0){
                        Toast.makeText(getApplicationContext(), "logged in", Toast.LENGTH_LONG).show();
                        cursor.close();
                        //use intent to move to the first page
                        Intent nextPage = new Intent(login.this, SearchActivity.class);
                        startActivity(nextPage);
                    }else {
                        Toast.makeText(getApplicationContext(), "Wrong password/ username", Toast.LENGTH_LONG).show();
                        cursor.close();
                    }
                }
                cursor.close();
            }
        });
    }
}
