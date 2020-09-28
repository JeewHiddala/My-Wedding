package com.example.mywedding;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    private ViewBudget viewBudget;
    private dashBoard dashBoard;
    private static final double DELTA = 1e-15;


    @Before //execute before all the test cases we write
    public void setup(){
        //initializing the class to settle the environment
        viewBudget = new ViewBudget();
        dashBoard = new dashBoard();
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

    @Test //test the pending budget count
    public void testPendingCount(){
        //invoking the function by giving dummy data
        int result = dashBoard.getPendingBudgets(8,2);

        //checking the expected answer 6 with the result
        assertEquals(6, result);
    }

    @Test //test the pending tasks count
    public void testPendingTasksCount(){
        //invoking the function by giving dummy data
        int result = dashBoard.getPendingTask(5,2);

        //checking the expected answer 3 with the result
        assertEquals(3, result);
    }

    @Test //test the pending vendor count
    public void testPendingVendorCount(){
        //invoking the function by giving dummy data
        int result = dashBoard.getPendingVendor(10,6);

        //checking the expected answer 4 with the result
        assertEquals(4, result);
    }

    @Test //test the not sent guest count
    public void testNotSentGuestCount(){
        //invoking the function by giving dummy data
        int result = dashBoard.getNotSent(8,6);

        //checking the expected answer 2 with the result
        assertEquals(2, result);
    }

}