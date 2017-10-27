package com.hyht.LateLetter.dto;

public class UserIdWithOtherUserId {
    private Long userId;
    private Long otherUserId;

    public UserIdWithOtherUserId(Long userId, Long otherUserId) {
        this.userId = userId;
        this.otherUserId = otherUserId;
    }

    public UserIdWithOtherUserId() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(Long otherUserId) {
        this.otherUserId = otherUserId;
    }
}
