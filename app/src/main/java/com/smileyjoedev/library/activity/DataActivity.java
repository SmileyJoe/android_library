package com.smileyjoedev.library.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.smileyjoedev.data.Data;
import com.smileyjoedev.library.R;

/**
 * Created by cody on 2016/03/25.
 */
public class DataActivity extends AppCompatActivity {

    private SpannableStringBuilder mSpanBuilder;
    private TextView mTextData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        mSpanBuilder = new SpannableStringBuilder();
        mTextData = (TextView) findViewById(R.id.text_data);
        setDummyData();
        populate();
    }

    private void populate(){
        mTextData.setText(mSpanBuilder);
    }

    private void setDummyData(){
        Data data = new Data();

        section(R.string.cat_title_name, data.nextName());
        section(R.string.cat_title_email, data.nextEmail());
        section(R.string.cat_title_phone_number, data.nextPhoneNumber(), data.nextPhoneNumber(Data.PHONE_NUMBER_COUTRY_RSA));
        section(R.string.cat_title_word, data.nextWord());
        section(R.string.cat_title_sentence, data.nextSentence(3, 20));
        section(R.string.cat_title_paragraph, data.nextParagraph(2));
    }

    private void section(int titleResId, String... content){
        boldLine(titleResId);

        for(String string:content){
            line(string);
        }

        newLine();
    }

    private void boldLine(int lineResId){
        boldLine(getResources().getString(lineResId));
    }

    private void boldLine(String line){
        SpannableString span = new SpannableString(line);
        StyleSpan style = new StyleSpan(android.graphics.Typeface.BOLD);
        span.setSpan(style, 0, line.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSpanBuilder.append(span);
        newLine();
    }

    private void line(String line){
//        SpannableString span = new SpannableString(line);
        mSpanBuilder.append(line);
        newLine();
    }

    private void newLine(){
        mSpanBuilder.append(System.lineSeparator());
    }
}
