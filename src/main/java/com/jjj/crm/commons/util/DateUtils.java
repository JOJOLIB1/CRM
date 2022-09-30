package com.jjj.crm.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: com.jjj.crm.commons.util.DateUtils
 * @description:
 * @author: 江骏杰
 * @create: 2022-09-30 20:53
 */
public class DateUtils {

    /**
     * @return   返回当前时间的格式化字符串
     */
    public static String formatDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * @param date 日期
     * @return 返回指定日期的格式化字符串
     */

    public static String formatDateTime(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }





}
