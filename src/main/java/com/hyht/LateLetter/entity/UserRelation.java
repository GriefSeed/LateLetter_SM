package com.hyht.LateLetter.entity;


import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserRelation {
    private Long uRelationId;
    private Long masterUserId;
    private Long otherUserId;
    private int relation;
    private Date startTime;



    public UserRelation(Long masterUserId, Long otherUserId, int relation, Date startTime) {
        this.masterUserId = masterUserId;
        this.otherUserId = otherUserId;
        this.relation = relation;
        this.startTime = startTime;
    }

    public UserRelation(Long masterUserId, Long otherUserId, int relation) {
        this.masterUserId = masterUserId;
        this.otherUserId = otherUserId;
        this.relation = relation;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public UserRelation() {
    }

    public Long getuRelationId() {
        return uRelationId;
    }

    public void setuRelationId(Long uRelationId) {
        this.uRelationId = uRelationId;
    }

    public Long getMasterUserId() {
        return masterUserId;
    }

    public void setMasterUserId(Long masterUserId) {
        this.masterUserId = masterUserId;
    }

    public Long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(Long otherUserId) {
        this.otherUserId = otherUserId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
