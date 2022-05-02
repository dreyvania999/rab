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

public class jurnals extends AppCompatActivity implements View.OnClickListener {

    Button pageProfile, buttonAdd, pagebook;
    DBHelper DBHelper;
    SQLiteDatabase DB;
    ContentValues contentValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_jurnals);


        pageProfile = findViewById(R.id.pageProfile);
        buttonAdd = findViewById(R.id.buttonAdd);
        pagebook = findViewById(R.id.pagebook);
        pageProfile.setOnClickListener(this);
        pagebook.setOnClickListener(this);
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
        Cursor cursor = DB.query(DBHelper.Jurnal, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idInd = cursor.getColumnIndex(DBHelper.KEY_ID);
            int jurIndex = cursor.getColumnIndex(DBHelper.Namejur);
            int izdIndex = cursor.getColumnIndex(DBHelper.Izdateljur);
            int priceIndex = cursor.getColumnIndex(DBHelper.Prisejur);
            int jIndex = cursor.getColumnIndex(DBHelper.JanrJ);
            TableLayout dbOutput = findViewById(R.id.dbOutput);
            dbOutput.removeAllViews();

            TableRow dbOutputRow1 = new TableRow(this);
            dbOutputRow1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            TextView id = new TextView(this);
            layoutParams.weight = 1f;
            id.setLayoutParams(layoutParams);
            id.setText(" ");
            dbOutputRow1.addView(id);

            TextView namejur = new TextView(this);
            layoutParams.weight = 3.0f;
            namejur.setLayoutParams (layoutParams);
            namejur.setText("Название");
            dbOutputRow1.addView(namejur);

            TextView izdat = new TextView(this);
            layoutParams.weight = 3.0f;
            izdat.setLayoutParams (layoutParams);
            izdat.setText("Автор");
            dbOutputRow1.addView(izdat);

            TextView cost = new TextView(this);
            layoutParams.weight = 2.0f;
            cost.setLayoutParams(layoutParams);
            cost.setText("Цена");
            dbOutputRow1.addView(cost);

            TextView janrj = new TextView(this);
            layoutParams.weight = 3.0f;
            janrj.setLayoutParams(layoutParams);
            janrj.setText("Направленность");
            dbOutputRow1.addView(janrj);



            if (lis.l.equals("admin"))  {
                TextView txt = new TextView(this);
                layoutParams.weight = 3.0f;
                txt.setLayoutParams(layoutParams);
                txt.setText("           ");
                dbOutputRow1.addView(txt);
            }

            dbOutput.addView(dbOutputRow1);
            do {
                TableRow dbOutputRow = new TableRow(this);
                dbOutputRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                TableRow.LayoutParams params = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                TextView outputId = new TextView(this);
                params.weight = 1f;
                outputId.setLayoutParams(params);
                outputId.setText(cursor.getString(idInd));
                dbOutputRow.addView(outputId);

                TextView outputJur = new TextView(this);
                params.weight = 3.0f;
                outputJur.setLayoutParams (params);
                outputJur.setText(cursor.getString(jurIndex));
                dbOutputRow.addView(outputJur);

                TextView outputIzd = new TextView(this);
                params.weight = 3.0f;
                outputIzd.setLayoutParams (params);
                outputIzd.setText(cursor.getString(izdIndex));
                dbOutputRow.addView(outputIzd);

                TextView outputPrise = new TextView(this);
                params.weight = 2.0f;
                outputPrise.setLayoutParams(params);
                outputPrise.setText(cursor.getString(priceIndex));
                dbOutputRow.addView(outputPrise);

                TextView outputJJ = new TextView(this);
                params.weight = 3.0f;
                outputJJ.setLayoutParams(params);
                outputJJ.setText(cursor.getString(jIndex));
                dbOutputRow.addView(outputJJ);


                if (lis.l.equals("admin"))  {
                    Button delete1 = new Button(this);
                    delete1.setOnClickListener(this);
                    params.weight = 1.0f;
                    delete1.setLayoutParams(params);
                    delete1.setText("del");
                    delete1.setId(cursor.getInt(idInd));
                    dbOutputRow.addView(delete1);

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
                Intent intent2 = new Intent(this, AdminAddJurnals.class);
                startActivity(intent2);
                break;
            case R.id.pagebook:
                Intent intent3 = new Intent(this, Book.class);
                startActivity(intent3);
                break;


            default:
                if (lis.l.equals("admin")) {
                    View outputDBRow = (View) view.getParent();
                    ViewGroup outputDB = (ViewGroup) outputDBRow.getParent();
                    outputDB.removeView(outputDBRow);
                    outputDB.invalidate();

                    DB.delete(DBHelper.Jurnal, DBHelper.KEY_ID + " = ?", new String[]{String.valueOf((view.getId()))});

                    contentValues = new ContentValues();
                    Cursor cursorUpdater = DB.query(DBHelper.Jurnal, null, null, null, null, null, null);
                    if (cursorUpdater.moveToFirst()) {
                        int idInd = cursorUpdater.getColumnIndex(DBHelper.KEY_ID);
                        int jurIndex = cursorUpdater.getColumnIndex(DBHelper.Namejur);
                        int izdIndex = cursorUpdater.getColumnIndex(DBHelper.Izdateljur);
                        int priceIndex = cursorUpdater.getColumnIndex(DBHelper.Prisejur);
                        int jIndex = cursorUpdater.getColumnIndex(DBHelper.JanrJ);
                        int realID = 1;
                        do {
                            if (cursorUpdater.getInt(idInd) > realID) {
                                contentValues.put(DBHelper.KEY_ID, realID);
                                contentValues.put(DBHelper.Namejur, cursorUpdater.getString(jurIndex));
                                contentValues.put(DBHelper.Izdateljur, cursorUpdater.getString(izdIndex));
                                contentValues.put(DBHelper.Prisejur, cursorUpdater.getString(priceIndex));
                                contentValues.put(DBHelper.JanrJ, cursorUpdater.getString(jIndex));
                                DB.replace(DBHelper.Jurnal, null, contentValues);
                            }
                            realID++;
                        } while (cursorUpdater.moveToNext());
                        if (cursorUpdater.moveToLast()) {
                            DB.delete(DBHelper.Jurnal, DBHelper.KEY_ID + " = ?", new String[]{cursorUpdater.getString(idInd)});
                        }
                        UpdateTable();
                    }

                }    break;
        }
    }
}
