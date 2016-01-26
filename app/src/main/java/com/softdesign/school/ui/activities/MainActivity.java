package com.softdesign.school.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import com.softdesign.school.R;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity {
    CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lg.i(this.getClass().getSimpleName(), "onCreate");
        setContentView(R.layout.activity_main);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.i(this.getClass().getSimpleName(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.i(this.getClass().getSimpleName(), "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.i(this.getClass().getSimpleName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.i(this.getClass().getSimpleName(), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.i(this.getClass().getSimpleName(), "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.i(this.getClass().getSimpleName(), "onRestart");
    }
}
