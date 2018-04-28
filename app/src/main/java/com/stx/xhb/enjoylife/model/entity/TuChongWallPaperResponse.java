package com.stx.xhb.enjoylife.model.entity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 图虫壁纸
 */
public class TuChongWallPaperResponse {


    private String tos_name;
    private boolean more;
    private String result;
    private List<FeedListBean> feedList;

    public String getTos_name() {
        return tos_name;
    }

    public boolean isMore() {
        return more;
    }

    public String getResult() {
        return result;
    }

    public List<FeedListBean> getFeedList() {
        return feedList;
    }

    public static class FeedListBean {

        private String type;
        private EntryBean entry;

        public String getType() {
            return type;
        }

        public EntryBean getEntry() {
            return entry;
        }

        public static class EntryBean {

            private String post_id;
            private String author_id;
            private String type;
            private String url;
            private String published_at;
            private String excerpt;
            private int favorites;
            private int comments;
            private String title;
            private int image_count;
            private boolean rewardable;
            private int rewards;
            private boolean wallpaper;
            private int views;
            private boolean delete;
            private String content;
            private boolean update;
            private Object title_image;
            private SiteBean site;
            private List<ImagesBean> images;
            private List<TagsBean> tags;
            private List<?> users;
            private List<?> comment_list;

            public String getPost_id() {
                return post_id;
            }

            public String getAuthor_id() {
                return author_id;
            }

            public String getType() {
                return type;
            }

            public String getUrl() {
                return url;
            }

            public String getPublished_at() {
                return published_at;
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

            public String getTitle() {
                return title;
            }

            public int getImage_count() {
                return image_count;
            }

            public boolean isRewardable() {
                return rewardable;
            }

            public int getRewards() {
                return rewards;
            }

            public boolean isWallpaper() {
                return wallpaper;
            }

            public int getViews() {
                return views;
            }

            public boolean isDelete() {
                return delete;
            }

            public String getContent() {
                return content;
            }

            public boolean isUpdate() {
                return update;
            }

            public Object getTitle_image() {
                return title_image;
            }

            public SiteBean getSite() {
                return site;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public List<?> getUsers() {
                return users;
            }

            public List<?> getComment_list() {
                return comment_list;
            }

            public static class SiteBean {

                private int site_id;
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
                private List<?> verification_list;

                public int getSite_id() {
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
                public List<?> getVerification_list() {
                    return verification_list;
                }
            }

            public static class ImagesBean {
                /**
                 * img_id : 27800987
                 * user_id : 103120
                 * title : _DSC4767
                 * excerpt : 在山顶上观看小镇Reine在日落前最后的一丝光线
                 * width : 1280
                 * height : 748
                 * description : 在山顶上观看小镇Reine在日落前最后的一丝光线
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

            public static class TagsBean {
                /**
                 * tag_id : 693
                 * type : subject
                 * tag_name : 风光
                 * event_type :
                 * vote :
                 */

                private int tag_id;
                private String type;
                private String tag_name;
                private String event_type;
                private String vote;

                public int getTag_id() {
                    return tag_id;
                }


                public String getType() {
                    return type;
                }

                public String getTag_name() {
                    return tag_name;
                }


                public String getEvent_type() {
                    return event_type;
                }

                public String getVote() {
                    return vote;
                }

            }
        }
    }
}
