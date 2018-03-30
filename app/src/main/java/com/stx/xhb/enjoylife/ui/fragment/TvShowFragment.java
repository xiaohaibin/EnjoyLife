package com.stx.xhb.enjoylife.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.meikoz.core.base.BaseFragment;
import com.meikoz.core.util.GsonUtil;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.TvModel;
import com.stx.xhb.enjoylife.ui.adapter.TvShowRecyclerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by jxnk25 on 2016/12/2.
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description： 电视直播
 */
public class TvShowFragment extends BaseFragment {

    @Bind(R.id.rv_tv_show)
    RecyclerView mRvTvShow;
    private List<TvModel.DataBean> mDataBeanList;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_tvshow;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mRvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        initJsonData();
        setAdapter();
    }


    /**
     * 从assert文件夹中读取省市区的json文件，然后转化为json对象
     */
    private void initJsonData() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("tv.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }
            mDataBeanList = GsonUtil.newGson().fromJson(sb.toString(), TvModel.class).getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAdapter() {
        if (mDataBeanList == null) {
            mDataBeanList = new ArrayList<>();
        }
        TvShowRecyclerAdapter tvShowRecyclerAdapter = new TvShowRecyclerAdapter(getActivity(), R.layout.list_item_selet_tv_primary, mDataBeanList);
        mRvTvShow.setAdapter(tvShowRecyclerAdapter);
    }

}
