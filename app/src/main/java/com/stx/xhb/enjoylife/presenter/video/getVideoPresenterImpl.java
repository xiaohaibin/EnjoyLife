package com.stx.xhb.enjoylife.presenter.video;

import android.text.TextUtils;

import com.xhb.core.base.BasePresenter;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.xiao on 16/7/14.
 */
public class getVideoPresenterImpl extends BasePresenter<getVideoContract.getVideoView> implements getVideoContract {

    @Override
    public void getVideoInfo(String date, int num) {
        HashMap<String, String> map = new HashMap<>();
        map.put("num", "2");
        if (!TextUtils.isEmpty(date)) {
            map.put("date", String.valueOf(date));
        }
        Call<VideoResponse> responseCall = ApiManager.ApiFactory.createVideoApi().getVideoList(map);
        responseCall.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (getView()!=null&&response.isSuccessful()) {
                    getView().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                if (getView()!=null) {
                    getView().onFailure(t.getMessage());
                }
            }
        });
        addCall(responseCall);
    }
}
