package com.plantatree.login.login;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private PurchaseActivity mCalculator;

    @Before
    public void setUp() throws Exception {
        PurchaseActivity mCalculator = new PurchaseActivity();
        this.mCalculator = mCalculator;
    }

    @Test
    public void calculateDelivery() {
        mCalculator.getPriceEquation(2,10.50);
        mCalculator.deliveryChecked = true;
        mCalculator.getAdditionalCostEquation();
        mCalculator.getTotalPrice();
        assertEquals("price is not correctly calculated",24.00,mCalculator.totalPrice,0.001);
    }

    @Test
    public void calculatePickup() {
        mCalculator.getPriceEquation(3,11.50);
        mCalculator.deliveryChecked = false;
        mCalculator.getAdditionalCostEquation();
        mCalculator.getTotalPrice();
        assertEquals("price is not correctly calculated",34.50,mCalculator.totalPrice,0.001);
    }
}
