package com.stx.xhb.enjoylife.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.xhb.core.base.BaseActivity;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.config.Config;
import com.stx.xhb.enjoylife.ui.fragment.TuChongFragment;
import com.stx.xhb.enjoylife.ui.fragment.VideoFragment;
import com.stx.xhb.enjoylife.ui.fragment.ZhihuFragment;
import com.stx.xhb.enjoylife.utils.AppUtil;
import com.stx.xhb.enjoylife.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ctl_main)
    CoordinatorLayout ctlMain;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private List<android.app.Fragment> mFragments;
    private android.app.Fragment mCurrentFragment;
    private ArrayList<Integer> mTitles;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
    }

    private void initView() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setSupportActionBar(toolbar);
        //判断SDK版本是否是API 19
        if (AppUtil.getSDKVersionNumber() == Build.VERSION_CODES.KITKAT) {
            ctlMain.setFitsSystemWindows(false);
            setToolBar(toolbar, true, false, drawerLayout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            assert navView != null;
            navView.setNavigationItemSelectedListener(this);
        }
        initMenu();
    }

    //初始化菜单项
    private void initMenu() {
        ArrayList<Config.Channel> savedChannelList = new ArrayList<>();
        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
        Menu menu = navView.getMenu();
        menu.clear();
        Collections.addAll(savedChannelList, Config.Channel.values());
        for (int i = 0; i < savedChannelList.size(); i++) {
            MenuItem menuItem = menu.add(0, i, 0, savedChannelList.get(i).getTitle());
            mTitles.add(savedChannelList.get(i).getTitle());
            menuItem.setIcon(savedChannelList.get(i).getIcon());
            menuItem.setCheckable(true);
            addFragment(savedChannelList.get(i).name());
            if (i == 0) {
                menuItem.setChecked(true);
            }
        }
        navView.inflateMenu(R.menu.activity_main_drawer);
        navView.setNavigationItemSelectedListener(this);
        switchFragment(mFragments.get(0), getString(mTitles.get(0)));
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            assert drawer != null;
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else if ((System.currentTimeMillis() - exitTime) > 2000) {
                SnackbarUtil.ShortSnackbar(ctlMain, "再按一次退出乐享", getResources().getColor(R.color.colorWhite), getResources().getColor(R.color.colorGreen)).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id < mFragments.size()) {
            switchFragment(mFragments.get(id), getString(mTitles.get(id)));
        }
        switch (id) {
            case R.id.nav_setting:
                startActivity(new Intent(this, SetttingActivity.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 添加Fragment
     *
     * @param name
     */
    private void addFragment(String name) {
        switch (name) {
            case "ZHIHU":
                mFragments.add(new ZhihuFragment());
                break;
            case "VIDEO":
                mFragments.add(new VideoFragment());
                break;
            case "IMAGE":
                mFragments.add(new TuChongFragment());
                break;
            default:
                break;
        }
    }


    /**
     * Fragment切换
     *
     * @param fragment
     * @param title
     */
    private void switchFragment(android.app.Fragment fragment, String title) {
        Slide slideTransition;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Gravity.START部分机型崩溃java.lang.IllegalArgumentException: Invalid slide direction
            slideTransition = new Slide(Gravity.LEFT);
            slideTransition.setDuration(700);
            fragment.setEnterTransition(slideTransition);
            fragment.setExitTransition(slideTransition);
        }
        if (mCurrentFragment == null || !mCurrentFragment.getClass().getName().equals(fragment.getClass().getName())) {
            getFragmentManager().beginTransaction().replace(R.id.replace, fragment).commit();
            mCurrentFragment = fragment;
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle(title);
        }
    }


}
