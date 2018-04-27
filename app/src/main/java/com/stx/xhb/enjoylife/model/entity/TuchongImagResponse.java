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
public class TuchongImagResponse {

    private boolean is_history;
    private int counts;
    private String message;
    private boolean more = false;
    private String result;
    private List<FeedListBean> feedList;

    public boolean isIs_history() {
        return is_history;
    }

    public int getCounts() {
        return counts;
    }

    public String getMessage() {
        return message;
    }

    public boolean isMore() {
        return more;
    }

    public String getResult() {
        return result;
    }

    public List<FeedListBean> getFeedList() {
        return feedList == null ? new ArrayList<FeedListBean>() : feedList;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public String getSite_id() {
            return site_id;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public String getPublished_at() {
            return published_at;
        }

        public String getPassed_time() {
            return passed_time;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public int getFavorites() {
            return favorites;
        }

        public int getComments() {
            return comments;
        }

        public boolean isRewardable() {
            return rewardable;
        }

        public String getParent_comments() {
            return parent_comments;
        }

        public String getRewards() {
            return rewards;
        }

        public int getViews() {
            return views;
        }

        public boolean isDelete() {
            return delete;
        }

        public boolean isUpdate() {
            return update;
        }

        public String getContent() {
            return content;
        }

        public String getTitle() {
            return title;
        }

        public int getImage_count() {
            return image_count;
        }

        public Object getTitle_image() {
            return title_image;
        }

        public String getData_type() {
            return data_type;
        }

        public String getCreated_at() {
            return created_at;
        }

        public SiteBean getSite() {
            return site;
        }

        public String getRecom_type() {
            return recom_type;
        }

        public String getRqt_id() {
            return rqt_id;
        }

        public boolean isIs_favorite() {
            return is_favorite;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public List<String> getTags() {
            return tags;
        }

        public List<?> getEvent_tags() {
            return event_tags;
        }

        public List<?> getFavorite_list_prefix() {
            return favorite_list_prefix;
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

        public List<?> getSites() {
            return sites;
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

            public String getType() {
                return type;
            }

            public String getName() {
                return name;
            }

            public String getDomain() {
                return domain;
            }

            public String getDescription() {
                return description;
            }

            public int getFollowers() {
                return followers;
            }

            public String getUrl() {
                return url;
            }

            public String getIcon() {
                return icon;
            }

            public boolean isVerified() {
                return verified;
            }

            public int getVerified_type() {
                return verified_type;
            }

            public String getVerified_reason() {
                return verified_reason;
            }

            public int getVerifications() {
                return verifications;
            }

            public boolean isIs_following() {
                return is_following;
            }

            public List<?> getVerification_list() {
                return verification_list;
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

            public int getUser_id() {
                return user_id;
            }

            public String getTitle() {
                return title;
            }

            public String getExcerpt() {
                return excerpt;
            }

            public int getWidth() {
                return width;
            }

            public int getHeight() {
                return height;
            }

            public String getDescription() {
                return description;
            }
        }
    }
}
