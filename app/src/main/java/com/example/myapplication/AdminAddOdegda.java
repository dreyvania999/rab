package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

public class AdminAddOdegda extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button addposition, pageclother, pagereg;
    EditText avtoradd, costadd, nameadd;
    Spinner janrsp;
    DBHelper DBHelper;
    SQLiteDatabase DB;
    ContentValues  contentValues;
    String polc;
    String[] jan = {"Классика","Фэнтези","Фантастика","Роман","Боевик","Триллер", "Ужасы"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_odegda);

        costadd = findViewById(R.id.costadd);
        avtoradd = findViewById(R.id.avtoradd);
        nameadd = findViewById(R.id.nameadd);
        janrsp = findViewById(R.id.janrsp);
        pageclother = findViewById(R.id.pageclother);
        addposition = findViewById(R.id.addposition);
        pagereg = findViewById(R.id.pagereg);

        addposition.setOnClickListener(this);
        pageclother.setOnClickListener(this);
        pagereg.setOnClickListener(this);

       DBHelper = new DBHelper(this);
       DB = DBHelper.getWritableDatabase();

        Spinner janrsp = findViewById(R.id.janrsp);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, jan);
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
                String name = nameadd.getText().toString();
                String avtor = avtoradd.getText().toString();
                String prise = costadd.getText().toString();

                contentValues.put(DBHelper.NameO, name);
                contentValues.put(DBHelper.Avtor, avtor);
                contentValues.put(DBHelper.Prise, prise);
                contentValues.put(DBHelper.Janr, polc);
                DB.insert(DBHelper.Book, null, contentValues);

                nameadd.setText("");
                avtoradd.setText("");
                costadd.setText("");
                break;

            case R.id.pageclother:
                Intent intent1 = new Intent(this, Odegda.class);
                startActivity(intent1);
                break;
            case R.id.pagereg:
                Intent intent2 = new Intent(this, Registration.class);
                startActivity(intent2);
                break;
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        polc = (String) parent.getSelectedItem();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}