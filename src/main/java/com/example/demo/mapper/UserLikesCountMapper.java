package com.example.demo.mapper;


import com.example.demo.entity.UserLikesCount;
import com.example.demo.entity.UserLikesCountExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserLikesCountMapper {
    int countByExample(UserLikesCountExample example);

    int deleteByExample(UserLikesCountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLikesCount record);

    int insertSelective(UserLikesCount record);

    List<UserLikesCount> selectByExample(UserLikesCountExample example);

    UserLikesCount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLikesCount record, @Param("example") UserLikesCountExample example);

    int updateByExample(@Param("record") UserLikesCount record, @Param("example") UserLikesCountExample example);

    int updateByPrimaryKeySelective(UserLikesCount record);

    int updateByPrimaryKey(UserLikesCount record);

    /**
     *
     * @param userLikesId 获赞者id
     * @return 获赞者
     */
    UserLikesCount getByUserId(Long userLikesId);

    /**
     * 总赞数 +1
     * @param userLikesId 获赞者id
     * @return 1 or 0
     */
    int updateUserLikeCount(@Param(value = "userLikesId") Long userLikesId);
}