package com.example.sqlitehelperapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {


    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c)
    {
        context=c;
    }

    public DBManager open() throws SQLException
    {
        databaseHelper=new DatabaseHelper(context);
        database=databaseHelper.getReadableDatabase();
        return  this;
    }

    public  void close(){ databaseHelper.close();}

    public  void insert(String name,String desc)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT,name);
        contentValues.put(DatabaseHelper.DESC,desc);
        database.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }

    public Cursor fetch()
    {
        String[] columns=new String[]{DatabaseHelper.ID,
         DatabaseHelper.SUBJECT,DatabaseHelper.DESC};

        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor!=null)
            cursor.moveToFirst();
        return  cursor;
    }


    public int update(long id,String name,String desc)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT,name);
        contentValues.put(DatabaseHelper.DESC,desc);

        int i=database.update(DatabaseHelper.TABLE_NAME,
                contentValues,DatabaseHelper.ID+
                " = " + id, null);
        return  i;
    }

    public  void delete(long id)
    {
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.ID +
                " = "+ id,null);
    }
}
