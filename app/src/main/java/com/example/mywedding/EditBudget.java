package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;
import com.example.mywedding.Models.BudgetModel;

import java.util.Calendar;

public class EditBudget extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText et_LastPayment, et_name, et_amount, et_notes, et_paid;
    private Spinner spin_category;
    private DatePickerDialog datePickerDialog;
    private DBHelper dbHelper;
    private Context context;
    private String selectedCategory, selectedRadio, category;
    private RadioGroup radioGroup;
    private RadioButton radioButton, radioButton1, radioButton2;
    private int selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_editBudget);

        context = this;
        dbHelper = new DBHelper(context);

        //data from the intent extra
        final String id = getIntent().getStringExtra("ID_EXTRA");
        System.out.println(id);
        BudgetModel budgetModel = dbHelper.getSingleBudget(Integer.parseInt(id));

        spin_category = findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.budget_categories,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_category.setAdapter(adapter);

        //fetching value of the spinner from the database
        selectedCategory = budgetModel.getCategory();
        System.out.println(selectedCategory);

        if(selectedCategory != null){
            int spinnerPosition = adapter.getPosition(selectedCategory);
            System.out.println(spinnerPosition);
            spin_category.setSelection(spinnerPosition);
        }

        spin_category.setOnItemSelectedListener(this);

        et_name = findViewById(R.id.txt_BudgetName);
        et_amount = findViewById(R.id.txt_BudgetAmount);
        et_notes = findViewById(R.id.txt_specialNote);
        et_paid = findViewById(R.id.txt_paidAmount);
        et_LastPayment = findViewById(R.id.txt_LastPaymentDate);
        radioGroup = findViewById(R.id.radStatusEdit);
        radioButton1 = findViewById(R.id.radPendingEdit);
        radioButton2 = findViewById(R.id.radPaidEdit);

        selectedRadio = budgetModel.getStatus();
//        System.out.println(selectedCategory);

        //setting the selected radio button
        if(selectedRadio.equals("Pending")){
            radioButton1.setChecked(true);
        } else {
            radioButton2.setChecked(true);
        }

        //Implementing Date Picker
        et_LastPayment.setInputType(InputType.TYPE_NULL);
        et_LastPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //date picker dialog box
                datePickerDialog = new DatePickerDialog(EditBudget.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                et_LastPayment.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        //setting values to the view
        et_name.setText(budgetModel.getBudgetName());
        et_amount.setText(String.valueOf(budgetModel.getAmount()));
        et_notes.setText(budgetModel.getNotes());
        et_paid.setText(String.valueOf(budgetModel.getPaidAmount()));
        et_LastPayment.setText(budgetModel.getPaidDate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //showing the selected item from the spinner as a toast
        category = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), category, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemIdNav = item.getItemId();

        if(itemIdNav == R.id.done){

            //getting selected radio button from the radio group
            selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(selectedId);

            String id = getIntent().getStringExtra("ID_EXTRA");

            String name = et_name.getText().toString();
            Double amount = Double.parseDouble(et_amount.getText().toString());
            String notes = et_notes.getText().toString();
            Double paid = Double.parseDouble(et_paid.getText().toString());
            String lastPaid = et_LastPayment.getText().toString();
            String status = radioButton.getText().toString();

            BudgetModel bModel = new BudgetModel(Integer.parseInt(id), name, amount, notes, category, paid, lastPaid, status);

            long val = dbHelper.updateBudget(bModel);
            System.out.println(val);

            if(val>0){
                Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BudgetList.class);
                startActivity(intent);
            } else {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, EditBudget.class);
                startActivity(intent);
            }

        }

        if(itemIdNav == android.R.id.home){
            Intent intent = new Intent(EditBudget.this,BudgetList.class);
            startActivity(intent);
        }
        return true;
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}