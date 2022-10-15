package com.jjj.crm.workbench.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jjj.crm.workbench.mapper.ActivityMapper;
import com.jjj.crm.workbench.pojo.Activity;
import com.jjj.crm.workbench.pojo.ActivityExample;
import com.jjj.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.impl.ActivityServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-02 21:42
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper mapper;

    @Override
    public int saveActivity(Activity activity) {
        return mapper.insertSelective(activity);
    }

    @Override
    public PageInfo<Activity> queryActivitiesForPages(Integer pageSize, Integer pageNo, Activity condition) {
        // 开始的页数与每一页的长度
        PageHelper.startPage(pageNo, pageSize);
        // 条件查询
        List<Activity> activities = mapper.unionSelectByExample(condition);
        // 封装pageInfo,导航分页是5,并返回
        return new PageInfo<Activity>(activities, 5);
    }

    @Override
    public int deleteActivities(String[] ids) {
        return mapper.batchDeleteByIds(ids);
    }

    @Override
    public Activity queryForUpdate(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateActivity(Activity activity) {
        return mapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public List<Activity> queryActivitiesByIds(String[] ids) {
        return mapper.batchSelectByPrimaryKey(ids);
    }

    @Override
    public int insertByList(List<Activity> activities) {
        return mapper.batchInsertActivities(activities);
    }

    @Override
    public Activity queryDetailOfActivity(String id) {
        return mapper.selectForDetail(id);
    }

    @Override
    public List<Activity> queryActivityByClueId(String clueId) {
        return mapper.selectActivityByClueId(clueId);
    }

    @Override
    public List<Activity> queryUnboundedActivityByName(String clueId, String name) {
        return mapper.selectUnboundedActivityByName(clueId, name);
    }
}
