package com.smileyjoedev.library.object;

/**
 * Created by cody on 2016/03/27.
 */
public class TestDebugObject {

    private String mName;
    private int mAge;
    private boolean mIsRegistered;

    public TestDebugObject(String name, int age, boolean isRegistered) {
        mName = name;
        mAge = age;
        mIsRegistered = isRegistered;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public void setRegistered(boolean registered) {
        mIsRegistered = registered;
    }

    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }

    public boolean isRegistered() {
        return mIsRegistered;
    }

    @Override
    public String toString() {
        return "TestDebugObject{" +
                "mName='" + mName + '\'' +
                ", mAge=" + mAge +
                ", mIsRegistered=" + mIsRegistered +
                '}';
    }
}
