package com.hyht.LateLetter.dto;


/**
 * 用于收藏
 */
public class LetterCollection {
    private Long letterId;
    private Long userId;

    public LetterCollection() {
    }

    public LetterCollection(Long letterId, Long userId) {
        this.letterId = letterId;
        this.userId = userId;
    }

    public Long getLetterId() {
        return letterId;
    }

    public void setLetterId(Long letterId) {
        this.letterId = letterId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
