package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.ActivityRemarkMapper;
import com.jjj.crm.workbench.pojo.ActivityRemark;
import com.jjj.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.impl.ActivityRemarkImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-10 18:09
 */
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    ActivityRemarkMapper mapper;

    @Override
    public List<ActivityRemark> queryRemarkFromActivity(String activityId) {
        return mapper.selectRemarkByActivityId(activityId);
    }
}
