package com.hyht.LateLetter.dto;


/**
 * 用于用户界面计数
 */
public class CountNum {
    //我的时间
    private int userTime;
    //直接收件人
    private int myReceiverNum;
    //我的关注
    private int myAttentionNum;
    //已写迟书
    private int myLetterNum;
    //收到迟书
    private int myAcceptLetterNum;
    //我的收藏
    private int myCollectionNum;

    public CountNum(int userTime, int myReceiverNum, int myAttentionNum, int myLetterNum, int myAcceptLetterNum, int myCollectionNum) {
        this.userTime = userTime;
        this.myReceiverNum = myReceiverNum;
        this.myAttentionNum = myAttentionNum;
        this.myLetterNum = myLetterNum;
        this.myAcceptLetterNum = myAcceptLetterNum;
        this.myCollectionNum = myCollectionNum;
    }

    public CountNum() {
    }

    public int getUserTime() {
        return userTime;
    }

    public void setUserTime(int userTime) {
        this.userTime = userTime;
    }

    public int getMyReceiverNum() {
        return myReceiverNum;
    }

    public void setMyReceiverNum(int myReceiverNum) {
        this.myReceiverNum = myReceiverNum;
    }

    public int getMyAttentionNum() {
        return myAttentionNum;
    }

    public void setMyAttentionNum(int myAttentionNum) {
        this.myAttentionNum = myAttentionNum;
    }

    public int getMyLetterNum() {
        return myLetterNum;
    }

    public void setMyLetterNum(int myLetterNum) {
        this.myLetterNum = myLetterNum;
    }

    public int getMyAcceptLetterNum() {
        return myAcceptLetterNum;
    }

    public void setMyAcceptLetterNum(int myAcceptLetterNum) {
        this.myAcceptLetterNum = myAcceptLetterNum;
    }

    public int getMyCollectionNum() {
        return myCollectionNum;
    }

    public void setMyCollectionNum(int myCollectionNum) {
        this.myCollectionNum = myCollectionNum;
    }
}
