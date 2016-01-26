package com.softdesign.school.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String VISIBLE_KEY = "visible";

    private String TAG;
    private CheckBox mCheckBox;
    private EditText mEditText;
    private EditText mEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        Lg.i(TAG, "======================================");
        Lg.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        setTitle("School Hometask 2");
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);

        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);
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
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.i(TAG, "onSaveInstanceState");
        outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.i(TAG, "onRestoreInstanceState");
        mEditText2.setVisibility(savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE);
    }
}
