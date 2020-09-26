package com.example.mywedding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mywedding.Database.DBHelper;
import com.example.mywedding.Models.BudgetModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BudgetList extends AppCompatActivity {
    private FloatingActionButton btn_add;
    private Button nav;
    private TextView count;
    private DBHelper dbHelper;
    private ListView listView;
    private List<BudgetModel> budgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_list);

        //app bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_budgetList);

        listView = findViewById(R.id.budgetList);
        btn_add = findViewById(R.id.btn_add_budget);
//        nav = findViewById(R.id.btn_nav);
        count = findViewById(R.id.txtBudgetCount);
        dbHelper = new DBHelper(this);

        budgets = new ArrayList<>(); //allocating memory
        budgets = dbHelper.getAllBudgets();

        BudgetAdapter bAdapter = new BudgetAdapter(this,R.layout.single_budget,budgets);
        listView.setAdapter(bAdapter);

        //invoking method to count the budgets
        int countBudget = dbHelper.countBudgets();
        count.setText("You have added " + countBudget + " budget(s)");

        //onclick Dialog box
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final BudgetModel b = budgets.get(position); //creating object relevant to the list item

                //Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(BudgetList.this);
                builder.setTitle(b.getBudgetName());
//                builder.setMessage("Select the Action you want to perform");

                //setting up the buttons in the Alert Dialog
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(BudgetList.this,ViewBudget.class);
                        intent.putExtra("ID_EXTRA",String.valueOf(b.getId()));
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deleteBudget(b.getId());
                        startActivity(new Intent(BudgetList.this, BudgetList.class));
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        startActivity(new Intent(BudgetList.this, EditBudget.class));
                        Intent intent = new Intent(BudgetList.this,EditBudget.class);
                        intent.putExtra("ID_EXTRA",String.valueOf(b.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetList.this,AddBudget.class);
                startActivity(intent);
            }
        });

    }
}