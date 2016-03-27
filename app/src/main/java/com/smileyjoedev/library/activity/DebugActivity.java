package com.smileyjoedev.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.smileyjoedev.debug.Debug;
import com.smileyjoedev.library.R;
import com.smileyjoedev.library.activity.base.BaseActivity;

/**
 * Created by cody on 2016/03/27.
 */
public class DebugActivity extends BaseActivity {

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, DebugActivity.class);
        return intent;
    }

    @Override
    public int getNavMenuId() {
        return R.id.navigation_debug;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        Debug.init(getBaseContext());

        setInformation();

        Debug.d("Test");
    }

    public void setInformation(){
        TextView textInformation = (TextView) findViewById(R.id.text_debug_information);

        String information = getString(R.string.text_debug_instructions) + System.lineSeparator() + System.lineSeparator();

        if(Debug.isEnabled()){
            information += getString(R.string.text_debug_enabled);
        } else {
            information += getString(R.string.text_debug_not_enabled);
        }

        information += System.lineSeparator() + System.lineSeparator();

        information += getString(R.string.text_debug_default_tag, Debug.getTag());

        textInformation.setText(information);

    }
}
