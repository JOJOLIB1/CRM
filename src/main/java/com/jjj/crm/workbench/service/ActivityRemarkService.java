package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.ActivityRemark;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.ActivityRemarkService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-10 18:08
 */
public interface ActivityRemarkService {
    List<ActivityRemark> queryRemarkFromActivity(String activityId);
}
