package com.wzw.seckill.vo;

import com.wzw.seckill.validator.IsMobile;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * 登录参数
 *
 * @author wanzhiwen
 * @Date 2022/2/8
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    private String password;
}
