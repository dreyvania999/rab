package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    Button editprof, regNewUser, pageView;
    TextView korzina, kolvo, username, usersurname;

//    Image avataruser;

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
//        editprof.setOnClickListener(this);
//        regNewUser.setOnClickListener(this);
//        pageView.setOnClickListener(this);
    }
}