package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DBVersion =2;
    public static final String DBName = "contactDb";
    public static final String Name = "name";
    public static final String KEY_ID = "_id";
    public static final String Pol = "pol";
    public static final String Phone = "phone";
    public static final String Surname = "surname";
    public static final String Address = "address";
    public static final String People = "people";
    public static final String Birtday = "birthday";
    public static final String Login = "login";
    public static final String Password = "password";

    public static final String NameO = "nameOdegda";
    public static final String Prise = "prise";

    public static final String Odegda = "odegda";

    public static final String Korzina = "korzina";
    public static final String KO = "keyo";


    public DBHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + People + "(" + KEY_ID
                + " integer primary key," + Name + " text," + Phone + " text,"+ Birtday+ " text,"+ Surname + " text," +
                Address + " text," + Login + " text," + Password + " text" + ")");

        sqLiteDatabase.execSQL("create table " + Odegda + "(" + KEY_ID
                + " integer primary key," + NameO + " text," + Prise + " real,"+  Pol +"  TEXT CHECK( pol IN ('М','Ж','Н') )   NOT NULL DEFAULT 'Н'" + ")");

        sqLiteDatabase.execSQL("create table " + Korzina + "(" + KEY_ID
                + " integer primary key," + KO + " integer NOT NULL, FOREIGN KEY (keyo) REFERENCES auth(_id)" +  ")");


        sqLiteDatabase.execSQL("insert into " + Odegda + "(" +  NameO + " ," + Prise + " ,"+ Pol  + ")" + " values "+"('Иванов', 1234, 'М')");

        sqLiteDatabase.execSQL("insert into " + People + "(" +  Name + " ," + Phone + " ,"+ Birtday+ " ,"+ Pol +" ," + Surname + " ," +
                Address + " ," + Login + " ," + Password  + ")" + " values "+"('Иванов', '+79535599079', '2003.03.03','М','Иванов','Улицаулицавобще','admin','admin')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + People);


        onCreate(sqLiteDatabase);
    }
}
