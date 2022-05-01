package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Odegda extends AppCompatActivity implements View.OnClickListener {
    Button pageProfile;
    TextView nameodegda, imageodegda, cost, sexclother;
    DBHelper DBHelper;
    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odegda);
        nameodegda = findViewById(R.id.nameodegda);
        imageodegda = findViewById(R.id.imageodegda);
        cost = findViewById(R.id.cost);
        sexclother = findViewById(R.id.sexclother);
        pageProfile = findViewById(R.id.pageProfile);
        pageProfile.setOnClickListener(this);

        DBHelper = new DBHelper(this);
        DB = DBHelper.getWritableDatabase();

        UpdateTable();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pageProfile:
                Intent intent1 = new Intent(this, Profile.class);
                startActivity(intent1);
                break;
        }
    }

    public void UpdateTable(){
        Cursor cursor = DB.query(DBHelper.Odeg, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int productIndex = cursor.getColumnIndex(DBHelper.NameO);
            int articleIndex = cursor.getColumnIndex(DBHelper.Prise);
            int countIndex = cursor.getColumnIndex(DBHelper.Pol);
            TableLayout dbOutput = findViewById(R.id.dbOutput);
            dbOutput.removeAllViews();


            do {
                TableRow dbOutputRow = new TableRow(this);
                dbOutputRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                TableRow.LayoutParams params = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);



                TextView outputID = new TextView(this);
                params.weight = 1.0f;
                outputID.setLayoutParams(params);
                outputID.setText(cursor.getString(idIndex));
                dbOutputRow.addView(outputID);

                TextView outputProduct = new TextView(this);
                params.weight = 3.0f;
                outputProduct.setLayoutParams(params);
                outputProduct.setText(cursor.getString(productIndex));
                dbOutputRow.addView(outputProduct);

                TextView outputArticle = new TextView(this);
                params.weight = 3.0f;
                outputArticle.setLayoutParams(params);
                outputArticle.setText(cursor.getString(articleIndex));
                dbOutputRow.addView(outputArticle);

                TextView outputCount = new TextView(this);
                params.weight = 3.0f;
                outputCount.setLayoutParams(params);
                outputCount.setText(cursor.getString(countIndex));
                dbOutputRow.addView(outputCount);


                dbOutput.addView(dbOutputRow);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

}