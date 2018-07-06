package com.stx.xhb.enjoylife.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.stx.xhb.enjoylife.R;
import com.xhb.core.base.BaseActivity;

import butterknife.BindView;

/**
 * 启动页面
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.splash_view)
    ImageView splashView;
    int milliseconds = 500;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置状态栏透明
        StatusBarUtil.setTranslucent(this,0);
        Glide.with(this).load("https://pic1.zhimg.com/v2-9639852750175df1b80ed995729e64e8.jpg").into(splashView);
        //设置动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(milliseconds);//设置动画持续时间
        alphaAnimation.setFillAfter(true);
        splashView.setAnimation(alphaAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, milliseconds);
    }

}
