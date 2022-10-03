package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.Activity;

/**
 * @className: com.jjj.crm.workbench.service.AcitvityService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-02 21:42
 */
public interface ActivityService {
    /**
     * 按条件插入,插入市场活动
     * @param activity 市场活动对象
     * @return 影响行数
     */
    int saveActivity(Activity activity);
}
