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
    TextView idodegda, nameodegda, imageodegda, cost, sexclother;
    DBHelper DBHelper;
    SQLiteDatabase DB;
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
                Intent intent1 = new Intent(this, Profile.class);
                startActivity(intent1);
                break;
        }
    }

    public void UpdateTable(){
        Cursor cursor = DB.query(DBHelper.Odegda, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int NameOIndex = cursor.getColumnIndex(DBHelper.NameO);
            int PriseIndex = cursor.getColumnIndex(DBHelper.Prise);
            int PolIndex = cursor.getColumnIndex(DBHelper.Pol);

            TableLayout dbOutput = findViewById(R.id.viewOdegda);
            dbOutput.removeAllViews();
            do {
                TableRow dbOutputRow = new TableRow(this);
                dbOutputRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);


                ImageView imageView = new ImageView(this);
                ConstraintLayout constraintLayout = new ConstraintLayout(this);

                imageView.setImageResource(R.drawable.pan);

                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                        (ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(layoutParams);
                constraintLayout.addView(imageView);

                setContentView(constraintLayout);

                TextView outputID = new TextView(this);
                params.weight = 1.0f;
                outputID.setLayoutParams(params);
                outputID.setText(cursor.getString(idIndex));
                outputID.setTextSize(12);
                dbOutputRow.addView(outputID);

                TextView outputBrand = new TextView(this);
                params.weight = 3.0f;
                outputBrand.setLayoutParams(params);
                outputBrand.setText(cursor.getString(NameOIndex));
                outputBrand.setTextSize(12);
                dbOutputRow.addView(outputBrand);

                TextView outputModel = new TextView(this);
                params.weight = 3.0f;
                outputModel.setLayoutParams(params);
                outputModel.setText(cursor.getString(PriseIndex));
                outputModel.setTextSize(12);
                dbOutputRow.addView(outputModel);

                TextView outputDate = new TextView(this);
                params.weight = 3.0f;
                outputDate.setLayoutParams(params);
                outputDate.setText(cursor.getString(PolIndex));
                outputDate.setTextSize(12);
                dbOutputRow.addView(outputDate);


                Button deleteBtn = new Button(this);
                deleteBtn.setOnClickListener(this);
                params.weight = 1.0f;
                deleteBtn.setLayoutParams(params);
                deleteBtn.setText("Удалить\nзапись");
                deleteBtn.setTextSize(12);
                deleteBtn.setId(cursor.getInt(idIndex));
                dbOutputRow.addView(deleteBtn);

                dbOutput.addView(dbOutputRow);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

}