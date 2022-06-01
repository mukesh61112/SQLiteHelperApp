package com.example.sqlitehelperapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String TABLE_NAME="COUNTRIES";
    public static final String ID="id";
    public static final String SUBJECT="subject";
    public static final String DESC="description";
    static final String DB_NAME="mukesh.db";
    static  final int DB_VERSION=1;
    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+ID+
            "INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT+
            "TEXT NOT NULL, "+DESC+" TEXT);";

    public DatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
