package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Odegda extends AppCompatActivity implements View.OnClickListener {
    Button pageProfile;
    TextView idodegda, nameodegda, imageodegda, cost, sexclother;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odegda);
        idodegda = findViewById(R.id.idodegda);
        nameodegda = findViewById(R.id.nameodegda);
        imageodegda = findViewById(R.id.imageodegda);
        cost = findViewById(R.id.cost);

        pageProfile = findViewById(R.id.pageProfile);
        pageProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pageProfile:
                Intent intent1 = new Intent(this, Registration.class);
                startActivity(intent1);
                break;
        }
    }
}