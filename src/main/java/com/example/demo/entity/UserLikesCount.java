package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserLikesCount {
    private Long id;

    private Long userId;

    private Long userLikesCount;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserLikesCount() {
        return userLikesCount;
    }

    public void setUserLikesCount(Long userLikesCount) {
        this.userLikesCount = userLikesCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}