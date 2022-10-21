package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.TranRemarkMapper;
import com.jjj.crm.workbench.pojo.TranRemark;
import com.jjj.crm.workbench.service.TranRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.impl.TranRemarkServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-21 10:34
 */
@Service
public class TranRemarkServiceImpl implements TranRemarkService {
    @Autowired
    TranRemarkMapper tranRemarkMapper;
    @Override
    public List<TranRemark> queryTranRemarkByTranId(String tranId) {
        return tranRemarkMapper.selectTranRemarkByTranId(tranId);
    }
}
