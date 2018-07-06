package com.stx.xhb.enjoylife.ui.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsResponse;
import com.stx.xhb.enjoylife.presenter.zhihu.getZhiHuNewsContract;
import com.stx.xhb.enjoylife.presenter.zhihu.getZhiHuNewsIPresenterImpl;
import com.stx.xhb.enjoylife.ui.adapter.NewsAdapter;
import com.stx.xhb.enjoylife.utils.ToastUtil;
import com.xhb.core.base.BaseFragment;

import butterknife.BindView;

/**
 * 知乎Fragment
 */
public class ZhihuFragment extends BaseFragment implements getZhiHuNewsContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recly_view)
    RecyclerView mReclyView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private String date = "";
    /**
     * 是否是第一次加载数据
     */
    private boolean isFirstLoad = false;
    private NewsAdapter mNewsAdapter;

    public static ZhihuFragment newInstance() {
        return new ZhihuFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected Class getLogicClazz() {
        return getZhiHuNewsContract.class;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mReclyView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary);
        mNewsAdapter = new NewsAdapter(R.layout.list_item_news,getActivity());
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mReclyView.setAdapter(mNewsAdapter);
        mNewsAdapter.openLoadAnimation();
        mNewsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMore();
            }
        }, mReclyView);
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        mSwipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onResponse(ZhiHuNewsResponse zhiHuNewsResponse) {
        onLoadComplete();
        mNewsAdapter.addData(zhiHuNewsResponse.getStories());
        date = zhiHuNewsResponse.getDate();
        if (isFirstLoad) {
            onLoadMore();
        }
    }

    @Override
    public void onFailure(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void onRefresh() {
        date = "latest";
        isFirstLoad = true;
        ((getZhiHuNewsIPresenterImpl) mPresenter).getNews(date);
    }

    public void onLoadMore() {
        if (!TextUtils.isEmpty(date)) {
            isFirstLoad = false;
            ((getZhiHuNewsIPresenterImpl) mPresenter).getNewsBefore(date);
        }
    }

    private void onLoadComplete() {
        if ("latest".equals(date)) {
            mNewsAdapter.setNewData(null);
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mNewsAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSwipeRefreshLayout!=null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
