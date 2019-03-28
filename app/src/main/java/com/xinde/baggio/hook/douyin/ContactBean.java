package com.xinde.baggio.hook.douyin;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author: Shawn
 * time  : 3/12/19 2:55 PM
 * desc  :
 * update: Shawn 3/12/19 2:55 PM
 */
public class ContactBean {

    /**
     * has_more : false
     * status_code : 0
     * cursor : 0
     * total_count : 1
     * type : 1
     * register_count : 1
     * user_list : [{"nickname":"李宁","uid":"108472289341","avatar_uri":"","avatar_thumb":{"uri":"","url_list":["https://p0.pstatp.com/origin/3795/3033762272"]},"avatar_medium":{"uri":"","url_list":["https://p0.pstatp.com/origin/3795/3033762272"]},"avatar_larger":{"uri":"","url_list":["https://p0.pstatp.com/origin/3795/3033762272"]},"third_name":"王建国","follow_status":0,"story_open":false,"signature":""}]
     * unregistered_user : []
     */

    @SerializedName("has_more")
    private boolean hasMore;
    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("cursor")
    private int cursor;
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("type")
    private int type;
    @SerializedName("register_count")
    private int registerCount;
    @SerializedName("user_list")
    private List<UserListBean> userList;
    @SerializedName("unregistered_user")
    private List<?> unregisteredUser;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(int registerCount) {
        this.registerCount = registerCount;
    }

    public List<UserListBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListBean> userList) {
        this.userList = userList;
    }

    public List<?> getUnregisteredUser() {
        return unregisteredUser;
    }

    public void setUnregisteredUser(List<?> unregisteredUser) {
        this.unregisteredUser = unregisteredUser;
    }

    public static class UserListBean {
        /**
         * nickname : 李宁
         * uid : 108472289341
         * avatar_uri :
         * avatar_thumb : {"uri":"","url_list":["https://p0.pstatp.com/origin/3795/3033762272"]}
         * avatar_medium : {"uri":"","url_list":["https://p0.pstatp.com/origin/3795/3033762272"]}
         * avatar_larger : {"uri":"","url_list":["https://p0.pstatp.com/origin/3795/3033762272"]}
         * third_name : 王建国
         * follow_status : 0
         * story_open : false
         * signature :
         */

        @SerializedName("nickname")
        private String nickname;
        @SerializedName("uid")
        private String uid;
        @SerializedName("avatar_uri")
        private String avatarUri;
        @SerializedName("avatar_thumb")
        private AvatarThumbBean avatarThumb;
        @SerializedName("avatar_medium")
        private AvatarMediumBean avatarMedium;
        @SerializedName("avatar_larger")
        private AvatarLargerBean avatarLarger;
        @SerializedName("third_name")
        private String thirdName;
        @SerializedName("follow_status")
        private int followStatus;
        @SerializedName("story_open")
        private boolean storyOpen;
        @SerializedName("signature")
        private String signature;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAvatarUri() {
            return avatarUri;
        }

        public void setAvatarUri(String avatarUri) {
            this.avatarUri = avatarUri;
        }

        public AvatarThumbBean getAvatarThumb() {
            return avatarThumb;
        }

        public void setAvatarThumb(AvatarThumbBean avatarThumb) {
            this.avatarThumb = avatarThumb;
        }

        public AvatarMediumBean getAvatarMedium() {
            return avatarMedium;
        }

        public void setAvatarMedium(AvatarMediumBean avatarMedium) {
            this.avatarMedium = avatarMedium;
        }

        public AvatarLargerBean getAvatarLarger() {
            return avatarLarger;
        }

        public void setAvatarLarger(AvatarLargerBean avatarLarger) {
            this.avatarLarger = avatarLarger;
        }

        public String getThirdName() {
            return thirdName;
        }

        public void setThirdName(String thirdName) {
            this.thirdName = thirdName;
        }

        public int getFollowStatus() {
            return followStatus;
        }

        public void setFollowStatus(int followStatus) {
            this.followStatus = followStatus;
        }

        public boolean isStoryOpen() {
            return storyOpen;
        }

        public void setStoryOpen(boolean storyOpen) {
            this.storyOpen = storyOpen;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public static class AvatarThumbBean {
            /**
             * uri :
             * url_list : ["https://p0.pstatp.com/origin/3795/3033762272"]
             */

            @SerializedName("uri")
            private String uri;
            @SerializedName("url_list")
            private List<String> urlList;

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public List<String> getUrlList() {
                return urlList;
            }

            public void setUrlList(List<String> urlList) {
                this.urlList = urlList;
            }
        }

        public static class AvatarMediumBean {
            /**
             * uri :
             * url_list : ["https://p0.pstatp.com/origin/3795/3033762272"]
             */

            @SerializedName("uri")
            private String uri;
            @SerializedName("url_list")
            private List<String> urlList;

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public List<String> getUrlList() {
                return urlList;
            }

            public void setUrlList(List<String> urlList) {
                this.urlList = urlList;
            }
        }

        public static class AvatarLargerBean {
            /**
             * uri :
             * url_list : ["https://p0.pstatp.com/origin/3795/3033762272"]
             */

            @SerializedName("uri")
            private String uri;
            @SerializedName("url_list")
            private List<String> urlList;

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public List<String> getUrlList() {
                return urlList;
            }

            public void setUrlList(List<String> urlList) {
                this.urlList = urlList;
            }
        }
    }
}
