package com.nonstopioassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String name=getIntent().getStringExtra("name");
        Toast.makeText(HomeActivity.this,name,Toast.LENGTH_SHORT).show();
    }




}