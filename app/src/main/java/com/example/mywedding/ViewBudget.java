package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mywedding.Database.DBHelper;
import com.example.mywedding.Models.BudgetModel;

public class ViewBudget extends AppCompatActivity {
    private TextView txtName, txtAmount, txtNotes, txtPaidDate, txtPaidAmount, txtCategory, txtpayableAmount, txtStatus;
    private DBHelper dbHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_viewBudget);

        context = this;
        dbHelper = new DBHelper(context);

        //data from the Intent Extra
        final String id = getIntent().getStringExtra("ID_EXTRA");
        System.out.println(id);

        //getting details according to the id received from the extra
        BudgetModel budgetModel = dbHelper.getSingleBudget(Integer.parseInt(id));

        //assigning the view elements to the objects
        txtName = findViewById(R.id.txt_BudgetName);
        txtAmount = findViewById(R.id.txt_BudgetAmount);
        txtNotes = findViewById(R.id.txt_specialNote);
        txtCategory = findViewById(R.id.txt_category);
        txtPaidAmount = findViewById(R.id.txt_paidAmount);
        txtPaidDate = findViewById(R.id.txt_LastPaymentDate);
        txtpayableAmount = findViewById(R.id.txt_Balance);
        txtStatus = findViewById(R.id.txt_Status);

        //printing values to the view
        txtName.setText(budgetModel.getBudgetName());
        txtAmount.setText(String.valueOf(budgetModel.getAmount()));
        txtNotes.setText(budgetModel.getNotes());
        txtCategory.setText(budgetModel.getCategory());
        txtPaidAmount.setText(String.valueOf(budgetModel.getPaidAmount()));
        txtPaidDate.setText(budgetModel.getPaidDate());
        txtStatus.setText(budgetModel.getStatus() + " Budget");

        //printing balance payable
        txtpayableAmount.setText("LKR: " + payable(budgetModel.getAmount(), budgetModel.getPaidAmount()));

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }*/


    //to calculate the budget payable amount
    public double payable(double value1, double value2){
        double payable = value1 - value2;

        //to avoid from printing minus values
        if(payable < 0){
            payable = 0.00;

        }
        return payable;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        /*if (id == R.id.edit){
            Intent intent = new Intent(ViewBudget.this,EditBudget.class);
            startActivity(intent);
        }*/

        if(id == android.R.id.home){
            Intent intent = new Intent(ViewBudget.this,BudgetList.class);
            startActivity(intent);
        }
        return true;
    }
}
