package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DBVersion =3;
    public static final String DBName = "magazineod";
    public static final String Name = "name";
    public static final String KEY_ID = "_id";
    public static final String Pol = "sex";
    public static final String Phone = "telephone";
    public static final String Surname = "surname";
    public static final String Address = "address";
    public static final String People = "people";
    public static final String Birthday = "birthday";
    public static final String Login = "login";
    public static final String Password = "password";

    public static final String Img = "image";
    public static final String NameO = "nameOdegda";
    public static final String Prise = "prise";

    public static final String Odeg = "odegda";

    public static final String Korzina = "korzina";
    public static final String KO = "keyo";


    public DBHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + People + "(" + KEY_ID
                + " integer primary key," + Name + " text," + Phone + " text,"+ Birthday + " text,"+ Surname + " text," +
                Address + " text," + Login + " text," + Password + " text" + ")");

        sqLiteDatabase.execSQL("create table " + Odeg + "(" + KEY_ID
                + " integer primary key," + Img + "image, " + NameO + " text," + Prise + " text,"+  Pol +"  text" + ")");

        sqLiteDatabase.execSQL("create table " + Korzina + "(" + KEY_ID
                + " integer primary key," + KO + " text" +  ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists " + People);
        sqLiteDatabase.execSQL("drop table if exists " + Odeg);
        sqLiteDatabase.execSQL("drop table if exists " + Korzina);


        onCreate(sqLiteDatabase);
    }
}
