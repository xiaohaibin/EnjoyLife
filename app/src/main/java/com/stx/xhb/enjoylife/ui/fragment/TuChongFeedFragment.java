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
import com.stx.xhb.enjoylife.model.entity.TuchongImagResponse;
import com.stx.xhb.enjoylife.ui.activity.PhotoViewActivity;
import com.stx.xhb.enjoylife.ui.adapter.TuChongListAdapter;
import com.xhb.core.base.BaseFragment;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.presenter.tuchong.getFeedAppContact;
import com.stx.xhb.enjoylife.presenter.tuchong.getFeedAppIPresenterImpl;
import com.stx.xhb.enjoylife.ui.widget.RecyclerViewNoBugStaggeredGridLayoutManger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description： 图虫摄影
 */
public class TuChongFeedFragment extends BaseFragment implements getFeedAppContact.View, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recly_view)
    RecyclerView mRvTuChong;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int page = 1;
    private String posId = "";
    private TuChongListAdapter mTuChongListAdapter;

    public static TuChongFeedFragment newInstance() {
        return new TuChongFeedFragment();
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

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mTuChongListAdapter = new TuChongListAdapter(R.layout.list_item_list_tuchong);
        mTuChongListAdapter.openLoadAnimation();
        mRvTuChong.setAdapter(mTuChongListAdapter);

        mTuChongListAdapter.setOnImageItemClickListener(new TuChongListAdapter.OnImageItemClickListener() {
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
        mTuChongListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                ((getFeedAppIPresenterImpl) mPresenter).getFeedAppImage(page, "loadmore", posId);
            }
        },mRvTuChong);
    }

    @Override
    public void onResponse(List<TuchongImagResponse.FeedListBean> feedList, boolean isMore) {
        onLoadComplete(page);
        mTuChongListAdapter.setEnableLoadMore(isMore);
        posId = String.valueOf(feedList.get(feedList.size() - 1).getPost_id());
        mTuChongListAdapter.addData(feedList);
    }

    @Override
    public void onFailure(String msg) {
        onLoadComplete(page);
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onInitData2Remote() {
        super.onInitData2Remote();
        onRefresh();
    }

    @Override
    protected Class getLogicClazz() {
        return getFeedAppContact.class;
    }

    @Override
    public void onRefresh() {
        page = 1;
        ((getFeedAppIPresenterImpl) mPresenter).getFeedAppImage(page, "refresh", posId);
    }

    private void onLoadComplete(int page) {
        if (page == 1) {
            mTuChongListAdapter.setNewData(null);
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mTuChongListAdapter.loadMoreComplete();
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
