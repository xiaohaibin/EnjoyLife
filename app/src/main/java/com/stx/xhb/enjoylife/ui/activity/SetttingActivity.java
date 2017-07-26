package com.stx.xhb.enjoylife.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.meikoz.core.base.BaseSwipeBackActivity;
import com.meikoz.core.ui.SwipeBackLayout;
import com.stx.xhb.enjoylife.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 设置界面
 */
public class SetttingActivity extends BaseSwipeBackActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static void start(Context context) {
        Intent intent = new Intent(context, SetttingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settting);
        ButterKnife.bind(this);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetttingActivity.this.onBackPressed();
            }
        });
    }
}
