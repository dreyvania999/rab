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
    TextView  username, usersurname;
    DBHelper DBHelper;
    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);;
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

        if (lis.l!=""){
            Cursor cursorLog = DB.query(DBHelper.People, null, null, null, null, null, null);
            if (cursorLog.moveToFirst()) {
                int NameIndex = cursorLog.getColumnIndex(DBHelper.Name);
                int SurnameIndex = cursorLog.getColumnIndex(DBHelper.Surname);
                int loginIndex = cursorLog.getColumnIndex(DBHelper.Login);
                do {
                    if (lis.l.equals(cursorLog.getString(loginIndex))) {

                        username.setText(cursorLog.getString(NameIndex));
                        usersurname.setText(cursorLog.getString(SurnameIndex));


                        break;
                    }

                } while (cursorLog.moveToNext());
                cursorLog.close();

            }
        }
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