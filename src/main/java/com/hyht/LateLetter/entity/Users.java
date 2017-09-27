package com.hyht.LateLetter.entity;

public class Users {
    private long userId;
    private String nickname;
    private String userPassword;
    private int sex;
    private String showImg;
    private String restTime;
    private String phoneNum;
    private String realName;
    private String idCard;

    public Users(String nickname, String userPassword, int sex, String showImg, String restTime, String phoneNum, String realName, String idCard, int status) {
        this.nickname = nickname;
        this.userPassword = userPassword;
        this.sex = sex;
        this.showImg = showImg;
        this.restTime = restTime;
        this.phoneNum = phoneNum;
        this.realName = realName;
        this.idCard = idCard;
        this.status = status;
    }

    public Users(String userPassword, String phoneNum) {
        this.userPassword = userPassword;
        this.phoneNum = phoneNum;
    }

    public Users() {
    }

    private int status;

    public long getUserId() {
        return userId;
    }



    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public String getRestTime() {
        return restTime;
    }

    public void setRestTime(String restTime) {
        this.restTime = restTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", sex=" + sex +
                ", showImg='" + showImg + '\'' +
                ", restTime='" + restTime + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", status=" + status +
                '}';
    }
}
