package com.smileyjoedev.library.handler;

import android.content.ContentValues;
import android.content.Context;

import com.smileyjoedev.database.Db;
import com.smileyjoedev.database.DbBase;
import com.smileyjoedev.library.helper.DbHelper;
import com.smileyjoedev.library.object.UserDb;

/**
 * Created by cody on 2016/04/04.
 */
public class DbUserHandler extends DbBase<UserDb> {

    private static final String TABLE = "user";
    private static final String COL_ID = Db.COL_ID;
    private static final String COL_NAME = TABLE + "_name";
    private static final String COL_SURNAME = TABLE + "_surname";
    private static final String COL_PHONE = TABLE + "_phone";
    private static final String COL_NOTE = TABLE + "_note";

    public DbUserHandler(Context context) {
        super(context, new DbHelper(context));
    }

    @Override
    protected String getTableName() {
        return TABLE;
    }

    @Override
    protected void setColoumns() {
        addColoumn(COL_ID);
        addColoumn(COL_NAME);
        addColoumn(COL_SURNAME);
        addColoumn(COL_PHONE);
        addColoumn(COL_NOTE);
    }

    @Override
    protected UserDb parseData() {
        UserDb user = defaultObject();

        user.setName(getColumnString(COL_NAME));
        user.setSurname(getColumnString(COL_SURNAME));
        user.setPhone(getColumnString(COL_PHONE));
        user.setNote(getColumnString(COL_NOTE));

        return user;
    }

    @Override
    protected ContentValues createContentValues(UserDb object) {
        ContentValues values = new ContentValues();

        values.put(COL_NAME, object.getName());
        values.put(COL_SURNAME, object.getSurname());
        values.put(COL_PHONE, object.getPhone());
        values.put(COL_NOTE, object.getNote());

        return values;
    }

    @Override
    protected String getQuery(String where) {
        String query = "SELECT %s, %s, %s, %s, %s FROM %s %s";

        return String.format(query, COL_ID, COL_NAME, COL_SURNAME, COL_PHONE, COL_NOTE, TABLE, where);
    }

    @Override
    protected UserDb defaultObject() {
        return new UserDb();
    }

    public static String getCreateStatement() {
        String statment = Db.createStatement(TABLE,
                Db.columnString(COL_NAME),
                Db.columnString(COL_SURNAME),
                Db.columnString(COL_PHONE),
                Db.columnString(COL_NOTE));

        return statment;
    }

    public static String getUpdateStatement(int oldVersion, int newVersion) {
        return null;
    }
}
