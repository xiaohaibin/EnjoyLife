package com.stx.xhb.enjoylife.ui.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.android.core.control.Glides;
import com.android.core.control.HandlerTip;
import com.android.core.control.StatusBarUtil;
import com.android.core.ui.BaseActivity;
import com.stx.xhb.enjoylife.R;

import butterknife.Bind;

/**
 * 启动页面
 */
public class SplashActivity extends BaseActivity  {
    @Bind(R.id.splash_view)
    ImageView splashView;
    int milliseconds = 1500;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onInitView() {
        //设置状态栏透明
        StatusBarUtil.setTranslucentBackground(this);
        //设置缩放动画
        ScaleAnimation animation = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(milliseconds);//设置动画持续时间
        animation.setFillAfter(true);
        splashView.setAnimation(animation);
        Glides.getInstance().loadAnima(this, "https://pic1.zhimg.com/v2-9639852750175df1b80ed995729e64e8.jpg", R.anim.splash_anim, R.drawable.splash_background,splashView);
        HandlerTip.getInstance().postDelayed(milliseconds, new HandlerTip.HandlerCallback() {
            @Override
            public void postDelayed() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
