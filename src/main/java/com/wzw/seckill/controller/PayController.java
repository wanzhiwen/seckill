package com.wzw.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lly835.bestpay.model.PayResponse;
import com.wzw.pay.service.IPayService;
import com.wzw.seckill.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;

/**
 * @author wanzhiwen
 * @Date 2022/7/19
 */
@Controller
@RequestMapping("/pay")
public class PayController {


    @Reference(version = "0.2.0")
    private IPayService payService;

    @RequestMapping("/createPayCode")
    @ResponseBody
    public RespBean create(@RequestParam("orderId") String orderId, @RequestParam("amount") BigDecimal amount){
        String payCode = payService.create(orderId, amount);
        return RespBean.success(payCode);
    }
}
