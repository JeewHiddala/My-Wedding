package com.example.mywedding;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    private ViewBudget viewBudget;
    private static final double DELTA = 1e-15;


    @Before //execute before all the test cases we write
    public void setup(){
        //initializing the class to settle the environment
        viewBudget = new ViewBudget();
    }

    @Test //test the balance payable value
    public void testBalance(){
        //invoking the function by giving dummy data
        double result = viewBudget.payable(3000, 1000);

        //checking the expected answer 2000 with the result
        assertEquals(2000, result, DELTA);
    }

    @Test //test the balance payable value if the paid is greater than amount
    public void testZero(){
        //invoking the function by giving dummy data
        double result = viewBudget.payable(1000,3000);

        //checking the expected answer 0 with the result
        assertEquals(0, result, DELTA);
    }

}