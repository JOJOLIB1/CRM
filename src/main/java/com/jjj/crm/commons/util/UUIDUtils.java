package com.jjj.crm.commons.util;

import java.util.UUID;

/**
 * @className: com.jjj.crm.commons.util.UUIDUtils
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-02 21:49
 */
public class UUIDUtils {
    /**
     *
     * @return 返回32位定长UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
