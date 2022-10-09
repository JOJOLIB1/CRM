package com.jjj.crm.workbench.service;

import com.github.pagehelper.PageInfo;
import com.jjj.crm.workbench.pojo.Activity;

import java.util.List;

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

    /**
     *
     * @param pageSize 一页显示的条目数
     * @param pageNo 当前页数
     * @param condition 条件查询的条件
     * @return pageInfo对象
     */
    PageInfo<Activity> queryActivitiesForPages(Integer pageSize, Integer pageNo, Activity condition);

    /**
     * 删除活动
     * @param ids 待删除的id数组
     * @return 影响行数
     */
    int deleteActivities(String[] ids);

    /**
     * 查询修改之前的市场活动
     * @param id 市场活动的id
     * @return 查询出来的数据
     */
    Activity queryForUpdate(String id);

    /**
     * 更新市场活动
     * @param activity 修改的内容
     * @return 影响行数
     */
    int updateActivity(Activity activity);

    /**
     * 选择指定的市场互动
     * @param ids
     * @return
     */
    List<Activity> queryActivitiesByIds(String[] ids);

    /**
     * 批量插入数据
     * @param activities
     * @return
     */
    int insertByList(List<Activity> activities);
}
