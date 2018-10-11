package com.plantatree.login.login;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DisplayActivity extends AppCompatActivity {
    private TextView name;
    private TextView number;
    private String treename;
    private TextView price;
    private TextView growth;
    private TextView place;
    private TextView height;
    int choose_tree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent i = getIntent();

        treename =i.getStringExtra("data");
        Random random = new Random(3);
        choose_tree = random.nextInt(3);
        tree_information(choose_tree);

        name = (TextView) findViewById(R.id.Treename);
        name.setText(treename);

        number = (TextView)findViewById(R.id.treenumber);
        price =(TextView)findViewById(R.id.Price);

        findViewById(R.id.increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberpicker = Integer.parseInt(number.getText().toString()) + 1;
                String n = Integer.toString(numberpicker);
                number.setText(n);
            }
        });

        findViewById(R.id.decrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberpicker = Integer.parseInt(number.getText().toString());
                if(numberpicker>0){
                    numberpicker = numberpicker - 1;
                }
                String n = Integer.toString(numberpicker);
                number.setText(n);
            }
        });

        findViewById(R.id.purchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberpicker = Integer.parseInt(number.getText().toString());
                double Price = Double.valueOf(price.getText().toString());

                Intent i = new Intent(DisplayActivity.this,PurchaseActivity.class);
                i.putExtra("number",numberpicker);
                i.putExtra("price",Price);
                startActivity(i);
            }
        });
    }

    private void tree_information(int number){
        if(number==0){
            price = (TextView)findViewById(R.id.Price);
            price.setText("10.18");
            growth = (TextView)findViewById(R.id.Growth);
            growth.setText("Growth: Fast");
            height = (TextView)findViewById(R.id.treeheight);
            height.setText("Height: 8m");
            place =(TextView)findViewById(R.id.PlantPlace);
            place.setText("Place: Auckland");
        }else if(number==1){
            price = (TextView)findViewById(R.id.Price);
            price.setText("30.22");
            growth = (TextView)findViewById(R.id.Growth);
            growth.setText("Growth: Slow");
            height = (TextView)findViewById(R.id.treeheight);
            height.setText("Height: 10m");
            place =(TextView)findViewById(R.id.PlantPlace);
            place.setText("Place: Christchurch");
        }else if(number==2){
            price = (TextView)findViewById(R.id.Price);
            price.setText("20.50");
            growth = (TextView)findViewById(R.id.Growth);
            growth.setText("Growth: Fast");
            height = (TextView)findViewById(R.id.treeheight);
            height.setText("Height: 6m");
            place =(TextView)findViewById(R.id.PlantPlace);
            place.setText("Place: Drury");
        }
    }
}
