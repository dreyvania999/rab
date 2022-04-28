package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class Odegda extends AppCompatActivity {

    Button pageProfile;
    TextView idodegda, nameodegda, cost, sexclother;
    TableLayout viewOdegda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odegda);
        idodegda = findViewById(R.id.idodegda);
        nameodegda = findViewById(R.id.nameodegda);
        cost = findViewById(R.id.cost);
        sexclother = findViewById(R.id.sexclother);
        pageProfile = findViewById(R.id.pageProfile);

//        pageProfile.setOnClickListener(this);
    }
}