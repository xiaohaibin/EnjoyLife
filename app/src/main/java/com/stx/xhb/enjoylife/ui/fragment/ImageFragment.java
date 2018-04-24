package com.stx.xhb.enjoylife.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meikoz.core.base.BaseFragment;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.ImageEntity;
import com.stx.xhb.enjoylife.presenter.image.getImageContact;
import com.stx.xhb.enjoylife.presenter.image.getImagePresenterImpl;
import com.stx.xhb.enjoylife.ui.adapter.ImageRecyclerAdapter;
import com.stx.xhb.enjoylife.ui.widget.RecyclerViewNoBugStaggeredGridLayoutManger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 干货福利Fragment
 */
public class ImageFragment extends BaseFragment implements getImageContact.getImageView, XRecyclerView.LoadingListener {

    @Bind(R.id.recly_view)
    XRecyclerView mRecyclerView;
    private ArrayList<String> list;
    private ImageRecyclerAdapter recyclerAdapter;
    private int page = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        RecyclerViewNoBugStaggeredGridLayoutManger layoutManager = new RecyclerViewNoBugStaggeredGridLayoutManger(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setLoadingListener(this);
        list = new ArrayList<>();
        recyclerAdapter = new ImageRecyclerAdapter(getActivity(), R.layout.item_list_picture, list);
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onResponse(List<ImageEntity.ResultsBean> response) {
        onLoadComplete(page);
        for (int i = 0; i < response.size(); i++) {
            list.add(response.get(i).getUrl());
        }
    }

    @Override
    public void onFailure(String msg) {
        onLoadComplete(page);
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Class getLogicClazz() {
        return getImageContact.class;
    }

    private void onLoadComplete(int page) {
        if (page == 1) {
            list.clear();
            mRecyclerView.refreshComplete();
        } else {
            mRecyclerView.loadMoreComplete();
        }
    }

    @Override
    protected void onInitData2Remote() {
        super.onInitData2Remote();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page = 1;
        ((getImagePresenterImpl) mPresenter).getImageInfo(10, page);
    }

    @Override
    public void onLoadMore() {
        page++;
        ((getImagePresenterImpl) mPresenter).getImageInfo(10, page);
    }

}
