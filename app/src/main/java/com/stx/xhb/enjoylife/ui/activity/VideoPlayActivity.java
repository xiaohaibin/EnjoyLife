package com.stx.xhb.enjoylife.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.listener.OnTransitionListener;
import com.stx.xhb.enjoylife.listener.SampleVideoPlayListener;

/**
 * 采用IJKPlayer播放器播放视频
 */
public class VideoPlayActivity extends AppCompatActivity {

    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";
    public final static String VIDEO_URL = "URL";
    public final static String VIDEO_TITLE = "title";
    public final static String VIDEO_IMAGE = "image";

    private StandardGSYVideoPlayer  mVideoPlayer;
    private OrientationUtils orientationUtils;

    private boolean isTransition;
    private boolean isPlay;
    private boolean isPause;
    private Transition transition;
    private String mTitle;
    private String mUrl;
    private String mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        StatusBarUtil.setTranslucent(this);
        initView();
        Intent intent = getIntent();
        if (intent.hasExtra("video")) {
            Bundle bundle = intent.getBundleExtra("video");
            if (bundle.containsKey(TRANSITION)) {
                isTransition = bundle.getBoolean(TRANSITION);
            }
            if (bundle.containsKey(VIDEO_TITLE)) {
                mTitle = bundle.getString(VIDEO_TITLE);
            }
            if (bundle.containsKey(VIDEO_URL)) {
                mUrl = bundle.getString(VIDEO_URL);
            }
            if (bundle.containsKey(VIDEO_IMAGE)) {
                mImage = bundle.getString(VIDEO_IMAGE);
            }
        }
        init();
    }


    private void initView() {
        mVideoPlayer = (StandardGSYVideoPlayer) findViewById(R.id.video_player);
    }

    private void init() {
        //需要路径的
        mVideoPlayer.setUp(mUrl, true, "");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this).load(mImage).into(imageView);
        mVideoPlayer.setThumbImageView(imageView);

        //增加title
        mVideoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        mVideoPlayer.getTitleTextView().setText(mTitle);
        mVideoPlayer.getTitleTextView().setSingleLine(true);
        mVideoPlayer.getTitleTextView().setEllipsize(TextUtils.TruncateAt.END);

        //设置返回键
        mVideoPlayer.getBackButton().setVisibility(View.VISIBLE);

        //设置旋转
        orientationUtils = new OrientationUtils(this, mVideoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        mVideoPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        mVideoPlayer.setRotateViewAuto(false);
        mVideoPlayer.setLockLand(false);
        mVideoPlayer.setShowFullAnimation(false);
        mVideoPlayer.setNeedLockFull(true);


        //设置全屏按键功能
        mVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                mVideoPlayer.startWindowFullscreen(VideoPlayActivity.this, true, true);
            }
        });

        mVideoPlayer.setBottomProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_progress));
        mVideoPlayer.setDialogVolumeProgressBar(getResources().getDrawable(R.drawable.video_new_volume_progress_bg));
        mVideoPlayer.setDialogProgressBar(getResources().getDrawable(R.drawable.video_new_progress));
        mVideoPlayer.setBottomShowProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_seekbar_progress),
                getResources().getDrawable(R.drawable.video_new_seekbar_thumb));
        mVideoPlayer.setDialogProgressColor(getResources().getColor(R.color.colorAccent), -11);


        //设置返回按键功能
        mVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mVideoPlayer.setStandardVideoAllCallBack(new SampleVideoPlayListener(){
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        mVideoPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });

        //过渡动画
        initTransition();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
        mVideoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    private void initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(mVideoPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            mVideoPlayer.startPlayLogic();
        }
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
        } else {
            finish();
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(onTransitionListener);
            return true;
        }
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!mVideoPlayer.isIfCurrentIsFullscreen()) {
                    mVideoPlayer.startWindowFullscreen(VideoPlayActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (mVideoPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }


    OnTransitionListener onTransitionListener = new OnTransitionListener() {

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onTransitionEnd(Transition transition) {
            super.onTransitionEnd(transition);
            mVideoPlayer.startPlayLogic();
            transition.removeListener(onTransitionListener);
        }
    };
}
