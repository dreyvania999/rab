package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminAddOdegda extends AppCompatActivity implements View.OnClickListener {

    Button addposition, pageClother, pageReg;
    TextView nameaddodegda, addimageodegda, addcostodegda, addsexogegda;
    EditText nameadd, costadd, addsex;
    DBHelper DBHelper;
    SQLiteDatabase DB;
    ContentValues  contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_odegda);
        nameadd = findViewById(R.id.nameadd);
        costadd = findViewById(R.id.costadd);
        addsex = findViewById(R.id.addsex);
        nameaddodegda = findViewById(R.id.nameaddodegda);
        addimageodegda = findViewById(R.id.addimageodegda);
        addcostodegda = findViewById(R.id.addcostodegda);
        addsexogegda = findViewById(R.id.addsexogegda);
        pageClother = findViewById(R.id.pageClother);
        addposition = findViewById(R.id.addposition);
        pageReg = findViewById(R.id.pageReg);

        addposition.setOnClickListener(this);
        pageClother.setOnClickListener(this);
        pageReg.setOnClickListener(this);

        DBHelper = new DBHelper(this);
        DB = DBHelper.getWritableDatabase();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addposition:
                String name =nameadd.getText().toString();
                String prise =costadd.getText().toString();
                String pol =addsex.getText().toString();
                contentValues = new ContentValues();
                contentValues.put(DBHelper.Name,name);
                contentValues.put(DBHelper.Prise,prise);
                contentValues.put(DBHelper.Pol,pol);
                DB.insert(DBHelper.Odegda,null,contentValues);
                addsex.setText("");
                costadd.setText("");
                nameadd.setText("");
                break;
            case R.id.pageClother:
                Intent intent1 = new Intent(this, Odegda.class);
                startActivity(intent1);
                break;
            case R.id.pageReg:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
        }

    }
}