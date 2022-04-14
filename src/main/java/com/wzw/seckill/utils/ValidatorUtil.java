package com.wzw.seckill.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 校验手机号码
 *
 * @author wanzhiwen
 * @Date 2022/2/9
 */
public class ValidatorUtil {
    private  static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    public static boolean isMobile(String mobile){
        if (mobile == null) return false;
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }

}
