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
    boolean bil=false;
    private Object ContentValues;
    int index;


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


        if (lis.l!=""){
            Cursor cursorLog = database.query(DBHelper.People, null, null, null, null, null, null);
            if (cursorLog.moveToFirst()) {
                int passwordIndex = cursorLog.getColumnIndex(DBHelper.Password);
                int NameIndex = cursorLog.getColumnIndex(DBHelper.Name);
                int PhoneIndex = cursorLog.getColumnIndex(DBHelper.Phone);
                int BirtdayIndex = cursorLog.getColumnIndex(DBHelper.Birthday);
                int SurnameIndex = cursorLog.getColumnIndex(DBHelper.Surname);
                int AddressIndex = cursorLog.getColumnIndex(DBHelper.Address);
                int loginIndex = cursorLog.getColumnIndex(DBHelper.Login);
                int idIndex =cursorLog.getColumnIndex(DBHelper.KEY_ID);
                do {
                    if (lis.l.equals(cursorLog.getString(loginIndex))) {

                        editPhone.setText(cursorLog.getString(PhoneIndex));
                        editPassword.setText(cursorLog.getString(passwordIndex));
                        editLogin.setText(cursorLog.getString(loginIndex));
                        editName.setText(cursorLog.getString(NameIndex));
                        editSurname.setText(cursorLog.getString(SurnameIndex));
                        editBirtday.setText(cursorLog.getString(BirtdayIndex));
                        editAddress.setText(cursorLog.getString(AddressIndex));
                        index  = Integer.parseInt(cursorLog.getString(idIndex));

                        break;
                    }

                } while (cursorLog.moveToNext());
                cursorLog.close();
                bil = true;

            }
        }

    }
    @Override
    public void onClick(View view){
        if (editAddress.getText().toString().equals("")||editBirtday.getText().toString().equals("") || editSurname.getText().toString().equals("")||editName.getText().toString().equals("") || editLogin.getText().toString().equals("")||editPhone.getText().toString().equals("") || editPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
            return;
        }
        if (bil==false) {

            Cursor signCursor1 = database.query(DBHelper.People, null, null, null, null, null, null);

            boolean finded = false;
            if (signCursor1.moveToFirst()) {
                int usernameIndex = signCursor1.getColumnIndex(DBHelper.Login);
                do {
                    if (editLogin.getText().toString().equals(signCursor1.getString(usernameIndex))) {
                        Toast.makeText(this, "Введённый логин уже зарегистрирован", Toast.LENGTH_LONG).show();
                        finded = true;
                        break;
                    }
                } while (signCursor1.moveToNext());
            }
            if (!finded) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.Login, editLogin.getText().toString());
                contentValues.put(DBHelper.Password, editPassword.getText().toString());
                contentValues.put(DBHelper.Name, editName.getText().toString());
                contentValues.put(DBHelper.Phone, editPhone.getText().toString());
                contentValues.put(DBHelper.Birthday, editBirtday.getText().toString());
                contentValues.put(DBHelper.Surname, editSurname.getText().toString());
                contentValues.put(DBHelper.Address, editAddress.getText().toString());
                database.insert(DBHelper.People, null, contentValues);
                Toast.makeText(this, "Вы успешно зарегистрированы", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
            }
            signCursor1.close();

        }
        else {
            boolean finded = false;
            Cursor signCursor2 = database.query(DBHelper.People, null, null, null, null, null, null);

            if (editLogin.getText().toString()!=lis.l){

            if(signCursor2.moveToFirst()){
                int usernameIndex = signCursor2.getColumnIndex(DBHelper.Login);
                do{
                    if(editLogin.getText().toString().equals(signCursor2.getString(usernameIndex))){
                        Toast.makeText(this, "Введённый логин уже зарегистрирован", Toast.LENGTH_LONG).show();
                        finded = true;
                        break;
                    }
                }while (signCursor2.moveToNext());
            }}
            if(!finded){
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.Login, editLogin.getText().toString());
                contentValues.put(DBHelper.Password, editPassword.getText().toString());
                contentValues.put(DBHelper.Name, editName.getText().toString());
                contentValues.put(DBHelper.Phone, editPhone.getText().toString());
                contentValues.put(DBHelper.Birthday, editBirtday.getText().toString());
                contentValues.put(DBHelper.Surname, editSurname.getText().toString());
                contentValues.put(DBHelper.Address, editAddress.getText().toString());
                database.update(DBHelper.People, contentValues,DBHelper.KEY_ID + " = '" + index + "'", null);
                Toast.makeText(this, "Вы успешно зменили информацию", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
            }
            signCursor2.close();


        }
    }


}
