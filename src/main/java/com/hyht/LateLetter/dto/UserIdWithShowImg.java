package com.hyht.LateLetter.dto;

public class UserIdWithShowImg {
    private String userId;
    private String showImg;


    public UserIdWithShowImg(String userId, String showImg) {
        this.userId = userId;
        this.showImg = showImg;
    }

    public UserIdWithShowImg() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }
}
