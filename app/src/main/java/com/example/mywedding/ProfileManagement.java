package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileManagement extends AppCompatActivity {
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_profileManagement);

        btn_update = findViewById(R.id.btn_update);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(ProfileManagement.this,Settings.class);
                startActivity(intent);

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence message = "Updated Successfully";
                Toast toast = Toast.makeText(context,message, duration);
                toast.show();
            }
        });
    }
}