package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserLikesList {
    private Long id;

    private Long userId;

    private Long userLikesId;

    private Date createTime;

    private Date updateTime;

    private Byte isDelete;

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

    public Long getUserLikesId() {
        return userLikesId;
    }

    public void setUserLikesId(Long userLikesId) {
        this.userLikesId = userLikesId;
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

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}