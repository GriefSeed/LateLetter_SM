package com.hyht.LateLetter.dto;

import com.hyht.LateLetter.entity.Letter;

public class LetterWithFiles {
    private Letter letter;
    private String[] picList;
    private String[] receiverList;

    public LetterWithFiles() {
    }

    public String[] getReceiverList() {
        return receiverList;
    }

    public void setReceiverList(String[] receiverList) {
        this.receiverList = receiverList;
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public String[] getPicList() {
        return picList;
    }

    public void setPicList(String[] picList) {
        this.picList = picList;
    }
}
