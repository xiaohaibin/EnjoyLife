package com.stx.xhb.enjoylife.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.TuChongWallPaperResponse;
import com.stx.xhb.enjoylife.presenter.tuchong.getWallPaperContract;
import com.stx.xhb.enjoylife.presenter.tuchong.getWallPaperPresenterImpl;
import com.stx.xhb.enjoylife.ui.activity.PhotoViewActivity;
import com.stx.xhb.enjoylife.ui.adapter.TuChongWallPaperAdapter;
import com.stx.xhb.enjoylife.ui.widget.RecyclerViewNoBugStaggeredGridLayoutManger;
import com.xhb.core.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description： 图虫摄影
 */
public class TuChongWallPaperFragment extends BaseFragment implements getWallPaperContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recly_view)
    RecyclerView mRvTuChong;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private int page = 1;
    private TuChongWallPaperAdapter mTuChongListAdapter;

    public static TuChongWallPaperFragment newInstance() {
        return new TuChongWallPaperFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mRvTuChong.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewNoBugStaggeredGridLayoutManger layoutManager = new RecyclerViewNoBugStaggeredGridLayoutManger(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary);
        mRvTuChong.setLayoutManager(layoutManager);

        mTuChongListAdapter = new TuChongWallPaperAdapter(R.layout.list_item_list_tuchong);
        mTuChongListAdapter.openLoadAnimation();
        mRvTuChong.setAdapter(mTuChongListAdapter);
        mTuChongListAdapter.setOnImageItemClickListener(new TuChongWallPaperAdapter.OnImageItemClickListener() {
            @Override
            public void setOnImageClick(View view, ArrayList<String> imageList) {
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                intent.putStringArrayListExtra("image", imageList);
                intent.putExtra("pos", 0);
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(), view, PhotoViewActivity.TRANSIT_PIC);
                try {
                    ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    startActivity(intent);
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mTuChongListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                ((getWallPaperPresenterImpl) mPresenter).getWallPaper(page);
            }
        }, mRvTuChong);
    }

    @Override
    public void onResponse(List<TuChongWallPaperResponse.FeedListBean> feedList, boolean isMore) {
        onLoadComplete(page);
        mTuChongListAdapter.loadMoreEnd();
        for (int i = 0; i < feedList.size(); i++) {
            TuChongWallPaperResponse.FeedListBean feedListBean = feedList.get(i);
            if ("post".equals(feedListBean.getType())) {
                mTuChongListAdapter.addData(feedListBean);
            }
        }
    }

    @Override
    public void onFailure(String msg) {
        onLoadComplete(page);
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        mSwipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    protected Class getLogicClazz() {
        return getWallPaperContract.class;
    }

    @Override
    public void onRefresh() {
        page = 1;
        ((getWallPaperPresenterImpl) mPresenter).getWallPaper(page);
    }

    private void onLoadComplete(int page) {
        if (page == 1) {
            mTuChongListAdapter.setNewData(null);
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mTuChongListAdapter.loadMoreComplete();
        }
    }
}
