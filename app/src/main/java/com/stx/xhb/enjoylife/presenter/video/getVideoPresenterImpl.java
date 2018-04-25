package com.stx.xhb.enjoylife.presenter.video;

import android.text.TextUtils;

import com.xhb.core.base.BasePresenter;
import com.stx.xhb.enjoylife.model.entity.VideoEntity;
import com.stx.xhb.enjoylife.model.http.ApiManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.xiao on 16/7/14.
 */
public class getVideoPresenterImpl extends BasePresenter<getVideoContact.getVideoView> implements getVideoContact {

    @Override
    public void getVideoInfo(String date, int num) {
        HashMap<String, String> map = new HashMap<>();
        map.put("num", "2");
        if (!TextUtils.isEmpty(date)) {
            map.put("date", String.valueOf(date));
        }
        ApiManager.ApiFactory.createVideoApi().getVideoEntity(map).enqueue(new Callback<VideoEntity>() {
            @Override
            public void onResponse(Call<VideoEntity> call, Response<VideoEntity> response) {
                if (response.isSuccessful()) {
                    getView().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<VideoEntity> call, Throwable t) {
                getView().onFailure(t.getMessage());
            }
        });
    }
}
