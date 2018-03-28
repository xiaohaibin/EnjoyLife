package com.stx.xhb.enjoylife.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meikoz.core.adapter.RecyclerAdapter;
import com.meikoz.core.adapter.RecyclerViewHolder;
import com.meikoz.core.base.BaseFragment;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.ImageEntity;
import com.stx.xhb.enjoylife.presenter.image.getImageContact;
import com.stx.xhb.enjoylife.presenter.image.getImagePresenterImpl;
import com.stx.xhb.enjoylife.ui.activity.PhotoViewActivity;
import com.stx.xhb.enjoylife.ui.adapter.HomeRecyclerAdapter;
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
    private List<ImageEntity.ResultsBean> list = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;
    private int page = 1;
    private ArrayList<String> imageList;
    private ImageAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RecyclerViewNoBugStaggeredGridLayoutManger layoutManager = new RecyclerViewNoBugStaggeredGridLayoutManger(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerAdapter = new HomeRecyclerAdapter(getActivity(), R.layout.item_list_picture, list);
        adapter = new ImageAdapter(getActivity(), R.layout.item_list_picture, list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLoadingListener(this);
    }

    @Override
    public void onResponse(List<ImageEntity.ResultsBean> response) {
        onLoadComplete(page);
        list.addAll(response);
        recyclerAdapter.notifyDataSetChanged();
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
        } else{
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
        page=1;
        ((getImagePresenterImpl) mPresenter).getImageInfo(10, page);
    }

    @Override
    public void onLoadMore() {
        page++;
        ((getImagePresenterImpl) mPresenter).getImageInfo(10, page);
    }

    class ImageAdapter extends RecyclerAdapter<ImageEntity.ResultsBean> {

        ImageAdapter(Context context, int layoutId, List<ImageEntity.ResultsBean> datas) {
            super(context, layoutId, datas);
        }


        @Override
        public void convert(final RecyclerViewHolder holder, final ImageEntity.ResultsBean item) {
            Glide.with(mContext).load(item.getUrl()).into((ImageView) holder.getView(R.id.iv));
            initData(list);
            holder.setOnClickListener(R.id.iv, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PhotoViewActivity.class);
                    intent.putStringArrayListExtra("image", imageList);
                    intent.putExtra("pos", holder.getmPosition());
                    startActivity(intent);
                }
            });
        }
    }

    private void initData(List<ImageEntity.ResultsBean> datas) {
        imageList = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            imageList.add(datas.get(i).getUrl());
        }
    }


}
