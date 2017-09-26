package com.hyht.LateLetter.entity;

import org.springframework.stereotype.Component;

@Component
public class BFile {
    private long bfileId;
    private long letterId;
    private String fileUrl;
    private int fileType;

    public BFile(long letterId, String fileUrl, int fileType) {
        this.letterId = letterId;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }

    public BFile() {
    }


    public long getBfileId() {
        return bfileId;
    }

    public void setBfileId(long bfileId) {
        this.bfileId = bfileId;
    }

    public long getLetterId() {
        return letterId;
    }

    public void setLetterId(long letterId) {
        this.letterId = letterId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "BFile{" +
                "bfileId=" + bfileId +
                ", letterId=" + letterId +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileType=" + fileType +
                '}';
    }
}
