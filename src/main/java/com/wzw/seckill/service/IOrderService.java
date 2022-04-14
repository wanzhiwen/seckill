package com.wzw.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzw.seckill.pojo.Order;
import com.wzw.seckill.pojo.User;
import com.wzw.seckill.vo.GoodsVo;
import com.wzw.seckill.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanzhiwen
 * @since 2022-02-24
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goods);

    OrderDetailVo detail(Long orderId);

    String createPath(User user, Long goodsId);

    boolean checkPath(User user, Long goodsId, String path);

    Boolean checkCaptcha(User user, Long goodsId, String captcha);
}
