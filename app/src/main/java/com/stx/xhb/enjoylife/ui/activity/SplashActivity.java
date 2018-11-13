package com.stx.xhb.enjoylife.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.stx.xhb.enjoylife.R;
import com.xhb.core.base.BaseActivity;

import butterknife.BindView;

/**
 * 启动页面
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.splash_view)
    FrameLayout splashView;
    /**动画时间 1000ms*/
    private static final int ANIMATOR_DURATION = 2000;
    private ObjectAnimator alphaAnimIn;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置状态栏透明
        StatusBarUtil.setTranslucent(this,0);
//        GlideApp.with(this).load("https://pic1.zhimg.com/v2-9639852750175df1b80ed995729e64e8.jpg").dontAnimate().into(ivSplash);
        //设置动画
        alphaAnimIn = ObjectAnimator.ofFloat(splashView, "alpha", 0f, 1f);

        alphaAnimIn.setDuration(ANIMATOR_DURATION);

        alphaAnimIn.start();
        alphaAnimIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                jumpToMain();
            }
        });
    }

    private void jumpToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.splash_fade_in, R.anim.splash_fade_out);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (alphaAnimIn != null) {
            alphaAnimIn.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alphaAnimIn != null) {
            alphaAnimIn.cancel();
            alphaAnimIn.removeAllListeners();
            alphaAnimIn = null;
        }
    }

}
