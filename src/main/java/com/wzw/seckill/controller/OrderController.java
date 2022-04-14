package com.wzw.seckill.controller;


import com.wzw.seckill.pojo.User;
import com.wzw.seckill.service.IOrderService;
import com.wzw.seckill.vo.OrderDetailVo;
import com.wzw.seckill.vo.RespBean;
import com.wzw.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanzhiwen
 * @since 2022-02-24
 */
@Controller
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private IOrderService orderService;

    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user, Long orderId){
        if(user==null) return RespBean.error(RespBeanEnum.SESSION_ERROR);
        OrderDetailVo orderDetailVo = orderService.detail(orderId);
        return RespBean.success(orderDetailVo);
    }
}
