package com.jjj.crm.workbench.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jjj.crm.workbench.mapper.ActivityMapper;
import com.jjj.crm.workbench.pojo.Activity;
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
}
