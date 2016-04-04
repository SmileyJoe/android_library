package com.smileyjoedev.library.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.smileyjoedev.library.MainActivity;
import com.smileyjoedev.library.R;
import com.smileyjoedev.library.activity.DbActivity;

/**
 * Created by cody on 2016/03/27.
 */
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerMain;

    public int getNavMenuId(){
        return -1;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(R.layout.activity_base);
        FrameLayout frameContentMain = (FrameLayout) findViewById(R.id.frame_content_main);
        LayoutInflater.from(getBaseContext()).inflate(layoutResId, frameContentMain, true);
        setupNavigation();
    }

    private void setupNavigation(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationMain = (NavigationView) findViewById(R.id.navigation_main);
        navigationMain.setNavigationItemSelectedListener(this);

        mDrawerMain = (DrawerLayout) findViewById(R.id.drawer_main);
        DrawerToggle drawerToggle = new DrawerToggle(this, mDrawerMain, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerMain.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerMain.closeDrawers();
        if(item.getItemId() != getNavMenuId()) {
            if(item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }

            Intent intent = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = MainActivity.getIntent(getBaseContext());
                    break;
                case R.id.navigation_db:
                    intent = DbActivity.getIntent(getBaseContext());
                    break;
                default:
                    break;
            }

            if (intent != null) {
                startActivity(navMenuIntent(intent));
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private Intent navMenuIntent(Intent intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    private class DrawerToggle extends ActionBarDrawerToggle{
        public DrawerToggle(Activity activity, DrawerLayout drawerLayout, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
            super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        public DrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
            super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
        }
    }
}
