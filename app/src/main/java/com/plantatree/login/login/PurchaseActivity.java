package com.plantatree.login.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class PurchaseActivity  extends AppCompatActivity {

    private TextView totalPriceText;
    private TextView priceEquation;
    private TextView additionalCostEquation;
    private LinearLayout deliveryAddressLayout;
    Button btnPay;
    protected boolean deliveryChecked = false; // becomes true when user chooses delivery option
    protected double price; // price of product from DisplayActivity
    protected int number; // number of product from DisplayActivity
    private double productPrice ; // this is a value which is price multiplied by number of product
    private double additionalCost ; // this is a value which is delivery fee plus discount
    protected double totalPrice ; // this is a value which is productPrice plus additionalCost
    public static final double DELIVERY_FEE = 3.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        Intent DisplayIntent = getIntent();

        //get the price
        price = DisplayIntent.getDoubleExtra("price",0.00);

        //get the number of products
        number = DisplayIntent.getIntExtra("number",0);

        deliveryAddressLayout = (LinearLayout) findViewById(R.id.deliveryAddressLayout);
        deliveryAddressLayout.setVisibility(View.GONE);
        totalPriceText = (TextView) findViewById(R.id.TotalPrice);
        priceEquation = (TextView) findViewById(R.id.PriceEquation);
        additionalCostEquation = (TextView) findViewById(R.id.AdditionalCost);

        priceEquation.setText(getPriceEquation(number, price));
        additionalCostEquation.setText(getAdditionalCostEquation());
        totalPriceText.setText(getTotalPrice());

        btnPay = (Button) findViewById(R.id.purchase);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("purchased");
            }
            });

    }

    protected String getPriceEquation(int numberOfProduct, double price) {
        String equation="";
        double fee = price * numberOfProduct;
        fee = round(fee,2);
        this.productPrice = fee;

        equation = "$" + Double.toString(price) + " x " + Integer.toString(numberOfProduct) + " = $" + Double.toString(fee);
        return equation;
    }

    protected String getAdditionalCostEquation()
    {
        String equation="";
        double deliveryFee = !deliveryChecked ||(deliveryChecked&&number>9) ? 0.00 : DELIVERY_FEE ;
        String option= deliveryChecked ? "(Delivery Fee)" : "(PickUp Fee)";
        this.additionalCost = deliveryFee;

        equation = " + $" + deliveryFee + " " + option;
        return equation;
    }

    protected String getTotalPrice()
    {
        String msg=" = $";
        double totalPrice =  this.productPrice + this.additionalCost;
        totalPrice = round(totalPrice,2);
        this.totalPrice = totalPrice;

        msg += totalPrice;

        return msg;
    }

    private void updateTotalFee() {
        additionalCostEquation.setText(getAdditionalCostEquation());
        totalPriceText.setText(getTotalPrice());
        totalPriceText.setTextSize(35);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.DeliveryOption:
                if (checked) {
                    deliveryAddressLayout.setVisibility(View.VISIBLE);
                    deliveryChecked = true;
                    updateTotalFee();
                    toastMessage("a:" + this.deliveryChecked + " b:"+ this.additionalCost + " c:"+ this.productPrice);
                }
                    break;
            case R.id.PickupOption:
                if (checked) {
                    deliveryAddressLayout.setVisibility(View.GONE);
                    deliveryChecked = false;
                    updateTotalFee();
                    toastMessage("a:" + this.deliveryChecked + " b:"+ this.additionalCost + " c:"+ this.productPrice);
                }
                    break;

        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}



