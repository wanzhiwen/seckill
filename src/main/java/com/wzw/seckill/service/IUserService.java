package com.wzw.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzw.seckill.pojo.User;
import com.wzw.seckill.vo.LoginVo;
import com.wzw.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanzhiwen
 * @since 2022-02-08
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    //根据cookie获取用户
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);

    RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response);
}
