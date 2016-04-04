package com.smileyjoedev.library.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smileyjoedev.library.handler.DbUserHandler;

/**
 * Created by cody on 2016/04/04.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME_BASE = "library";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME_BASE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbUserHandler.getCreateStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbUserHandler.getUpdateStatement(oldVersion, newVersion));
    }
}
