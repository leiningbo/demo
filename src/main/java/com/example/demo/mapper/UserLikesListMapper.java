package com.example.demo.mapper;

import com.example.demo.entity.UserLikesList;
import com.example.demo.entity.UserLikesListExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserLikesListMapper {
    int countByExample(UserLikesListExample example);

    int deleteByExample(UserLikesListExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLikesList record);

    int insertSelective(UserLikesList record);

    List<UserLikesList> selectByExample(UserLikesListExample example);

    UserLikesList selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLikesList record, @Param("example") UserLikesListExample example);

    int updateByExample(@Param("record") UserLikesList record, @Param("example") UserLikesListExample example);

    int updateByPrimaryKeySelective(UserLikesList record);

    int updateByPrimaryKey(UserLikesList record);

    /**
     * 检查用户是否已经给这个人点过赞
     * @param userId 用户id
     * @param getLikeUserId 获赞者id
     */
    int checkUserIsLike(@Param("userId")Long userId,@Param("getLikeUserId") Long getLikeUserId);

    /**
     * 点赞
     * @param userId 用户id
     * @param getLikeUserId 获赞者id
     */
    int userLike(@Param("userId")Long userId,@Param("getLikeUserId") Long getLikeUserId);
}