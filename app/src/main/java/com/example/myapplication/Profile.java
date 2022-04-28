package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    Button editprof, regNewUser, pageView;
    TextView korzina, kolvo, username, usersurname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        korzina = findViewById(R.id.korzina);
        kolvo = findViewById(R.id.kolvo);
        username = findViewById(R.id.username);
        usersurname = findViewById(R.id.usersurname);
        editprof = findViewById(R.id.editprof);
        regNewUser = findViewById(R.id.regNewUser);
        pageView = findViewById(R.id.pageView);
        editprof.setOnClickListener(this);
        regNewUser.setOnClickListener(this);
        pageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editprof:
                Intent intent1 = new Intent(this, Registration.class);
                startActivity(intent1);
                break;
            case R.id.regNewUser:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.pageView:
                Intent intent3 = new Intent(this, Odegda.class);
                startActivity(intent3);
                break;
        }
    }
}