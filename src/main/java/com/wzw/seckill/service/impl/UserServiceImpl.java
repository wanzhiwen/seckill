package com.wzw.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzw.seckill.exception.GlobalException;
import com.wzw.seckill.mapper.UserMapper;
import com.wzw.seckill.pojo.User;
import com.wzw.seckill.service.IUserService;
import com.wzw.seckill.utils.CookieUtil;
import com.wzw.seckill.utils.MD5Util;
import com.wzw.seckill.utils.UUIDUtil;
import com.wzw.seckill.vo.LoginVo;
import com.wzw.seckill.vo.RespBean;
import com.wzw.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanzhiwen
 * @since 2022-02-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        User user = userMapper.selectById(mobile);
        if (user == null) throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        if (!MD5Util.fromPassToDBPass(password, user.getSalt()).equals(user.getPassword())) throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        //生成cookie
        String ticket = UUIDUtil.uuid();
        //将用户信息存入redis
        redisTemplate.opsForValue().set("user:" + ticket ,user);
        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success(ticket);
    }


    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isEmpty(userTicket)) return null;
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if(user != null){
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }

    @Override
    public RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = getUserByCookie(userTicket, request, response);
        if (user == null) throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        user.setPassword(MD5Util.inputPassToDBPass(password, user.getSalt()));
        int result = userMapper.updateById(user);
        if(result == 1){
            redisTemplate.delete("user:" + userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
    }

}
