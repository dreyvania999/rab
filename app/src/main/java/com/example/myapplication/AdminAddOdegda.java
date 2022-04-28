package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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
        UpdateDB();
    }

    public void UpdateDB(){
        Cursor cursor = DB.query(DBHelper.Odegda, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.NameO);
            int priseIndex = cursor.getColumnIndex(DBHelper.Prise);
            int pol = cursor.getColumnIndex(DBHelper.Pol);
        }
        cursor.close();
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
                UpdateDB();
                addsex.setText("");
                costadd.setText("");
                nameadd.setText("");
                break;
        }

    }
}