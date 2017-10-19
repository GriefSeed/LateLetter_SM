package com.hyht.LateLetter.dto;

public class LetterCollectionList {
    private Long[] letterId;
    private Long userId;

    public LetterCollectionList() {
    }

    public LetterCollectionList(Long[] letterId, Long userId) {
        this.letterId = letterId;
        this.userId = userId;
    }

    public Long[] getLetterId() {
        return letterId;
    }

    public void setLetterId(Long[] letterId) {
        this.letterId = letterId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
