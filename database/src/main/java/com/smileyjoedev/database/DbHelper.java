package com.smileyjoedev.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME_BASE = "challengeaccepted";
    public static final int DB_VERSION = 12;

    public DbHelper(Context context){
        super(context, DB_NAME_BASE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
    }
}