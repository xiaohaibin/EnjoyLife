package com.stx.xhb.enjoylife.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.stx.xhb.enjoylife.utils.ShareUtils;
import com.xhb.core.base.BaseSwipeBackActivity;
import com.xhb.core.ui.SwipeBackLayout;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.utils.NetConnectedUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoDetailActivity extends BaseSwipeBackActivity {

    @Bind(R.id.video_detail_iv)
    ImageView videoDetailIv;
    @Bind(R.id.video_detail_ivmo)
    ImageView videoDetailIvmo;
    @Bind(R.id.video_detail_title)
    TextView videoDetailTitle;
    @Bind(R.id.video_detail_time)
    TextView videoDetailTime;
    @Bind(R.id.video_detail_desc)
    TextView videoDetailDesc;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String video;
    private String title;
    private String mFeed;
    public static final String TRANSIT_PIC = "transit_picture";

    public static void start(Context context) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("视频详情");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ViewCompat.setTransitionName(videoDetailIv, VideoDetailActivity.TRANSIT_PIC);
        initData();
    }

    //初始化数据
    private void initData() {
        //背景图片
        mFeed = getIntent().getStringExtra("feed");
        title = getIntent().getStringExtra("title");
        String time = getIntent().getStringExtra("time");//时间
        String desc = getIntent().getStringExtra("desc");//视频详情
        String blurred = getIntent().getStringExtra("blurred");//模糊图片
        video = getIntent().getStringExtra("video");//视频播放地址
        //给控件设置数据
        Glide.with(this).load(mFeed).diskCacheStrategy(DiskCacheStrategy.ALL).into(videoDetailIv);
        videoDetailTitle.setText(title);
        videoDetailTime.setText(time);
        videoDetailDesc.setText(desc);
        Glide.with(this).load(blurred).diskCacheStrategy(DiskCacheStrategy.ALL).into(videoDetailIvmo);
    }

    @OnClick({R.id.video_paly, R.id.article_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_paly:
                if (NetConnectedUtils.isNetConnected(this)) {
                    Intent intent = new Intent(this, VideoPlayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(VideoPlayActivity.VIDEO_URL, video);
                    bundle.putString(VideoPlayActivity.VIDEO_TITLE, title);
                    bundle.putString(VideoPlayActivity.VIDEO_IMAGE, mFeed);
                    bundle.putBoolean(VideoPlayActivity.TRANSITION, true);
                    intent.putExtra("video", bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "网络异常，请稍后再试", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.article_share:
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(video)) {
                    ShareUtils.share(this, title + " " + video + "(分享自开眼视频)");
                }
                break;
            default:
                break;
        }

    }
}
