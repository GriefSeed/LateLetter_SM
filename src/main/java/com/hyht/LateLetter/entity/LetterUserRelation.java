package com.hyht.LateLetter.entity;

import org.springframework.stereotype.Component;

@Component
public class LetterUserRelation {
    private Long lURelationId;
    private Long letterId;
    private Long userId;
    private int relation;

    public LetterUserRelation(Long letterId, Long userId, int relation) {
        this.letterId = letterId;
        this.userId = userId;
        this.relation = relation;
    }

    public LetterUserRelation() {
    }

    public Long getlURelationId() {
        return lURelationId;
    }

    public void setlURelationId(Long lURelationId) {
        this.lURelationId = lURelationId;
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

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }
}
