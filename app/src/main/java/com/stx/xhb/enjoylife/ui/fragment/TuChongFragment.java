package com.stx.xhb.enjoylife.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.enjoylife.ui.activity.PhotoViewActivity;
import com.stx.xhb.enjoylife.ui.adapter.TuChongListAdapter;
import com.xhb.core.base.BaseFragment;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.TuchongImagEntity;
import com.stx.xhb.enjoylife.presenter.tuchong.getFeedAppContact;
import com.stx.xhb.enjoylife.presenter.tuchong.getFeedAppPresenterImpl;
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
public class TuChongFragment extends BaseFragment implements XRecyclerView.LoadingListener, getFeedAppContact.View {

    @Bind(R.id.recly_view)
    XRecyclerView mRvTuChong;
    private int page = 1;
    private String posId = "";
    private List<TuchongImagEntity.FeedListBean> imgList;

    public static TuChongFragment newInstance() {
        return new TuChongFragment();
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
        mRvTuChong.setLayoutManager(layoutManager);
        mRvTuChong.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRvTuChong.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRvTuChong.setArrowImageView(R.drawable.iconfont_downgrey);
        mRvTuChong.setLoadingListener(this);
        imgList = new ArrayList<>();

        TuChongListAdapter tuChongListAdapter = new TuChongListAdapter(getActivity(), R.layout.list_item_list_tuchong,imgList);
        mRvTuChong.setAdapter(tuChongListAdapter);
        tuChongListAdapter.setOnImageItemClickListener(new TuChongListAdapter.setOnImageItemClickListener() {
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
    }

    @Override
    public void onResponse(List<TuchongImagEntity.FeedListBean> feedList, boolean isMore) {
        onLoadComplete(page);
        posId = String.valueOf(feedList.get(feedList.size() - 1).getPost_id());
        imgList.addAll(feedList);
        mRvTuChong.setLoadingMoreEnabled(isMore);
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
        ((getFeedAppPresenterImpl) mPresenter).getFeedAppImage(page, "refresh", posId);
    }

    @Override
    public void onLoadMore() {
        page++;
        ((getFeedAppPresenterImpl) mPresenter).getFeedAppImage(page, "loadmore", posId);
    }

    private void onLoadComplete(int page) {
        if (page == 1) {
            imgList.clear();
            mRvTuChong.refreshComplete();
        } else {
            mRvTuChong.loadMoreComplete();
        }
    }
}
