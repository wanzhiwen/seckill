package com.wzw.seckill.config;

import com.wzw.seckill.pojo.User;

/**
 * @author wanzhiwen
 * @Date 2022/4/13
 */
public class UserContext {

    private static ThreadLocal<User> userHolder = new ThreadLocal<User>();

    public static void setUser(User user){
        userHolder.set(user);
    }

    public static User getUser(){
        return userHolder.get();
    }

}
