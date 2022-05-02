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

public class AdminAddJurnals extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Button addposition, pagejurnals, statistic;
    EditText publish, cost, name;
    Spinner janrsp;
    DBHelper DBHelper;
    SQLiteDatabase DB;
    ContentValues contentValues;
    String polc1;
    String[] jan1 = {"Мода","Научно-Популярное","Публицистика"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_jurnals);

        name = findViewById(R.id.name);
        cost = findViewById(R.id.cost);
        publish = findViewById(R.id.publish);
        janrsp = findViewById(R.id.janrsp);
        pagejurnals = findViewById(R.id.pageclother);
        addposition = findViewById(R.id.addposition);
        statistic = findViewById(R.id.statistic);

        addposition.setOnClickListener(this);
        pagejurnals.setOnClickListener(this);
        statistic.setOnClickListener(this);

        DBHelper = new DBHelper(this);
        DB = DBHelper.getWritableDatabase();

        Spinner janrsp = findViewById(R.id.janrsp);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, jan1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        janrsp.setAdapter(adapter);

        janrsp.setOnItemSelectedListener(this);
        janrsp.setPrompt("Title");

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.addposition:

                contentValues = new ContentValues();
                String nam = name.getText().toString();
                String pub = publish.getText().toString();
                String prise = cost.getText().toString();

                contentValues.put(DBHelper.NameO, nam);
                contentValues.put(DBHelper.Avtor, pub);
                contentValues.put(DBHelper.Prise, prise);
                contentValues.put(DBHelper.Janr, polc1);
                DB.insert(DBHelper.Jurnal, null, contentValues);

                name.setText("");
                publish.setText("");
                cost.setText("");
                break;

            case R.id.pagejurnals:
                Intent intent1 = new Intent(this, Book.class);
                startActivity(intent1);
                break;
            case R.id.statistic:
                Intent intent2 = new Intent(this, Jpos.class);
                startActivity(intent2);
                break;
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        polc1 = (String) parent.getSelectedItem();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}