package com.hyht.LateLetter.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Letter {
    private long letterId;
    private String title;
    private String letterContent;
    private Date deadline;
    private Date startDate;
    private int status;
    private long userId;
    private int publicFlag;
    private int readAuto;
    private int countDown;


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
