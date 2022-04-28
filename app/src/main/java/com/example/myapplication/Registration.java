package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    SQLiteDatabase database;
    Button ButtonLogin;
    EditText editPhone,editPassword,editLogin,editName,editSurname,editBirtday,editAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        editPhone = findViewById(R.id.editPhone);
        editPassword = findViewById(R.id.editPassword);
        editLogin = findViewById(R.id.editLogin);
        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editBirtday = findViewById(R.id.editBirtday);
        editAddress = findViewById(R.id.editAddress);
        ButtonLogin = findViewById(R.id.ButtonLogin);
        ButtonLogin.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){

            case R.id.ButtonLogin:
                Cursor signCursor = database.query(DBHelper.People, null, null, null, null, null, null);

                boolean finded = false;
                if(signCursor.moveToFirst()){
                    int usernameIndex = signCursor.getColumnIndex(DBHelper.Login);
                    do{
                        if(editLogin.getText().toString().equals(signCursor.getString(usernameIndex))){
                            Toast.makeText(this, "Введённый логин уже зарегистрирован", Toast.LENGTH_LONG).show();
                            finded = true;
                            break;
                        }
                    }while (signCursor.moveToNext());
                }
                if(!finded){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DBHelper.Login, editLogin.getText().toString());
                    contentValues.put(DBHelper.Password, editPassword.getText().toString());
                    contentValues.put(DBHelper.Name, editName.getText().toString());
                    contentValues.put(DBHelper.Phone, editPhone.getText().toString());
                    contentValues.put(DBHelper.Birtday, editBirtday.getText().toString());
                    contentValues.put(DBHelper.Surname, editSurname.getText().toString());
                    contentValues.put(DBHelper.Address, editAddress.getText().toString());
                    database.insert(DBHelper.People, null, contentValues);
                    Toast.makeText(this, "Вы успешно зарегистрированы", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(this, MainActivity.class);
                    startActivity(intent1);
                }
                signCursor.close();
                break;
        }
    }


}
