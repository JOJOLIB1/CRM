package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.ActivityRemarkMapper;
import com.jjj.crm.workbench.pojo.ActivityRemark;
import com.jjj.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.impl.ActivityRemarkImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-10 18:09
 */
@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    ActivityRemarkMapper mapper;

    @Override
    public List<ActivityRemark> queryRemarkFromActivity(String activityId) {
        return mapper.selectRemarkByActivityId(activityId);
    }

    @Override
    public int addRemarkForActivity(ActivityRemark remark) {
        return mapper.insertSelective(remark);
    }

    @Override
    public int deleteRemarkById(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRemark(ActivityRemark remark) {
        return mapper.updateByPrimaryKeySelective(remark);
    }
}
