package com.stx.xhb.enjoylife.model.entity;

import java.util.List;

/**
 * Author: Mr.xiao on 2017/7/19
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class TvModel {


    /**
     * name : 地方卫视
     * tv_list : [{"name":"湖南卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000cf99/desc.m3u8"},{"name":"江苏卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000dh99/desc.m3u8"},{"name":"深圳卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000cz99/desc.m3u8"},{"name":"浙江卫视","url":"http://r.gslb.lecloud.com/live/hls/2017062230000006q99/desc.m3u8"},{"name":"东方卫视,","url":"http://r.gslb.lecloud.com/live/hls/201706223000000d099/desc.m3u8"},{"name":"广东卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000d499/desc.m3u8"},{"name":"北京卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000dl99/desc.m3u8"},{"name":"天津卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000dj99/desc.m3u8"},{"name":"山东卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000de99/desc.m3u8"},{"name":"安徽卫视","url":"http://r.gslb.lecloud.com/live/hls/201704243000000wn99/desc.m3u8"},{"name":"四川卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000cy99/desc.m3u8"},{"name":"重庆卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000dd99/desc.m3u8"},{"name":"宁夏卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000dq99/desc.m3u8"},{"name":"新疆卫视","url":"http://r.gslb.lecloud.com/live/hls/201706223000000du99/desc.m3u8"},{"name":"东方卫视,","url":"http://220.167.81.55/PLTV/88888888/224/3221226289/00000100000000060000000000035240_0.smil"},{"name":"辽宁卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226123/00000100000000060000000000035152_0.smil"},{"name":"东南卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226251/00000100000000060000000000035220_0.smil"},{"name":"江西卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226333/00000100000000060000000000035265_0.smil"},{"name":"河南卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226249/00000100000000060000000000035219_0.smil"},{"name":"吉林卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226257/00000100000000060000000000035224_0.smil"},{"name":"黑龙江卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226259/00000100000000060000000000035225_0.smil"},{"name":"湖北卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226255/00000100000000060000000000035223_0.smil"},{"name":"广西卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226497/10000100000000060000000000076462_0.smil"},{"name":"云南卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226028/10000100000000060000000000076416_0.smil"},{"name":"贵州卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226491/10000100000000060000000000076425_0.smil"},{"name":"河北卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226487/10000100000000060000000000076421_0.smil"},{"name":"吉林卫视","url":"http://220.167.81.55/PLTV/88888888/224/3221226032/10000100000000060000000000076424_0.smil"},{"name":"澳门卫视","url":"http://live.mastvnet.com/iVx460D/live.m3u8"},{"name":"香港卫视","url":"http://fms.cntv.lxdns.com/live/flv/channel84.flv"},{"name":"凤凰卫视","url":"rtmp://live.hkstv.hk.lxdns.com:1935/live/hks"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String name;
        /**
         * name : 湖南卫视
         * url : http://r.gslb.lecloud.com/live/hls/201706223000000cf99/desc.m3u8
         */

        private List<SwitchVideoModel> tv_list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SwitchVideoModel> getTv_list() {
            return tv_list;
        }

    }
}
