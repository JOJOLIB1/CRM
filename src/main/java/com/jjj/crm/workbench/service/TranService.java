package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.Tran;

import java.util.Map;

/**
 * @className: com.jjj.crm.workbench.service.TranService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-21 8:50
 */
public interface TranService {
    /**
     * 保存交易
     */
    void doSaveTran(Map<String, Object> map);

    /**
     * 根据id查询交易
     */
    Tran queryTranById(String id);
}
