package com.example.kurt.plantatree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;

    //private static Scanner x;

    //possibly get rid of if there are issues
    /*public static void main(String[] args)
    {
        String username = "Bob69";
        String password = "123";
        String filepath = "members.txt";

        verifyLogin(username, password, filepath);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("Attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

   /* public static void verifyLogin(String username, String password, String filepath)
    {
        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";

        try
        {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while(x.hasNext() && !found)
            {
                tempUsername = x.next();
                tempPassword = x.next();

                if(tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim()))
                {
                    found = true;
                }
            }
            x.close();
            System.out.println(found);
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }*/

    //validate user input
    private void validate(String userName, String userPassword)
    {
        if((userName.equals( "Admin")) && (userPassword.equals("1234")))
        {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }else{
            counter--;

            Info.setText("Attempts remaining: " + String.valueOf(counter));

            if(counter == 0)
            {
            Login.setEnabled(false);
            }
        }
    }
}
