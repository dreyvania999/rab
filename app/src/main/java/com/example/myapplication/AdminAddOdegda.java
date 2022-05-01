package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AdminAddOdegda extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button addposition, pageClother, pageReg;
    TextView nameaddodegda, addimageodegda, addcostodegda, addsexogegda;
    EditText nameadd, costadd;
    Spinner addsex;
    DBHelper DBHelper;
    SQLiteDatabase DB;
    ContentValues  contentValues;
    String polc;
    String[] sex = {"Мужской","Женский","Не указан"};


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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, sex);
        addsex.setAdapter(adapter);
        DBHelper = new DBHelper(this);
        DB = DBHelper.getWritableDatabase();
        addsex.setOnItemSelectedListener(this);
        addsex.setPrompt("Title");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addposition:
                String nam =nameadd.getText().toString();
                String pris =costadd.getText().toString();


                contentValues = new ContentValues();
                contentValues.put(DBHelper.NameO,nam);
                contentValues.put(DBHelper.Prise,pris);
                contentValues.put(DBHelper.Pol,polc);
                DB.insert(DBHelper.Odeg,null,contentValues);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        polc = (String) parent.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}