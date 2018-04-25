package com.stx.xhb.enjoylife.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xhb.core.base.BaseSwipeBackActivity;
import com.xhb.core.ui.SwipeBackLayout;
import com.stx.xhb.enjoylife.BuildConfig;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.utils.ShareUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 关于界面
 */
public class AboutActivity extends BaseSwipeBackActivity {

    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        initView();
    }

    //初始化视图控件
    private void initView() {
        tvVersion.setText("Version " + BuildConfig.VERSION_NAME);
        collapsingToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.this.onBackPressed();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_share:
                ShareUtils.share(this, R.string.share_text);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
