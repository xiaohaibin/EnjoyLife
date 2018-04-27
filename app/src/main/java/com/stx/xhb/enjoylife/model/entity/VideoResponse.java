package com.stx.xhb.enjoylife.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.xiao on 16/7/13.
 * 视频推荐实体类
 */
public class VideoResponse {

    private String nextPageUrl;
    private long nextPublishTime;
    private String newestIssueType;

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public String getNewestIssueType() {
        return newestIssueType;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    private List<IssueListEntity> issueList;

    public List<IssueListEntity> getIssueList() {
        return issueList;
    }

    public static class IssueListEntity {
        private long date;
        private long publishTime;
        private String type;
        private int count;

        private List<ItemListEntity> itemList;

        public void setType(String type) {
            this.type = type;
        }

        public long getDate() {
            return date;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public String getType() {
            return type;
        }

        public int getCount() {
            return count;
        }

        public List<ItemListEntity> getItemList() {
            return itemList;
        }

        public static class ItemListEntity implements Serializable {
            private String type;
            private String image;

            public String getImage() {
                return image;
            }

            private DataEntity data;

            public String getType() {
                return type;
            }

            public DataEntity getData() {
                return data;
            }

            public static class DataEntity {
                private String image;
                public String getImage() {
                    return image;
                }
                public void setImage(String image) {
                    this.image = image;
                }

                private int id;
                private long date;
                private int idx;
                private String title;
                private String description;
                private String category;
                private int duration;
                private String playUrl;
                private String text;
                private String actionUrl;

                public String getActionUrl() {
                    return actionUrl;
                }

                public String getText() {
                    return text;
                }

                private ConsumptionEntity consumption;
                private Object promotion;
                private Object waterMarks;
                private ProviderEntity provider;
                private Object author;
                private Object adTrack;
                private Object shareAdTrack;
                private Object favoriteAdTrack;
                private Object webAdTrack;

                private CoverEntity cover;

                private WebUrlEntity webUrl;
                private Object campaign;

                private List<PlayInfoEntity> playInfo;

                public int getId() {
                    return id;
                }

                public long getDate() {
                    return date;
                }

                public int getIdx() {
                    return idx;
                }

                public String getTitle() {
                    return title;
                }

                public String getDescription() {
                    return description;
                }

                public String getCategory() {
                    return category;
                }

                public int getDuration() {
                    return duration;
                }

                public String getPlayUrl() {
                    return playUrl;
                }

                public ConsumptionEntity getConsumption() {
                    return consumption;
                }

                public Object getPromotion() {
                    return promotion;
                }

                public Object getWaterMarks() {
                    return waterMarks;
                }

                public ProviderEntity getProvider() {
                    return provider;
                }

                public Object getAuthor() {
                    return author;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public Object getShareAdTrack() {
                    return shareAdTrack;
                }

                public Object getFavoriteAdTrack() {
                    return favoriteAdTrack;
                }

                public Object getWebAdTrack() {
                    return webAdTrack;
                }

                public CoverEntity getCover() {
                    return cover;
                }

                public WebUrlEntity getWebUrl() {
                    return webUrl;
                }

                public Object getCampaign() {
                    return campaign;
                }

                public List<PlayInfoEntity> getPlayInfo() {
                    return playInfo;
                }

                public static class ConsumptionEntity {
                    private int collectionCount;
                    private int shareCount;
                    private int playCount;
                    private int replyCount;

                    public int getCollectionCount() {
                        return collectionCount;
                    }

                    public int getShareCount() {
                        return shareCount;
                    }

                    public int getPlayCount() {
                        return playCount;
                    }

                    public int getReplyCount() {
                        return replyCount;
                    }
                }

                public static class ProviderEntity {
                    private String name;
                    private String alias;
                    private String icon;

                    public String getName() {
                        return name;
                    }

                    public String getAlias() {
                        return alias;
                    }

                    public String getIcon() {
                        return icon;
                    }
                }

                public static class CoverEntity {
                    private String feed;
                    private String detail;
                    private String blurred;
                    private String sharing;

                    public String getFeed() {
                        return feed;
                    }

                    public String getDetail() {
                        return detail;
                    }

                    public String getBlurred() {
                        return blurred;
                    }

                    public String getSharing() {
                        return sharing;
                    }
                }

                public static class WebUrlEntity {
                    private String raw;
                    private String forWeibo;

                    public String getRaw() {
                        return raw;
                    }

                    public String getForWeibo() {
                        return forWeibo;
                    }
                }

                public static class PlayInfoEntity {
                    private int height;
                    private int width;
                    private String name;
                    private String type;
                    private String url;

                    public int getHeight() {
                        return height;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public String getName() {
                        return name;
                    }

                    public String getType() {
                        return type;
                    }

                    public String getUrl() {
                        return url;
                    }
                }
            }
        }
    }
}
