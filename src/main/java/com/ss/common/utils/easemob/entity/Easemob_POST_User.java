package com.ss.common.utils.easemob.entity;

public class Easemob_POST_User {


    /**
     * username : ${用户名}
     * password : ${密码}
     * nickname : ${昵称值}
     */

    private String username;
    private String password;
    private String nickname;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
