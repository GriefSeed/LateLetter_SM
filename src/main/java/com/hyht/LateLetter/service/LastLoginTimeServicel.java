package com.hyht.LateLetter.service;

public interface LastLoginTimeServicel {

    /**
     * 更新用户最近登陆时间时间
     * @param userId
     * @param type 1 表示 非连续登陆，加30天，2 表示连续登陆，加 30 * 3 天
     * @return 0 表示失败 1 表示成功
     */
    int updateUserLastLoginTime(Long userId, int type);
}
