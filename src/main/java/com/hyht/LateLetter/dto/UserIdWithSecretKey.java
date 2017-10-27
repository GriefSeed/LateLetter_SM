package com.hyht.LateLetter.dto;

public class UserIdWithSecretKey {
    private Long userId;
    private String secretKey;

    public UserIdWithSecretKey(Long userId, String secretKey) {
        this.userId = userId;
        this.secretKey = secretKey;
    }

    public UserIdWithSecretKey() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
