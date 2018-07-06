package com.stx.xhb.enjoylife.presenter.video;

import android.text.TextUtils;

import com.xhb.core.base.BaseIPresenter;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;

import java.util.HashMap;

/**
 * Created by Mr.xiao on 16/7/14.
 */
public class getVideoIPresenterImpl extends BaseIPresenter<VideoResponse, getVideoContract.getVideoView> implements getVideoContract.Model {

    @Override
    public void getVideoInfo(String date, int num) {
        HashMap<String, String> map = new HashMap<>();
        map.put("num", "2");
        if (!TextUtils.isEmpty(date)) {
            map.put("date", String.valueOf(date));
        }
        request(ApiManager.ApiFactory.createVideoApi().getVideoList(map), new LoadTaskCallback<VideoResponse>() {
            @Override
            public void onTaskLoaded(VideoResponse data) {
                if (getView() != null) {
                    getView().onResponse(data);
                }
            }

            @Override
            public void onDataNotAvailable(String msg) {
                if (getView() != null) {
                    getView().onFailure(msg);
                }
            }
        });


    }
}
