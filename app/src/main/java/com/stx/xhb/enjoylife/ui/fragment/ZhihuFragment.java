package com.stx.xhb.enjoylife.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsResponse;
import com.stx.xhb.enjoylife.presenter.video.getVideoPresenterImpl;
import com.stx.xhb.enjoylife.presenter.zhihu.getZhiHuNewsContract;
import com.stx.xhb.enjoylife.presenter.zhihu.getZhiHuNewsPresenterImpl;
import com.stx.xhb.enjoylife.ui.adapter.NewsAdapter;
import com.stx.xhb.enjoylife.utils.ToastUtil;
import com.xhb.core.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 知乎Fragment
 */
public class ZhihuFragment extends BaseFragment implements getZhiHuNewsContract.View, XRecyclerView.LoadingListener {


    @Bind(R.id.recly_view)
    XRecyclerView mReclyView;
    private String date = "";
    private List<ZhiHuNewsResponse.StoriesBean> datas;

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
        datas = new ArrayList<>();
        mReclyView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReclyView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mReclyView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mReclyView.setArrowImageView(R.drawable.iconfont_downgrey);
        mReclyView.setLoadingListener(this);
        mReclyView.setAdapter(new NewsAdapter(datas,getActivity()));
        onRefresh();
    }

    @Override
    public void onResponse(ZhiHuNewsResponse zhiHuNewsResponse) {
        onLoadComplete();
        datas.addAll(zhiHuNewsResponse.getStories());
        date = zhiHuNewsResponse.getDate();
    }

    @Override
    public void onFailure(String msg) {
        onLoadMore();
        ToastUtil.show(msg);
    }

    @Override
    public void onRefresh() {
        date = "latest";
        ((getZhiHuNewsPresenterImpl) mPresenter).getNews(date);
    }

    @Override
    public void onLoadMore() {
        if (!TextUtils.isEmpty(date)) {
            ((getZhiHuNewsPresenterImpl) mPresenter).getNewsBefore(date);
        }
    }

    private void onLoadComplete() {
        if ("latest".equals(date)) {
            datas.clear();
            mReclyView.refreshComplete();
        } else {
            mReclyView.loadMoreComplete();
        }
    }
}
