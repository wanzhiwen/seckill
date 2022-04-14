package com.wzw.seckill.vo;

import com.wzw.seckill.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wanzhiwen
 * @Date 2022/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {
    private BigDecimal seckillPrice;
    private BigDecimal goodsPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
