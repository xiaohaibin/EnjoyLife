package com.stx.xhb.enjoylife.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.xiao on 16/7/7.
 * 福利图实体类
 */
public class ImageEntity {

    /**
     * error : false
     * results : [{"_id":"577c7f77421aa90191bc2a67","createdAt":"2016-07-06T11:48:07.778Z","desc":"堀未央奈","publishedAt":"2016-07-06T12:16:53.218Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg","used":true,"who":"代码家"},{"_id":"577b277a421aa9019c09f2e1","createdAt":"2016-07-05T11:20:26.261Z","desc":"7.5","publishedAt":"2016-07-05T11:36:50.61Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f5iv5babirj20zk0nptcn.jpg","used":true,"who":"代码家"},{"_id":"5779d9c4421aa964a66e5893","createdAt":"2016-07-04T11:36:36.327Z","desc":"7.4","publishedAt":"2016-07-04T12:21:38.653Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f5hpzuy3r7j20np0zkgpd.jpg","used":true,"who":"代码家"},{"_id":"5775db69421aa97f5186e455","createdAt":"2016-07-01T10:54:33.55Z","desc":"本田翼","publishedAt":"2016-07-01T11:06:20.244Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f5e7x5vlfyj20dw0euaax.jpg","used":true,"who":"代码家"},{"_id":"5774911c421aa97a566cc153","createdAt":"2016-06-30T11:25:16.598Z","desc":"6.30","publishedAt":"2016-06-30T11:40:10.110Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f5d36vpqyuj20zk0qo7fc.jpg","used":true,"who":"代码家"},{"_id":"577348a5421aa95e542023e8","createdAt":"2016-06-29T12:03:49.269Z","desc":"6.29","publishedAt":"2016-06-29T12:08:28.744Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f5byokn81tj20dw0hiwfe.jpg","used":true,"who":"daimajia"},{"_id":"5771e21c421aa931d274f24b","createdAt":"2016-06-28T10:34:04.375Z","desc":"6.28","publishedAt":"2016-06-28T11:33:25.276Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f5aqgzu2oej20rt15owo7.jpg","used":true,"who":"代码家"},{"_id":"57709843421aa95318978e88","createdAt":"2016-06-27T11:06:43.180Z","desc":"6.27","publishedAt":"2016-06-27T11:31:53.48Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034gw1f59lsn7wjnj20du0ku40c.jpg","used":true,"who":"代码家"},{"_id":"576caea6421aa90c875dc46b","createdAt":"2016-06-24T11:53:10.564Z","desc":"6.25","publishedAt":"2016-06-24T12:01:16.638Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f566a296rpj20lc0sggoj.jpg","used":true,"who":"代码家"},{"_id":"576b5d31421aa94f365b4fc0","createdAt":"2016-06-23T11:53:21.562Z","desc":"6.23","publishedAt":"2016-06-23T11:58:19.404Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f550nt5zklj20zk19rtf3.jpg","used":true,"who":"代码家"}]
     */

    private boolean error;
    /**
     * _id : 577c7f77421aa90191bc2a67
     * createdAt : 2016-07-06T11:48:07.778Z
     * desc : 堀未央奈
     * publishedAt : 2016-07-06T12:16:53.218Z
     * source : chrome
     * type : 福利
     * url : http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg
     * used : true
     * who : 代码家
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        if (results==null){
            results=new ArrayList<>();
        }
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
