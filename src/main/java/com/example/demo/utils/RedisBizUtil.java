package com.example.demo.utils;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-27 22:41
 **/
public class RedisBizUtil {

    public static final String BIZ_POST = "post";
    public static final String BIZ_LIKE = "like";
    public static final String BIZ_LIKE_COUNT = "count";

    public static final String SEPARATE = ":";

    /**
     * 点赞Key
     * @param userId  用户id
     * @return like:post:132456798
     */
    public static String getLikeKey(Long userId) {
        return BIZ_LIKE
                + SEPARATE
                + BIZ_POST
                + SEPARATE
                + userId;
    }

    /**
     * 获得赞数Key
     * @return  like:count
     */
    public static String getLikeCountKey() {
        return BIZ_LIKE
                + SEPARATE
                + BIZ_LIKE_COUNT;
    }



    public static void main(String[] args) {
        String likeKey = RedisBizUtil.getLikeKey(11L);
        System.out.println(likeKey);

        String likeCountKey = RedisBizUtil.getLikeCountKey();
        System.out.println(likeCountKey);



    }




}
