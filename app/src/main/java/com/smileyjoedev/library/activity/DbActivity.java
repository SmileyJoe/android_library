package com.smileyjoedev.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.smileyjoedev.library.R;
import com.smileyjoedev.library.activity.base.BaseActivity;
import com.smileyjoedev.library.handler.DbUserHandler;
import com.smileyjoedev.library.object.UserDb;

/**
 * Created by cody on 2016/04/04.
 */
public class DbActivity extends BaseActivity {

    private EditText mEditName;
    private EditText mEditSurname;
    private EditText mEditPhone;
    private EditText mEditNote;
    private EditText mEditDbId;

    private DbUserHandler mUserHandler;

    private UserDb mUser;

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, DbActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        setupViews();
        mUserHandler = new DbUserHandler(getBaseContext());
    }

    private void setupViews(){
        mEditName = (EditText) findViewById(R.id.edit_user_name);
        mEditSurname = (EditText) findViewById(R.id.edit_user_surname);
        mEditPhone = (EditText) findViewById(R.id.edit_user_phone);
        mEditNote = (EditText) findViewById(R.id.edit_user_note);
        mEditDbId = (EditText) findViewById(R.id.edit_user_db_id);

        findViewById(R.id.button_cancel).setOnClickListener(new OnCancelClick());
        findViewById(R.id.button_save).setOnClickListener(new OnSaveClick());
        findViewById(R.id.button_load).setOnClickListener(new OnLoadClick());
    }

    private void clearForm(){
        mEditName.setText("");
        mEditSurname.setText("");
        mEditPhone.setText("");
        mEditNote.setText("");
        mUser = null;
    }

    private void saveForm(){
        if(mUser == null){
            mUser = new UserDb();
        }

        mUser.setName(mEditName.getText().toString());
        mUser.setSurname(mEditSurname.getText().toString());
        mUser.setPhone(mEditPhone.getText().toString());
        mUser.setNote(mEditNote.getText().toString());

        long id = mUserHandler.save(mUser);
        clearForm();

        Toast.makeText(getBaseContext(),
                getString(R.string.success_user_db_saved,
                        Long.toString(id)),
                Toast.LENGTH_SHORT)
                .show();
    }

    private void loadUser(long dbId){
        mUser = mUserHandler.getDetails(dbId);

        if(mUser != null){
            mEditName.setText(mUser.getName());
            mEditSurname.setText(mUser.getSurname());
            mEditPhone.setText(mUser.getPhone());
            mEditNote.setText(mUser.getNote());
        } else {
            Toast.makeText(getBaseContext(), R.string.error_db_id_does_not_exist, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getNavMenuId() {
        return R.id.navigation_db;
    }

    private class OnCancelClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            clearForm();
        }
    }

    private class OnSaveClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            saveForm();
        }
    }

    private class OnLoadClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String dbIdString = mEditDbId.getText().toString().trim();

            try{
                long dbId = Long.parseLong(dbIdString);
                loadUser(dbId);
            } catch (ClassCastException e){
                Toast.makeText(getBaseContext(), R.string.error_db_id_not_long, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
