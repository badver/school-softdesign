package com.softdesign.school.ui.activities;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String VISIBLE_KEY = "visible";
    public final static String THEME_KEY = "theme_id";
    private int mThemeId;
    private String TAG;
    private CheckBox mCheckBox;
    private EditText mEditText;
    private EditText mEditText2;
    private Toolbar mToolbar;
    private Button mButtonRed;
    private Button mButtonGreen;
    private Button mButtonBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        Lg.i(TAG, "======================================");
        Lg.i(TAG, "onCreate");
        Lg.i(TAG, "Theme: " + mThemeId);
        if (savedInstanceState != null) {
            mThemeId = savedInstanceState.getInt(THEME_KEY);
        } else {
            mThemeId = 0;
        }
        setTheme(mThemeId);
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

        setTitle("School Hometask 2");
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);

        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mButtonRed = (Button) findViewById(R.id.btn_red);
        mButtonRed.setOnClickListener(this);
        mButtonGreen = (Button) findViewById(R.id.btn_green);
        mButtonGreen.setOnClickListener(this);
        mButtonBlue = (Button) findViewById(R.id.btn_blue);
        mButtonBlue.setOnClickListener(this);

        setupToolbar();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.checkBox:
                Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
                if (mCheckBox.isChecked()) {
                    mEditText2.setVisibility(View.INVISIBLE);
                } else {
                    mEditText2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_red:
                mThemeId = R.style.Red;
                Toast.makeText(MainActivity.this, "Red!", Toast.LENGTH_SHORT).show();
                recreate();
                break;
            case R.id.btn_green:
                mThemeId = R.style.Green;
                Toast.makeText(MainActivity.this, "Green!", Toast.LENGTH_SHORT).show();
                recreate();
                break;
            case R.id.btn_blue:
                mThemeId = R.style.Blue;
                Toast.makeText(MainActivity.this, "Blue!", Toast.LENGTH_SHORT).show();
                recreate();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.i(TAG, "onSaveInstanceState");
        outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
        outState.putInt(THEME_KEY, mThemeId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.i(TAG, "onRestoreInstanceState");
        mEditText2.setVisibility(savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE);
        mThemeId = savedInstanceState.getInt(THEME_KEY);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(MainActivity.this, "Menu clicked", Toast.LENGTH_SHORT).show();
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
