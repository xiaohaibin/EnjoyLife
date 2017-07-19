package com.stx.xhb.enjoylife.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.android.core.adapter.RecyclerAdapter;
import com.android.core.adapter.RecyclerViewHolder;
import com.android.core.ui.BaseFragment;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.ImageEntity;
import com.stx.xhb.enjoylife.presenter.core.BaseDataView;
import com.stx.xhb.enjoylife.presenter.getImagePresenter;
import com.stx.xhb.enjoylife.presenter.impl.getImagePresenterImpl;
import com.stx.xhb.enjoylife.ui.activity.PhotoViewActivity;
import com.stx.xhb.enjoylife.ui.adapter.HomeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 干货福利Fragment
 */
public class ImageFragment extends BaseFragment implements BaseDataView<ImageEntity>, XRecyclerView.LoadingListener {

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
    protected void onInitView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mPresenter = getLogicImpl(getImagePresenter.class, this);
        recyclerAdapter = new HomeRecyclerAdapter(getActivity(), R.layout.item_list_picture, list);
        adapter = new ImageAdapter(getActivity(), R.layout.item_list_picture, list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLoadingListener(this);
        onRefresh();
    }

    @Override
    public void onLoadComplete(boolean isMore) {
        if (isMore)
            mRecyclerView.loadMoreComplete();
        else
            mRecyclerView.refreshComplete();
    }

    @Override
    public void onResponseLData(ImageEntity response, boolean isMore) {
        if (!response.isError()) {
            if (!isMore)
            list.clear();
            list.addAll(response.getResults());
            recyclerAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onRefresh() {
        ((getImagePresenterImpl) mPresenter).getImageInfo(false, 1);
    }

    @Override
    public void onLoadMore() {
        page++;
        ((getImagePresenterImpl) mPresenter).getImageInfo(true, page);
    }

    class ImageAdapter extends RecyclerAdapter<ImageEntity.ResultsBean> {
        public ImageAdapter(Context context, int layoutId, List<ImageEntity.ResultsBean> datas) {
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
                    intent.putExtra("pos",holder.getmPosition());
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
