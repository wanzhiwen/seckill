package com.wzw.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzw.seckill.pojo.Goods;
import com.wzw.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanzhiwen
 * @since 2022-02-24
 */
public interface IGoodsService extends IService<Goods> {

    //获取商品列表
    List<GoodsVo> findGoodsVo();

    //获取商品详情
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
