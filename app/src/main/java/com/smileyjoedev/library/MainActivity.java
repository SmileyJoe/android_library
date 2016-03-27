package com.smileyjoedev.library;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.smileyjoedev.data.Data;

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

        Data data = new Data();
        Log.d("TestThings", data.nextName());
        Log.d("TestThings", data.nextParagraph(2));
        Log.d("TestThings", data.nextWord());
        Log.d("TestThings", data.nextWord(true));
        Log.d("TestThings", data.nextSentence(5, 10));
        Log.d("TestThings", data.nextSentence(2, 20));
        Log.d("TestThings", data.nextPhoneNumber());
        Log.d("TestThings", data.nextPhoneNumber(Data.PHONE_NUMBER_COUTRY_RSA));
        Log.d("TestThings", data.nextEmail());
        Log.d("TestThings", data.nextEmail("Tom Johns"));
        Log.d("TestThings", data.nextWebsite());
    }

    @Override
    public int getNavMenuId() {
        return R.id.navigation_home;
    }

}
