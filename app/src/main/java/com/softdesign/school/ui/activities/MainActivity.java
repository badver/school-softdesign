package com.softdesign.school.ui.activities;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
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

    public final static String THEME_KEY = "theme_id";
    public final static String EXTRA_IMAGE = "extra_image";
    private int mThemeId;
    private String TAG;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mNavigationDrawer;
    private Fragment mFragment;
    private FrameLayout mFrameContainer;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBar;

    private boolean isCollapsed;

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

        mAppBar = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFrameContainer = (FrameLayout) findViewById(R.id.main_frame_container);

        setupToolbar();
        setupDrawer();

        if (savedInstanceState == null) {
            mFragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, mFragment).commit();
            isCollapsed = false;
        }
    }

    private void setDrag(final boolean isDrag, AppBarLayout appBar) {
        Lg.d(TAG, "Drag is " + isDrag);
        ((CoordinatorLayout.LayoutParams) appBar.getLayoutParams()).setBehavior(new AppBarLayout.Behavior());

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();

        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) lp.getBehavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return isDrag;
            }
        });
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Lg.i(TAG, "action bar creation");
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    }

    public void lockAppBar(boolean collapse) {
        if (isCollapsed != collapse) {
            isCollapsed = collapse;
            mAppBar.setExpanded(!collapse, !collapse);
            if (collapse) {
                AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout mAppBar, int verticalOffset) {
                        if (mCollapsingToolbarLayout.getHeight() + verticalOffset <= ViewCompat.getMinimumHeight(mCollapsingToolbarLayout) + getStatusBarHeight()) {
                            lockToolbar();
                            mAppBar.removeOnOffsetChangedListener(this);
                        }
                    }
                };
                mAppBar.addOnOffsetChangedListener(mListener);
            } else {
                unlockToolbar();
            }
            setDrag(!collapse, mAppBar);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void lockToolbar() {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mCollapsingToolbarLayout.getLayoutParams();
        params.setScrollFlags(0);
        mCollapsingToolbarLayout.setLayoutParams(params);
    }

    private void unlockToolbar() {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mCollapsingToolbarLayout.getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        mCollapsingToolbarLayout.setLayoutParams(params);
    }

    private void setupDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragment = new ProfileFragment();
                        mCollapsingToolbarLayout.setTitle(getString(R.string.fragment_profile_text_name));
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
                        break;
                    case R.id.drawer_contacts:
                        mFragment = new ContactsFragment();
                        mCollapsingToolbarLayout.setTitle(getString(R.string.drawer_contacts));
                        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
                        break;
                    case R.id.drawer_team:
                        mFragment = new TeamFragment();
                        mCollapsingToolbarLayout.setTitle(getString(R.string.drawer_team));
                        mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
                        break;
                    case R.id.drawer_tasks:
                        mFragment = new TasksFragment();
                        mCollapsingToolbarLayout.setTitle(getString(R.string.drawer_tasks));
                        mNavigationView.getMenu().findItem(R.id.drawer_tasks).setChecked(true);
                        break;
                    case R.id.drawer_setting:
                        mFragment = new SettingsFragment();
                        mCollapsingToolbarLayout.setTitle(getString(R.string.drawer_setting));
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
    public void onBackPressed() {
        super.onBackPressed();
        switch (mFrameContainer.getChildAt(0).getId()) {
            case R.id.fragment_profile:
                mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
                break;
            case R.id.fragment_contacts:
                mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
                break;
            case R.id.fragment_tasks:
                mNavigationView.getMenu().findItem(R.id.drawer_tasks).setChecked(true);
                break;
            case R.id.fragment_team:
                mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
                break;
            case R.id.fragment_settings:
                mNavigationView.getMenu().findItem(R.id.drawer_setting).setChecked(true);
                break;
        }
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
