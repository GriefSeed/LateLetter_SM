package com.hyht.LateLetter.entity;

import java.util.Date;

public class Letter {
    private int letterId;
    private String title;
    private String letterContent;
    private Date deadline;
    private int status;
    private int userId;
    private int publicFlag;
    private int readAuto;
    private int countDown;

    public int getLetterId() {
        return letterId;
    }

    public void setLetterId(int letterId) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(int publicFlag) {
        this.publicFlag = publicFlag;
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

    @Override
    public String toString() {
        return "Letter{" +
                "letterId=" + letterId +
                ", title='" + title + '\'' +
                ", content='" + letterContent + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                ", userId=" + userId +
                ", publicFlag=" + publicFlag +
                ", readAuto=" + readAuto +
                ", countDown=" + countDown +
                '}';
    }
}
