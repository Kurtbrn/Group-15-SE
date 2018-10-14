package com.plantatree.login.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SearchActivity extends AppCompatActivity {
    private EditText et;
    SQLiteDatabase db;
    DatabaseHelper openHelper;
    ListView list;
    Cursor cursor;
    ArrayList<Tree> trees = new ArrayList<Tree>();
    String[] itemname ={
            "Green",
            "Pine",
            "Black Cherry",
            "Maple",
            "Spruce",
            "RedBud",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        populateTree();


        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.input);
                String stringToLocate = input.getText().toString();
                boolean found = false;
                for (int i = 0; i <itemname.length ; i++)  {
                    if ( itemname[i].equalsIgnoreCase( stringToLocate)) {
                        found = true;
                        System.out.println( "The value is found!");
                        int position = i;
                        Intent intent = new Intent(SearchActivity.this,DisplayActivity.class);
                        intent.putExtra("name",trees.get(position).name);
                        intent.putExtra("height",trees.get(position).height);
                        intent.putExtra("growth",trees.get(position).growth);
                        intent.putExtra("price",trees.get(position).price);
                        intent.putExtra("imgId",trees.get(position).imgId);
                        startActivity(intent);
                    }
                }
                if (!found) {
                    Toast.makeText(getApplicationContext(),"Input : "+stringToLocate+" was not found", Toast.LENGTH_SHORT).show();
                }


            }
        });

        findViewById(R.id.random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int position = rand.nextInt(trees.size());
                Intent i = new Intent(SearchActivity.this,DisplayActivity.class);
               i.putExtra("name",trees.get(position).name);
                i.putExtra("height",trees.get(position).height);
                i.putExtra("growth",trees.get(position).growth);
                i.putExtra("price",trees.get(position).price);
                i.putExtra("imgId",trees.get(position).imgId);
                startActivity(i);
            }
        });

        TreeItem adapter=new TreeItem(this, itemname,trees);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                 TODO Auto-generated method stub
                Intent i = new Intent(SearchActivity.this,DisplayActivity.class);
               i.putExtra("name",trees.get(position).name);
                i.putExtra("height",trees.get(position).height);
                i.putExtra("growth",trees.get(position).growth);
                i.putExtra("price",trees.get(position).price);
                i.putExtra("imgId",trees.get(position).imgId);
                startActivity(i);
                //Toast.makeText(getApplicationContext(),Integer.toString(position), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void populateTree() {
        trees.add(new Tree("Green",95,11,100.99,R.drawable.green));
        trees.add(new Tree("Pine",82,15,154.99,R.drawable.tree1));
        trees.add(new Tree("Black Cherry",125,4,500.99,R.drawable.blackcherry));
        trees.add(new Tree("Maple",60,6,250.99,R.drawable.maple));
        trees.add(new Tree("Spruce",220,20,66.99,R.drawable.spruce));
        trees.add(new Tree("RedBud",154,13,323.99,R.drawable.redbud));
    }
}
