package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DBVersion =100000001;
    public static final String DBName = "magazinebk";
    public static final String Name = "name";
    public static final String KEY_ID = "_id";
    public static final String Janr = "janr";
    public static final String Phone = "telephone";
    public static final String Surname = "surname";
    public static final String Address = "address";
    public static final String People = "people";
    public static final String Birthday = "birthday";
    public static final String Login = "login";
    public static final String Password = "password";

    public static final String NameO = "nameobook";
    public static final String Avtor = "avtorbook";
    public static final String Prise = "prise";

    public static final String Book = "book";

    public static final String Jurnal = "jurnal";
    public static final String Namejur = "jurnalname";
    public static final String Izdateljur = "jurnalizdat";
    public static final String Prisejur = "jurnalcost";
    public static final String JanrJ = "janrjur";



    public DBHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + People + "(" + KEY_ID
                + " integer primary key," + Name + " text," + Phone + " text,"+ Birthday + " text,"+ Surname + " text," +
                Address + " text," + Login + " text," + Password + " text" + ")");

        sqLiteDatabase.execSQL("create table " + Book + "(" + KEY_ID
                + " integer primary key,"  + NameO + " text, " +  Avtor + " text, " + Prise + " text,"+  Janr + " text " + ")");

        sqLiteDatabase.execSQL("create table " + Jurnal + "(" + KEY_ID
                + " integer primary key, " +  Namejur + " text," +  Izdateljur + " text," + Prisejur + " text,"+  JanrJ + " text " + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists " + People);
        sqLiteDatabase.execSQL("drop table if exists " + Book);
        sqLiteDatabase.execSQL("drop table if exists " + Jurnal);


        onCreate(sqLiteDatabase);
    }
}
