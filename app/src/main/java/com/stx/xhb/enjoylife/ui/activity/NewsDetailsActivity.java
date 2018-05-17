package com.stx.xhb.enjoylife.ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsContentResponse;
import com.stx.xhb.enjoylife.presenter.zhihu.getNewsContentContract;
import com.stx.xhb.enjoylife.presenter.zhihu.getNewsContentPresenterImpl;
import com.stx.xhb.enjoylife.utils.ShareUtils;
import com.stx.xhb.enjoylife.utils.ToastUtil;
import com.stx.xhb.enjoylife.utils.WebHtmlUtil;
import com.xhb.core.base.BaseActivity;
import com.xhb.core.imageloader.ImageLoaderProxy;
import com.xhb.core.util.ScreenUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 页面详情
 *
 * @author Mr.xiao
 */
public class NewsDetailsActivity extends BaseActivity implements getNewsContentPresenterImpl.View {

    private static final String SHARE_FROM_ZHIHU = " 分享自知乎网";
    @Bind(R.id.web_webview)
    WebView webView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_banner)
    ImageView mIvBanner;
    private ZhiHuNewsContentResponse mZhiHuNewsContentResponse;
    private ProgressDialog mProgressDialog;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_web_details;
    }

    @Override
    protected Class getLogicClazz() {
        return getNewsContentContract.class;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvBanner.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(this) / 2 + 70));
        initWeb();
        init();
    }

    @Override
    public void onResponse(ZhiHuNewsContentResponse response) {
        mZhiHuNewsContentResponse = response;
        if (response.getCss() != null && !response.getCss().isEmpty()) {
            showWebdata(response.getBody(), response.getCss().get(0));
        } else {
            showWebdata(response.getBody(), "");
        }
        Glide.with(this).load(response.getImage()).into(mIvBanner);
    }

    @Override
    public void onFailed(String msg) {
        ToastUtil.show(msg);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWeb() {
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        //隐藏缩放控件
        settings.setDisplayZoomControls(false);
        //解决HTTPS协议下出现的mixed content问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(getCacheDir().getPath());
        settings.setAppCacheEnabled(true);
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("data")) {
                String data = bundle.getString("data");
                ((getNewsContentPresenterImpl) mPresenter).getNewsContent(data);
            }
            if (bundle.containsKey("title")) {
                String title = bundle.getString("title");
                getSupportActionBar().setTitle(title);
            }
        }
    }

    private void showWebdata(String data, String css) {
        if (webView != null) {
            webView.loadData(WebHtmlUtil.htmlText(data, css), "text/html; charset=utf-8", "utf-8");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
        return true;
    }

    public static void start(Context context, String data, String title) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        bundle.putString("data", data);
        bundle.putString("title", title);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    //点击分享
    @OnClick(R.id.article_share)
    public void onClick() {
        if (mZhiHuNewsContentResponse != null) {
            ShareUtils.share(this, mZhiHuNewsContentResponse.getTitle() + " " + mZhiHuNewsContentResponse.getShare_url() + SHARE_FROM_ZHIHU);
        }
    }

    ///////////////////////////////////解决webView耗电的问题///////////////////////////

    @Override
    protected void onPause() {
        super.onPause();
        webView.pauseTimers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.resumeTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((getNewsContentPresenterImpl) mPresenter).cancleNetWork();
    }

    @Override
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("正在加载...");
        }
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
