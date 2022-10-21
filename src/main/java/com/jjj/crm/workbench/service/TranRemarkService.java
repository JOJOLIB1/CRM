package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.TranRemark;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.TranRemarkService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-21 10:28
 */
public interface TranRemarkService {
    List<TranRemark> queryTranRemarkByTranId(String tranId);
}
