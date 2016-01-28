package com.softdesign.school.ui.activities;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingsFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity {
    private static int theme = 0;
    private String TAG;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mNavigationDrawer;
    private Fragment mFragment;
    private FrameLayout mFrameContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        Lg.i(TAG, "======================================");
        Lg.i(TAG, "onCreate");
        Lg.i(TAG, "Theme: " + theme);
        setTheme(theme);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = this.getTheme();
            theme.resolveAttribute(R.color.colorPrimaryDark, typedValue, true);
            int color = typedValue.data;

            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setupToolbar();
        setupDrawer();

        if (savedInstanceState != null) {

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, new ProfileFragment()).commit();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Lg.i(TAG, "action bar creation");
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragment = new ProfileFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
                        break;
                    case R.id.drawer_contacts:
                        mFragment = new ContactsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
                        break;
                    case R.id.drawer_tasks:
                        mFragment = new TasksFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_tasks).setChecked(true);
                        break;
                    case R.id.drawer_team:
                        mFragment = new TeamFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
                        break;
                    case R.id.drawer_setting:
                        mFragment = new SettingsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_setting).setChecked(true);
                        break;
                }

                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, mFragment).addToBackStack(null).commit();
                }

                mNavigationDrawer.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.i(TAG, "onRestoreInstanceState");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mNavigationDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.i(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.i(TAG, "onRestart");
    }


}
