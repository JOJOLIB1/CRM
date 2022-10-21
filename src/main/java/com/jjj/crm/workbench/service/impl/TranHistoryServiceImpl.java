package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.TranHistoryMapper;
import com.jjj.crm.workbench.pojo.TranHistory;
import com.jjj.crm.workbench.service.TranHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.impl.TranHistoryServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-21 10:37
 */
@Service
public class TranHistoryServiceImpl implements TranHistoryService {
    @Autowired
    TranHistoryMapper tranHistoryMapper;
    @Override
    public List<TranHistory> queryTranHistoryByTranId(String tranId) {
        return tranHistoryMapper.selectTranHistoryByTranId(tranId);
    }
}
