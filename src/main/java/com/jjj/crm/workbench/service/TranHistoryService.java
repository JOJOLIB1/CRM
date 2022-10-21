package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.TranHistory;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.TranHistroyService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-21 10:33
 */
public interface TranHistoryService {
    List<TranHistory> queryTranHistoryByTranId(String tranId);
}
