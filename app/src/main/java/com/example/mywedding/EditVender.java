package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class EditVender extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vender);

       //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //App bar name
        getSupportActionBar().setTitle(R.string.appbar_name_edit_vendor);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show(); //Toast
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);
        return true;
    }

    //Button  action on AppBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.done) {
            Intent intent = new Intent(EditVender.this, ViewVender.class);
            startActivity(intent);

            //Toast
            Context context = getApplicationContext();
            CharSequence message = "Details Saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }

        if(id == android.R.id.home){
            Intent intent = new Intent(EditVender.this, VenderList.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}