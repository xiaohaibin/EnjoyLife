package com.stx.xhb.enjoylife.model.http;

import com.android.core.api.HttpRequest;
import com.stx.xhb.enjoylife.config.Constants;
import com.stx.xhb.enjoylife.model.entity.ImageEntity;
import com.stx.xhb.enjoylife.model.entity.SplashEntity;
import com.stx.xhb.enjoylife.model.entity.VideoEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * API管理
 */
public interface ApiManager {

    @GET("4/start-image/{resolution}")
    Call<SplashEntity> getSplashImageEntity(@Path("resolution")String res);

    @GET("data/福利/10/{page}")
    Call<ImageEntity> getImageEntity(@Path("page")int id);

    @GET("v2/feed")
    Call<VideoEntity> getVideoEntity(@Query("num") int num);

    class ApiFactory {

        public static ApiManager createApi() {
            return HttpRequest.getInstance().create(Constants.BASE_URL,ApiManager.class);
        }

        public static ApiManager createVideoApi(){
            return HttpRequest.getInstance().create(Constants.VIDEO_URL,ApiManager.class);
        }
    }

}
