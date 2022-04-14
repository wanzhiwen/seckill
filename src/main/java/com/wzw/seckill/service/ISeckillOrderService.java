package com.wzw.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzw.seckill.pojo.SeckillOrder;
import com.wzw.seckill.pojo.User;

/**
 * @author wanzhiwen
 * @Date 2022/2/24
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {
    Long getResult(User user, Long goodsId);
}
