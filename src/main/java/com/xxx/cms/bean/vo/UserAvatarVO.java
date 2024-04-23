package com.xxx.cms.bean.vo;

public class UserAvatarVO {


        private String avatar;


        public UserAvatarVO(){//这种空的用来实例化的，好像有申请空间的作用
        }

        public UserAvatarVO(String avatar) {
            this.avatar = avatar;
        }

        public String getUseravatar() {
            return avatar;
        }

        public void setUseravatar(String avatar) {
            this.avatar = avatar;
        }

        @Override
        public String toString() {
            return "UserAvatarVO{" +
                    "avatar='" + avatar + '\'' +
                    '}';
        }

}
