package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.ClueRemarkMapper;
import com.jjj.crm.workbench.pojo.ClueRemark;
import com.jjj.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.impl.ClueRemarkServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-15 9:06
 */
@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Autowired
    ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> queryClueRemarkByClueId(String clueId) {
        return clueRemarkMapper.selectRemarkByClueId(clueId);
    }
}
