package com.smileyjoedev.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.smileyjoedev.debug.Debug;
import com.smileyjoedev.library.R;
import com.smileyjoedev.library.activity.base.BaseActivity;
import com.smileyjoedev.library.object.TestDebugObject;

import java.util.ArrayList;

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

        makeLogs();
    }

    private void makeLogs(){
        int[] intArray = new int[]{0,1,2,3,4,5,6,7,8,9};

        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("Element 1");
        stringArrayList.add("Element 2");
        stringArrayList.add("Element 3");
        stringArrayList.add("Element 4");

        ArrayList<TestDebugObject> customArrayList = new ArrayList<>();
        customArrayList.add(new TestDebugObject("Name 2", 5, true));
        customArrayList.add(new TestDebugObject("Name 3", 10, true));
        customArrayList.add(new TestDebugObject("Name 4", 7, false));
        customArrayList.add(new TestDebugObject("Name 5", 28, true));
        customArrayList.add(new TestDebugObject("Name 6", 54, false));

        Debug.d("----------- Log tests ----------");

        Debug.d("Debug");
        Debug.i("Information");
        Debug.w("Warning");
        Debug.e("Error");
        Debug.v("Verbose");

        Debug.trace(getBaseContext(), Debug.DEBUG);

        Debug.initMonitor(getBaseContext(), "Monitor test");
        Debug.monitor("log 1");
        Debug.endMonitor();

        Debug.d("More", "Then", "One", "String");
        Debug.d("Handles objects without conversion", 500, 500.99f, 0.98, true);

        Debug.d("Int[]", intArray);
        Debug.d("ArrayList<String>", stringArrayList);

        Debug.d("Custom object (TestDebugObject)", new TestDebugObject("Name 1", 20, false));

        Debug.d("ArrayList<TestDebugObject>", customArrayList);

        Debug.d("----------- Log tests ----------");
    }

    private void setInformation(){
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
