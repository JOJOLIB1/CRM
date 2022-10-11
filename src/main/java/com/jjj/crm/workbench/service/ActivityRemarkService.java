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
    /**
     * 查询市场活动对应的留言
     * @param activityId 市场活动id
     * @return
     */
    List<ActivityRemark> queryRemarkFromActivity(String activityId);

    /**
     * 添加市场活动留言
     * @param remark 市场活动的实体类对象
     */
    int addRemarkForActivity(ActivityRemark remark);


    /**
     * 通过id删除留言
     * @param id 市场活动留言id
     */
    int deleteRemarkById(String id);
}
