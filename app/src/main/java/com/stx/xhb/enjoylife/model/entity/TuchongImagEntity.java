package com.stx.xhb.enjoylife.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/24
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 图虫推荐图片列表实体类
 */
public class TuchongImagEntity {

    private boolean is_history;
    private int counts;
    private String message;
    private boolean more = false;
    private String result;
    private List<FeedListBean> feedList;

    public boolean isIs_history() {
        return is_history;
    }

    public void setIs_history(boolean is_history) {
        this.is_history = is_history;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<FeedListBean> getFeedList() {
        return feedList == null ? new ArrayList<FeedListBean>() : feedList;
    }

    public void setFeedList(List<FeedListBean> feedList) {
        this.feedList = feedList;
    }

    public static class FeedListBean {

        private int post_id;
        private String type;
        private String url;
        private String site_id;
        private String author_id;
        private String published_at;
        private String passed_time;
        private String excerpt;
        private int favorites;
        private int comments;
        private boolean rewardable;
        private String parent_comments;
        private String rewards;
        private int views;
        private boolean delete;
        private boolean update;
        private String content;
        private String title;
        private int image_count;
        private Object title_image;
        private String data_type;
        private String created_at;
        private SiteBean site;
        private String recom_type;
        private String rqt_id;
        private boolean is_favorite;
        private List<ImagesBean> images;
        private List<String> tags;
        private List<?> event_tags;
        private List<?> favorite_list_prefix;
        private List<?> reward_list_prefix;
        private List<?> comment_list_prefix;
        private List<?> sites;

        public int getPost_id() {
            return post_id;
        }

        public void setPost_id(int post_id) {
            this.post_id = post_id;
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

        public String getSite_id() {
            return site_id;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public String getPassed_time() {
            return passed_time;
        }

        public void setPassed_time(String passed_time) {
            this.passed_time = passed_time;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public int getFavorites() {
            return favorites;
        }

        public void setFavorites(int favorites) {
            this.favorites = favorites;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public boolean isRewardable() {
            return rewardable;
        }

        public void setRewardable(boolean rewardable) {
            this.rewardable = rewardable;
        }

        public String getParent_comments() {
            return parent_comments;
        }

        public void setParent_comments(String parent_comments) {
            this.parent_comments = parent_comments;
        }

        public String getRewards() {
            return rewards;
        }

        public void setRewards(String rewards) {
            this.rewards = rewards;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public boolean isDelete() {
            return delete;
        }

        public void setDelete(boolean delete) {
            this.delete = delete;
        }

        public boolean isUpdate() {
            return update;
        }

        public void setUpdate(boolean update) {
            this.update = update;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getImage_count() {
            return image_count;
        }

        public void setImage_count(int image_count) {
            this.image_count = image_count;
        }

        public Object getTitle_image() {
            return title_image;
        }

        public void setTitle_image(Object title_image) {
            this.title_image = title_image;
        }

        public String getData_type() {
            return data_type;
        }

        public void setData_type(String data_type) {
            this.data_type = data_type;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public SiteBean getSite() {
            return site;
        }

        public void setSite(SiteBean site) {
            this.site = site;
        }

        public String getRecom_type() {
            return recom_type;
        }

        public void setRecom_type(String recom_type) {
            this.recom_type = recom_type;
        }

        public String getRqt_id() {
            return rqt_id;
        }

        public void setRqt_id(String rqt_id) {
            this.rqt_id = rqt_id;
        }

        public boolean isIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(boolean is_favorite) {
            this.is_favorite = is_favorite;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<?> getEvent_tags() {
            return event_tags;
        }

        public void setEvent_tags(List<?> event_tags) {
            this.event_tags = event_tags;
        }

        public List<?> getFavorite_list_prefix() {
            return favorite_list_prefix;
        }

        public void setFavorite_list_prefix(List<?> favorite_list_prefix) {
            this.favorite_list_prefix = favorite_list_prefix;
        }

        public List<?> getReward_list_prefix() {
            return reward_list_prefix;
        }

        public void setReward_list_prefix(List<?> reward_list_prefix) {
            this.reward_list_prefix = reward_list_prefix;
        }

        public List<?> getComment_list_prefix() {
            return comment_list_prefix;
        }

        public void setComment_list_prefix(List<?> comment_list_prefix) {
            this.comment_list_prefix = comment_list_prefix;
        }

        public List<?> getSites() {
            return sites;
        }

        public void setSites(List<?> sites) {
            this.sites = sites;
        }

        public static class SiteBean {

            private String site_id;
            private String type;
            private String name;
            private String domain;
            private String description;
            private int followers;
            private String url;
            private String icon;
            private boolean verified;
            private int verified_type;
            private String verified_reason;
            private int verifications;
            private boolean is_following;
            private List<?> verification_list;

            public String getSite_id() {
                return site_id;
            }

            public void setSite_id(String site_id) {
                this.site_id = site_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getFollowers() {
                return followers;
            }

            public void setFollowers(int followers) {
                this.followers = followers;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public boolean isVerified() {
                return verified;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public int getVerified_type() {
                return verified_type;
            }

            public void setVerified_type(int verified_type) {
                this.verified_type = verified_type;
            }

            public String getVerified_reason() {
                return verified_reason;
            }

            public void setVerified_reason(String verified_reason) {
                this.verified_reason = verified_reason;
            }

            public int getVerifications() {
                return verifications;
            }

            public void setVerifications(int verifications) {
                this.verifications = verifications;
            }

            public boolean isIs_following() {
                return is_following;
            }

            public void setIs_following(boolean is_following) {
                this.is_following = is_following;
            }

            public List<?> getVerification_list() {
                return verification_list;
            }

            public void setVerification_list(List<?> verification_list) {
                this.verification_list = verification_list;
            }
        }

        public static class ImagesBean {
            /**
             * img_id : 28696983
             * user_id : 3333155
             * title : 001
             * excerpt :
             * width : 1242
             * height : 931
             * description :
             */

            private int img_id;
            private int user_id;
            private String title;
            private String excerpt;
            private int width;
            private int height;
            private String description;

            public int getImg_id() {
                return img_id;
            }

            public void setImg_id(int img_id) {
                this.img_id = img_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getExcerpt() {
                return excerpt;
            }

            public void setExcerpt(String excerpt) {
                this.excerpt = excerpt;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
