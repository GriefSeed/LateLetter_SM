package com.hyht.LateLetter.dto;


import com.alibaba.fastjson.annotation.JSONField;
import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.entity.Users;

import java.util.Date;


public class LetterWithUser {
    private Users users;
    private long letterId;
    private String title;
    private String letterContent;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date deadline;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    private int status;
    private long userId;
    private int publicFlag;
    private int deleteFlag;
    private int readAuto;
    private int countDown;

    public LetterWithUser(Users users, Letter letter) {
        this.users = users;
        this.letterId = letter.getLetterId();
        this.title = letter.getTitle();
        this.letterContent = letter.getLetterContent();
        this.deadline = letter.getDeadline();
        this.startDate = letter.getStartDate();
        this.status = letter.getStatus();
        this.userId = letter.getUserId();
        this.publicFlag = letter.getPublicFlag();
        this.deleteFlag = letter.getDeleteFlag();
        this.readAuto = letter.getReadAuto();
        this.countDown = letter.getCountDown();
    }

    public LetterWithUser() {
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public long getLetterId() {
        return letterId;
    }

    public void setLetterId(long letterId) {
        this.letterId = letterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(int publicFlag) {
        this.publicFlag = publicFlag;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getReadAuto() {
        return readAuto;
    }

    public void setReadAuto(int readAuto) {
        this.readAuto = readAuto;
    }

    public int getCountDown() {
        return countDown;
    }

    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }
}
