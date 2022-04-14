package com.wzw.seckill.rabbitmq;

import com.wzw.seckill.pojo.SeckillMessage;
import com.wzw.seckill.pojo.SeckillOrder;
import com.wzw.seckill.pojo.User;
import com.wzw.seckill.service.IGoodsService;
import com.wzw.seckill.service.IOrderService;
import com.wzw.seckill.utils.JsonUtil;
import com.wzw.seckill.vo.GoodsVo;
import com.wzw.seckill.vo.RespBean;
import com.wzw.seckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wanzhiwen
 * @Date 2022/4/11
 */
@Service
@Slf4j
public class MQReceiver {


    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IOrderService orderService;

    @RabbitListener(queues = "seckillQueue")
    public void receiveSeckillMessage(String msg){
        log.info("接收的消息：" + msg);
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(msg, SeckillMessage.class);
        Long goodsId = seckillMessage.getGoodsId();
        User user = seckillMessage.getUser();
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        if (goodsVo.getStockCount() < 1){
            return;
        }
        SeckillOrder seckillOrder = (SeckillOrder)redisTemplate.opsForValue().get("order" + user.getId() + ":" + goodsId);
        if (seckillOrder != null) {
            return;
        }
        orderService.seckill(user, goodsVo);
    }

}
