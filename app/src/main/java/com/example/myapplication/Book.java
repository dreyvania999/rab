package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Book extends AppCompatActivity implements View.OnClickListener {
    Button pageProfile, buttonAdd;
    DBHelper DBHelper;
    SQLiteDatabase DB;
    ContentValues contentValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        pageProfile = findViewById(R.id.pageProfile);
        buttonAdd = findViewById(R.id.buttonAdd);
        pageProfile.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);

        if (lis.l.equals("admin"))  {
            buttonAdd.setVisibility(View.VISIBLE);
            pageProfile.setVisibility(View.INVISIBLE);

        }

        DBHelper = new DBHelper(this);
        DB = DBHelper.getWritableDatabase();

        UpdateTable();

    }



    public void UpdateTable(){
        Cursor cursor = DB.query(DBHelper.Book, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int bookIndex = cursor.getColumnIndex(DBHelper.NameO);
            int athorIndex = cursor.getColumnIndex(DBHelper.Avtor);
            int priseIndex = cursor.getColumnIndex(DBHelper.Prise);
            int janrIndex = cursor.getColumnIndex(DBHelper.Janr);
            TableLayout dbOutput = findViewById(R.id.dbOutput);
            dbOutput.removeAllViews();

            TableRow dbOutputRow1 = new TableRow(this);
            dbOutputRow1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            TextView id = new TextView(this);
            layoutParams.weight = 1f;
            id.setLayoutParams(layoutParams);
            id.setText("Название");
            dbOutputRow1.addView(id);

            TextView namebook = new TextView(this);
            layoutParams.weight = 3.0f;
            namebook.setLayoutParams (layoutParams);
            namebook.setText("Название");
            dbOutputRow1.addView(namebook);

            TextView avtorbook = new TextView(this);
            layoutParams.weight = 3.0f;
            avtorbook.setLayoutParams (layoutParams);
            avtorbook.setText("Автор");
            dbOutputRow1.addView(avtorbook);

            TextView costbook = new TextView(this);
            layoutParams.weight = 2.0f;
            costbook.setLayoutParams(layoutParams);
            costbook.setText("Цена");
            dbOutputRow1.addView(costbook);

            TextView janr = new TextView(this);
            layoutParams.weight = 3.0f;
            janr.setLayoutParams(layoutParams);
            janr.setText("Жанр");
            dbOutputRow1.addView(janr);


            if (lis.l.equals("admin"))  {
                Button delete = new Button(this);
                delete.setOnClickListener(this);
                layoutParams.weight = 1.0f;
                delete.setLayoutParams(layoutParams);
                delete.setText("del");
                delete.setId(cursor.getInt(idIndex));
                dbOutputRow1.addView(delete);

            }

            dbOutput.addView(dbOutputRow1);
            do {
                TableRow dbOutputRow = new TableRow(this);
                dbOutputRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                TableRow.LayoutParams params = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                TextView outputID = new TextView(this);
                params.weight = 1f;
                outputID.setLayoutParams(params);
                outputID.setText(cursor.getString(idIndex));
                dbOutputRow.addView(outputID);

                TextView outputBook = new TextView(this);
                params.weight = 3.0f;
                outputBook.setLayoutParams (params);
                outputBook.setText(cursor.getString(bookIndex));
                dbOutputRow.addView(outputBook);

                TextView outputAvtor = new TextView(this);
                params.weight = 3.0f;
                outputAvtor.setLayoutParams (params);
                outputAvtor.setText(cursor.getString(athorIndex));
                dbOutputRow.addView(outputAvtor);

                TextView outputPrice = new TextView(this);
                params.weight = 2.0f;
                outputPrice.setLayoutParams(params);
                outputPrice.setText(cursor.getString(priseIndex));
                dbOutputRow.addView(outputPrice);

                TextView outputJanr = new TextView(this);
                params.weight = 3.0f;
                outputJanr.setLayoutParams(params);
                outputJanr.setText(cursor.getString(janrIndex));
                dbOutputRow.addView(outputJanr);


                if (lis.l.equals("admin"))  {
                    Button delete = new Button(this);
                    delete.setOnClickListener(this);
                    params.weight = 1.0f;
                    delete.setLayoutParams(params);
                    delete.setText("del");
                    delete.setId(cursor.getInt(idIndex));
                    dbOutputRow.addView(delete);

                }

                dbOutput.addView(dbOutputRow);

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pageProfile:
                Intent intent1 = new Intent(this, Profile.class);
                startActivity(intent1);
                break;
            case R.id.buttonAdd:
                Intent intent2 = new Intent(this, AdminAddBook.class);
                startActivity(intent2);
                break;

            default:
                if (lis.l.equals("admin"))  {
                View outputDBRow = (View) view.getParent();
                ViewGroup outputDB = (ViewGroup) outputDBRow.getParent();
                outputDB.removeView(outputDBRow);
                outputDB.invalidate();

                DB.delete(DBHelper.Book, DBHelper.KEY_ID + " = ?", new String[]{String.valueOf((view.getId()))});

                contentValues = new ContentValues();
                Cursor cursorUpdater = DB.query(DBHelper.Book, null, null, null, null, null, null);
                if (cursorUpdater.moveToFirst()) {
                    int idIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_ID);
                    int bookIndex = cursorUpdater.getColumnIndex(DBHelper.NameO);
                    int athorIndex = cursorUpdater.getColumnIndex(DBHelper.Avtor);
                    int priseIndex = cursorUpdater.getColumnIndex(DBHelper.Prise);
                    int janrIndex = cursorUpdater.getColumnIndex(DBHelper.Janr);
                    int realID = 1;
                    do {
                        if (cursorUpdater.getInt(idIndex) > realID) {
                            contentValues.put(DBHelper.KEY_ID, realID);
                            contentValues.put(DBHelper.NameO, cursorUpdater.getString(bookIndex));
                            contentValues.put(DBHelper.Avtor, cursorUpdater.getString(athorIndex));
                            contentValues.put(DBHelper.Prise, cursorUpdater.getString(priseIndex));
                            contentValues.put(DBHelper.Janr, cursorUpdater.getString(janrIndex));
                            DB.replace(DBHelper.Book, null, contentValues);
                        }
                        realID++;
                    } while (cursorUpdater.moveToNext());
                    if (cursorUpdater.moveToLast()) {
                        DB.delete(DBHelper.Book, DBHelper.KEY_ID + " = ?", new String[]{cursorUpdater.getString(idIndex)});
                    }
                    UpdateTable();
                }}

                break;
        }


    }
}