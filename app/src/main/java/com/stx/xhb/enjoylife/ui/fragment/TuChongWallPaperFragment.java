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
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description： 图虫摄影
 */
public class TuChongWallPaperFragment extends BaseFragment implements XRecyclerView.LoadingListener,getWallPaperContract.View {

    @Bind(R.id.recly_view)
    XRecyclerView mRvTuChong;
    private int page = 1;
    private List<TuChongWallPaperResponse.FeedListBean> imgList;

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
        mRvTuChong.setLayoutManager(layoutManager);
        mRvTuChong.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRvTuChong.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRvTuChong.setArrowImageView(R.drawable.iconfont_downgrey);
        mRvTuChong.setLoadingListener(this);
        imgList = new ArrayList<>();

        TuChongWallPaperAdapter tuChongListAdapter = new TuChongWallPaperAdapter(getActivity(), R.layout.list_item_list_tuchong,imgList);
        mRvTuChong.setAdapter(tuChongListAdapter);
        tuChongListAdapter.setOnImageItemClickListener(new TuChongWallPaperAdapter.OnImageItemClickListener() {
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
    public void onResponse(List<TuChongWallPaperResponse.FeedListBean> feedList, boolean isMore) {
        onLoadComplete(page);
        mRvTuChong.setLoadingMoreEnabled(isMore);
        for (int i = 0; i < feedList.size(); i++) {
            TuChongWallPaperResponse.FeedListBean feedListBean = feedList.get(i);
            if ("post".equals(feedListBean.getType())) {
                imgList.add(feedListBean);
            }
        }
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
        return getWallPaperContract.class;
    }

    @Override
    public void onRefresh() {
        page = 1;
        ((getWallPaperPresenterImpl) mPresenter).getWallPaper(page);
    }

    @Override
    public void onLoadMore() {
        page++;
        ((getWallPaperPresenterImpl) mPresenter).getWallPaper(page);
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
