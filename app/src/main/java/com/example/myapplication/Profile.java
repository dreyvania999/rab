package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    Button editprof, exite, pageView;
    TextView korzina, kolvo, username, usersurname;
    DBHelper DBHelper;
    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        korzina = findViewById(R.id.korzina);
        kolvo = findViewById(R.id.kolvo);
        username = findViewById(R.id.username);
        usersurname = findViewById(R.id.usersurname);
        editprof = findViewById(R.id.editprof);
        exite = findViewById(R.id.exite);
        pageView = findViewById(R.id.pageView);
        editprof.setOnClickListener(this);
        exite.setOnClickListener(this);
        pageView.setOnClickListener(this);

        DBHelper = new DBHelper(this);
        DB = DBHelper.getWritableDatabase();

        UpdateTable();


    }
    public void UpdateTable(){
        Cursor cursor = DB.query(DBHelper.Korzina, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int bookIndex = cursor.getColumnIndex(DBHelper.NameK);
            int athorIndex = cursor.getColumnIndex(DBHelper.AvtorK);
            int priseIndex = cursor.getColumnIndex(DBHelper.PriseK);
            TableLayout dbOutput = findViewById(R.id.dbOutput);
            dbOutput.removeAllViews();


            do {
                TableRow tableLayoutRow = new TableRow(this);
                tableLayoutRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                TableRow.LayoutParams params = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                TextView outputID = new TextView(this);
                params.weight = 1f;
                outputID.setLayoutParams(params);
                outputID.setText(cursor.getString(idIndex));
                tableLayoutRow.addView(outputID);

                TextView outputBook = new TextView(this);
                params.weight = 3.0f;
                outputBook.setLayoutParams (params);
                outputBook.setText(cursor.getString(bookIndex));
                tableLayoutRow.addView(outputBook);

                TextView outputAvtor = new TextView(this);
                params.weight = 3.0f;
                outputAvtor.setLayoutParams (params);
                outputAvtor.setText(cursor.getString(athorIndex));
                tableLayoutRow.addView(outputAvtor);

                TextView outputPrice = new TextView(this);
                params.weight = 2.0f;
                outputPrice.setLayoutParams(params);
                outputPrice.setText(cursor.getString(priseIndex));
                tableLayoutRow.addView(outputPrice);

//                Button delKorz = new Button(this);
//                delKorz.setOnClickListener(this);
//                params.weight = 1.0f;
//                delKorz.setLayoutParams(params);
//                delKorz.setText("-");
//                delKorz.setId(cursor.getInt(idIndex));
//                tableLayoutRow.addView(delKorz);

                dbOutput.addView(tableLayoutRow);

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editprof:
                Intent intent1 = new Intent(this, Registration.class);
                startActivity(intent1);
                break;
            case R.id.pageView:
                Intent intent3 = new Intent(this, Odegda.class);
                startActivity(intent3);
                break;
        }
    }
}