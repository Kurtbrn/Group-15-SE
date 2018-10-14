package com.plantatree.login.login;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {
    private TextView name;
    private TextView number;
    private String treename;
    private double price = 19.99;
    int growth;
    int height;
    int imgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent i = getIntent();

        treename =i.getStringExtra("name");
        price =i.getDoubleExtra("price",0);
        imgId =i.getIntExtra("imgId",0);
        height =i.getIntExtra("height",0);
        growth =i.getIntExtra("growth",0);

        name = (TextView) findViewById(R.id.Treename);
        name.setText(treename);
        ImageView pic = (ImageView) findViewById(R.id.Treepicture);
        pic.setImageResource(imgId);
        name.setText(treename);
        TextView p  = (TextView) findViewById(R.id.Price);
        p.setText("Price: $" + price);
        TextView h = (TextView) findViewById(R.id.treeheight);
        h.setText("Max height: " + height + " m");
        TextView g = (TextView) findViewById(R.id.Growth);
        g.setText("Growth: " + growth+" months");

        number = (TextView)findViewById(R.id.treenumber);

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
                int numberpicker = Integer.parseInt(number.getText().toString()) - 1;
                String n = Integer.toString(numberpicker);
                number.setText(n);
            }
        });


        findViewById(R.id.purchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberpicker = Integer.parseInt(number.getText().toString());
                Intent i = new Intent(DisplayActivity.this,PurchaseActivity.class);
                i.putExtra("number",numberpicker);
                i.putExtra("price",price);
                startActivity(i);
            }
        });





    }
}
