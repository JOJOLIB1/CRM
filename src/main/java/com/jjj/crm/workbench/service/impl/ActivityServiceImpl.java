package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.ActivityMapper;
import com.jjj.crm.workbench.pojo.Activity;
import com.jjj.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
