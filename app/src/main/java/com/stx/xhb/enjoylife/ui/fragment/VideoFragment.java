package com.stx.xhb.enjoylife.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;
import com.stx.xhb.enjoylife.presenter.video.getVideoContract;
import com.stx.xhb.enjoylife.presenter.video.getVideoPresenterImpl;
import com.stx.xhb.enjoylife.ui.activity.VideoDetailActivity;
import com.stx.xhb.enjoylife.ui.adapter.VideoRecyclerAdapter;
import com.xhb.core.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 视频推荐
 */
public class VideoFragment extends BaseFragment implements getVideoContract.getVideoView, XRecyclerView.LoadingListener {

    @Bind(R.id.recly_view)
    XRecyclerView mReclyView;
    private List<VideoResponse.IssueListEntity.ItemListEntity> list;
    private String nextPublishTime = "";
    private VideoRecyclerAdapter mRecyclerAdapter;
    private boolean isRefresh;

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        list = new ArrayList<>();
        mReclyView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReclyView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mReclyView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mReclyView.setArrowImageView(R.drawable.iconfont_downgrey);
        mReclyView.setLoadingListener(this);
        setLvAdapter();
        setListener();
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        mReclyView.refresh();
    }

    @Override
    protected Class getLogicClazz() {
        return getVideoContract.class;
    }

    @Override
    public void onResponse(VideoResponse response) {
        List<VideoResponse.IssueListEntity> issueList = response.getIssueList();
        if (issueList == null || issueList.isEmpty()) {
            return;
        }
        //刷新需要清除数据
        if (isRefresh) {
            list.clear();
            mReclyView.refreshComplete();
        }
        mReclyView.loadMoreComplete();
        for (int i = 0; i < issueList.size(); i++) {
            list.addAll(issueList.get(i).getItemList());
        }
        String nextUrl = response.getNextPageUrl();
        nextPublishTime = Uri.parse(nextUrl).getQueryParameter("date");
        notifydata();
    }

    @Override
    public void onFailure(String msg) {
        if (isRefresh) {
            mReclyView.refreshComplete();
        }
        mReclyView.loadMoreComplete();
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    private void setListener() {
        mRecyclerAdapter.setItemClickListener(new VideoRecyclerAdapter.setOnItemClickListener() {
            @Override
            public void onItemClick(View view,int position) {
                Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                VideoResponse.IssueListEntity.ItemListEntity.DataEntity data = list.get(position).getData();
                if (!"video".equals(list.get(position).getType())) {
                    return;
                }
                bundle.putString("title", data.getTitle());
                //获取到时间
                int duration = data.getDuration();
                int mm = duration / 60;//分
                int ss = duration % 60;//秒
                String second = "";//秒
                String minute = "";//分
                if (ss < 10) {
                    second = "0" + String.valueOf(ss);
                } else {
                    second = String.valueOf(ss);
                }
                if (mm < 10) {
                    minute = "0" + String.valueOf(mm);
                } else {
                    minute = String.valueOf(mm);//分钟
                }
                bundle.putString("time", "#" + data.getCategory() + " / " + minute + "'" + second + '"');
                bundle.putString("desc", data.getDescription());//视频描述
                bundle.putString("blurred", data.getCover().getBlurred());//模糊图片地址
                bundle.putString("feed", data.getCover().getFeed());//图片地址
                bundle.putString("video", data.getPlayUrl());//视频播放地址
                bundle.putInt("collect", data.getConsumption().getCollectionCount());//收藏量
                bundle.putInt("share", data.getConsumption().getShareCount());//分享量
                bundle.putInt("reply", data.getConsumption().getReplyCount());//回复数量
                intent.putExtras(bundle);

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(), view, VideoDetailActivity.TRANSIT_PIC);
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
    public void onRefresh() {
        isRefresh = true;
        ((getVideoPresenterImpl) mPresenter).getVideoInfo("", 2);
    }

    @Override
    public void onLoadMore() {
        isRefresh = false;
        ((getVideoPresenterImpl) mPresenter).getVideoInfo(nextPublishTime, 2);
    }

    public void notifydata() {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private void setLvAdapter() {
        mRecyclerAdapter = new VideoRecyclerAdapter(getActivity(), list);
        mReclyView.setAdapter(mRecyclerAdapter);
    }
}
