package com.stx.xhb.enjoylife.model.http;

import com.stx.xhb.enjoylife.model.entity.TuChongWallPaperResponse;
import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsContentResponse;
import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsResponse;
import com.xhb.core.api.RestApi;
import com.stx.xhb.enjoylife.config.Constants;
import com.stx.xhb.enjoylife.model.entity.TuchongImagResponse;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;

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

    /**
     * 开眼推荐视频
     *
     * @param param
     * @return
     */
    @GET("v2/feed")
    Call<VideoResponse> getVideoList(@QueryMap Map<String, String> param);

    /**
     * 图虫推荐
     *
     * @param param
     * @return
     */
    @GET("feed-app")
    Call<TuchongImagResponse> getFeedApp(@QueryMap Map<String, String> param);

    /**
     * 图虫壁纸
     *
     * @param page
     * @return
     */
    @GET("2/wall-paper/app")
    Call<TuChongWallPaperResponse> getWallPaper(@Query("page") int page);

    /**
     * 获取知乎日报
     *
     * @param url https://news-at.zhihu.com/api/4/news/latest 最新消息
     *            https://news-at.zhihu.com/api/4/news/before/20131119  过往消息
     * @return
     */
    @GET("{url}")
    Call<ZhiHuNewsResponse> getZhiHuNews(@Path("url") String url);

    /**
     * 获取知乎日报
     *
     * @param time https://news-at.zhihu.com/api/4/news/latest 最新消息
     *            https://news-at.zhihu.com/api/4/news/before/20131119  过往消息
     * @return
     */
    @GET("before/{time}")
    Call<ZhiHuNewsResponse> getZhiHuNewsBefore(@Path("time") String time);

    /**
     * 获取知乎日报新闻详情
     * @param time
     * @return
     */
    @GET("{time}")
    Call<ZhiHuNewsContentResponse> getZhiHuNewsContent(@Path("time") String time);

    class ApiFactory {

        public static ApiManager createVideoApi() {
            return RestApi.getInstance().create(Constants.VIDEO_API, ApiManager.class);
        }

        public static ApiManager createTuChongApi() {
            return RestApi.getInstance().create(Constants.TUCHONG_API, ApiManager.class);
        }

        public static ApiManager creatZhiHuApi() {
            return RestApi.getInstance().create(Constants.ZHIHU_API, ApiManager.class);
        }

    }

}
