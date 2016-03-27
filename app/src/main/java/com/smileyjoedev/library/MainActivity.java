package com.smileyjoedev.library;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smileyjoedev.library.activity.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public int getNavMenuId() {
        return R.id.navigation_home;
    }

}
