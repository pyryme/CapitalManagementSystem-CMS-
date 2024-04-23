package com.xxx.cms.bean.vo;


/**
 * 登录时用户user的视图对象
 *
 */
public class UserLoginVO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public UserLoginVO() {
    }

    public UserLoginVO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}