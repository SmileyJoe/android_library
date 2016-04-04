package com.smileyjoedev.library.object;

import com.smileyjoedev.database.DbObject;

/**
 * Created by cody on 2016/04/04.
 */
public class UserDb extends DbObject {

    private String mName;
    private String mSurname;
    private String mPhone;
    private String mNote;

    public void setName(String name) {
        mName = name;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public String getName() {
        return mName;
    }

    public String getSurname() {
        return mSurname;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getNote() {
        return mNote;
    }

    @Override
    public String toString() {
        return "UserDb{" +
                "mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mNote='" + mNote + '\'' +
                '}';
    }
}
