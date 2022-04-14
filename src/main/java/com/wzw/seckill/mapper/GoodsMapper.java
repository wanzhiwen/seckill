package com.wzw.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzw.seckill.pojo.Goods;
import com.wzw.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanzhiwen
 * @since 2022-02-24
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
