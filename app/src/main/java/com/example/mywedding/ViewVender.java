package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ViewVender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vender);

        //Back Button in android
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //App bar name
        getSupportActionBar().setTitle(R.string.appbar_name_view_vendor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.edit) {
            Intent intent = new Intent(ViewVender.this, EditVender.class);
            startActivity(intent);
        }
        if(id == android.R.id.home) {
            Intent intent = new Intent(ViewVender.this, VenderList.class);
            startActivity(intent);
        }

        return true;
       // return super.onOptionsItemSelected(item);
    }
}
