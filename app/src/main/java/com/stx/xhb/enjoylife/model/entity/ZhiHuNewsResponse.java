package com.stx.xhb.enjoylife.model.entity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/9
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 知乎日报新闻实体类
 */
public class ZhiHuNewsResponse {

    /**
     * date : 20180509
     * stories : [{"images":["https://pic1.zhimg.com/v2-2eab141000845dedd3e5215cec396ca0.jpg"],"type":0,"id":9681671,"ga_prefix":"050910","title":"听见「2000 块护肤品不如一次医美」，作为皮肤科医生，我得说几句"},{"images":["https://pic4.zhimg.com/v2-85c9f286013d987008a35033d661fef7.jpg"],"type":0,"id":9680802,"ga_prefix":"050909","title":"为什么很多经历原生家庭伤害的人，父母往往是老师？"},{"images":["https://pic4.zhimg.com/v2-cb1218d5947e60be39df4ce9f2a8c4b7.jpg"],"type":0,"id":9681981,"ga_prefix":"050908","title":"「你都 20 好几的男人了还天天打游戏，真没出息」"},{"images":["https://pic3.zhimg.com/v2-0a82314c26a546ab0e2591d65494ba96.jpg"],"type":0,"id":9681931,"ga_prefix":"050907","title":"港股迎来 20 年来最大变革，A 股恐怕又要因此再失去一波公司"},{"images":["https://pic2.zhimg.com/v2-cd3128cc82ee7330c4b19d1fc20301c1.jpg"],"type":0,"id":9681999,"ga_prefix":"050907","title":"马化腾张一鸣朋友圈「互怼」，看来头条和腾讯一战是在所难免了"},{"images":["https://pic2.zhimg.com/v2-6a5e13826658386270a9d2c559811e95.jpg"],"type":0,"id":9682062,"ga_prefix":"050906","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-e8a7f6e103bf5da69f785279223e71b4.jpg","type":0,"id":9681671,"ga_prefix":"050910","title":"听见「2000 块护肤品不如一次医美」，作为皮肤科医生，我得说几句"},{"image":"https://pic4.zhimg.com/v2-bf2189b8bad4ca7b78abf349c2497183.jpg","type":0,"id":9681999,"ga_prefix":"050907","title":"马化腾张一鸣朋友圈「互怼」，看来头条和腾讯一战是在所难免了"},{"image":"https://pic1.zhimg.com/v2-c00667590f4fd4b739e6904379a23768.jpg","type":0,"id":9681981,"ga_prefix":"050908","title":"「你都 20 好几的男人了还天天打游戏，真没出息」"},{"image":"https://pic2.zhimg.com/v2-f25b047abda14637c43dae6aee8c4969.jpg","type":0,"id":9681931,"ga_prefix":"050907","title":"港股迎来 20 年来最大变革，A 股恐怕又要因此再失去一波公司"},{"image":"https://pic3.zhimg.com/v2-3285705600681b9f8cd49c503b5d69e2.jpg","type":0,"id":9680802,"ga_prefix":"050909","title":"为什么很多经历原生家庭伤害的人，父母往往是老师？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-2eab141000845dedd3e5215cec396ca0.jpg"]
         * type : 0
         * id : 9681671
         * ga_prefix : 050910
         * title : 听见「2000 块护肤品不如一次医美」，作为皮肤科医生，我得说几句
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getImages() {
            return images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-e8a7f6e103bf5da69f785279223e71b4.jpg
         * type : 0
         * id : 9681671
         * ga_prefix : 050910
         * title : 听见「2000 块护肤品不如一次医美」，作为皮肤科医生，我得说几句
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public String getTitle() {
            return title;
        }
    }
}
