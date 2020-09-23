package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import java.util.Calendar;

public class AddBudget extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner categorySpinner;
    EditText etName, etAmount, etNotes, etPaid, etPayDate;
    String category;
    DatePickerDialog datePickerDialog;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int selectedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_addBudget);

        categorySpinner = findViewById(R.id.spBudgetCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.budget_categories,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);

        etName = findViewById(R.id.etBudgetName);
        etAmount = findViewById(R.id.etBudgetAmount);
        etNotes = findViewById(R.id.etBudgetNote);
        etPaid = findViewById(R.id.etPaidAmount);
        etPayDate = findViewById(R.id.etPaymentDate);

        etPayDate.setInputType(InputType.TYPE_NULL);

        //Calendar View
        etPayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //date picker dialog box
                datePickerDialog = new DatePickerDialog(AddBudget.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etPayDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        radioGroup = findViewById(R.id.radStatus);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //showing the selected item from the spinner as a toast
        category = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), category, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.done){

            //getting selected radio button from the radio group
            selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(selectedId);

            DBHelper dbHelper = new DBHelper(this);

            long val = dbHelper.addBudget(etName.getText().toString(), etAmount.getText().toString(), etNotes.getText().toString(), category, etPaid.getText().toString(), etPayDate.getText().toString(), radioButton.getText().toString());

            if(val>0){
                Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddBudget.this,BudgetList.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddBudget.this,AddBudget.class);
                startActivity(intent);
            }

//            Intent intent = new Intent(AddBudget.this,BudgetList.class);
//            startActivity(intent);

//            Context context = getApplicationContext();
//            CharSequence message = "Budget Added";
//            int duration = Toast.LENGTH_SHORT;
//            Toast toast = Toast.makeText(context, message, duration);
//            toast.show();
        }

        if(id == android.R.id.home){
            Intent intent = new Intent(AddBudget.this,BudgetList.class);
            startActivity(intent);
        }
        return true;
    }

   /* public void addBudget(View view){
        DBHelper dbHelper = new DBHelper(this);

        long val = dbHelper.addBudget(etName.getText().toString(), etAmount.getText().toString(), etNotes.getText().toString(), category, etPaid.getText().toString(), etPayDate.getText().toString(), 0);

        if(val>0){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(AddBudget.this,BudgetList.class);
//            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(AddBudget.this,AddBudget.class);
//            startActivity(intent);
        }
    }*/
}