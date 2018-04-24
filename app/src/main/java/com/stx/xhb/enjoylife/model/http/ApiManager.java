package com.stx.xhb.enjoylife.model.http;

import com.meikoz.core.api.RestApi;
import com.stx.xhb.enjoylife.config.Constants;
import com.stx.xhb.enjoylife.model.entity.ImageEntity;
import com.stx.xhb.enjoylife.model.entity.TuchongImagEntity;
import com.stx.xhb.enjoylife.model.entity.VideoEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * API管理
 */
public interface ApiManager {

    @GET("data/福利/{size}/{page}")
    Call<ImageEntity> getImageEntity(@Path("size") int size, @Path("page") int id);

    @GET("v2/feed")
    Call<VideoEntity> getVideoEntity(@Query("num") int num);

    @GET("feed-app")
    Call<TuchongImagEntity> getFeedApp(@QueryMap Map<String, String> param);

    class ApiFactory {

        public static ApiManager createGankApi() {
            return RestApi.getInstance().create(Constants.GANK_API, ApiManager.class);
        }

        public static ApiManager createVideoApi() {
            return RestApi.getInstance().create(Constants.VIDEO_API, ApiManager.class);
        }

        public static ApiManager createTuChongApi() {
            return RestApi.getInstance().create(Constants.TUCHONG_API, ApiManager.class);
        }
    }

}
