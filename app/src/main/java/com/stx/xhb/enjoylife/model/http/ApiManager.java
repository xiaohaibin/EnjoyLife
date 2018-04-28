package com.stx.xhb.enjoylife.model.http;

import com.stx.xhb.enjoylife.model.entity.TuChongWallPaperResponse;
import com.xhb.core.api.RestApi;
import com.stx.xhb.enjoylife.config.Constants;
import com.stx.xhb.enjoylife.model.entity.TuchongImagResponse;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * API管理
 */
public interface ApiManager {

    /**
     * 开眼视频
     * @param param
     * @return
     */
    @GET("v2/feed")
    Call<VideoResponse> getVideoEntity(@QueryMap Map<String, String> param);

    /**
     * 图虫推荐
     * @param param
     * @return
     */
    @GET("feed-app")
    Call<TuchongImagResponse> getFeedApp(@QueryMap Map<String, String> param);

    /**
     * 图虫壁纸
     * @param page
     * @return
     */
    @GET("2/wall-paper/app")
    Call<TuChongWallPaperResponse> getWallPaper(@Query("page") int page);

    class ApiFactory {

        public static ApiManager createVideoApi() {
            return RestApi.getInstance().create(Constants.VIDEO_API, ApiManager.class);
        }

        public static ApiManager createTuChongApi() {
            return RestApi.getInstance().create(Constants.TUCHONG_API, ApiManager.class);
        }
    }

}
