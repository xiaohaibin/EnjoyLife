package com.stx.xhb.enjoylife.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsContentResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;
import com.stx.xhb.enjoylife.utils.ToastUtil;
import com.stx.xhb.enjoylife.utils.WebHtmlUtil;
import com.xhb.core.base.BaseActivity;
import com.xhb.core.base.BaseSwipeBackActivity;
import com.xhb.core.ui.SwipeBackLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 页面详情
 *
 * @author Mr.xiao
 */
public class NewsDetailsActivity extends BaseActivity {

    @Bind(R.id.web_webview)
    WebView webView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private static final String SHARE_FROM_ZHIHU = " 分享自知乎网";
    private ZhiHuNewsContentResponse mZhiHuNewsContentResponse;
    private Call<ZhiHuNewsContentResponse> mZhiHuNewsContent;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_web_details;
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
        initWeb();
        init();
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
                getNewsDetails(data);
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

    public void getNewsDetails(String data) {
        mZhiHuNewsContent = ApiManager.ApiFactory.creatZhiHuApi().getZhiHuNewsContent(data);
        mZhiHuNewsContent.enqueue(new Callback<ZhiHuNewsContentResponse>() {
            @Override
            public void onResponse(Call<ZhiHuNewsContentResponse> call, Response<ZhiHuNewsContentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mZhiHuNewsContentResponse = response.body();
                    if (response.body().getCss() != null && !response.body().getCss().isEmpty()) {
                        showWebdata(response.body().getBody(), response.body().getCss().get(0));
                    } else {
                        showWebdata(response.body().getBody(), "");
                    }
                } else {
                    ToastUtil.show(response.message());
                }
            }

            @Override
            public void onFailure(Call<ZhiHuNewsContentResponse> call, Throwable t) {
                ToastUtil.show(t.getMessage());
            }
        });
    }

    private void shareQuestion(Context context, String questionTitle, String questionUrl) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_TEXT,
                questionTitle + " " + questionUrl + SHARE_FROM_ZHIHU);
        context.startActivity(Intent.createChooser(share, context.getString(R.string.share_to)));
    }

    //点击分享
    @OnClick(R.id.article_share)
    public void onClick() {
        if (mZhiHuNewsContentResponse != null) {
            shareQuestion(this, mZhiHuNewsContentResponse.getTitle(), mZhiHuNewsContentResponse.getShare_url());
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
        if (mZhiHuNewsContent != null) {
            mZhiHuNewsContent.cancel();
        }
    }
}
