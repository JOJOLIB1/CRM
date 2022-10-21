package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.Chart;
import com.jjj.crm.workbench.pojo.Tran;

import java.util.List;
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


    /**
     * 更改新的阶段
     */
    int saveNewStage(Tran tran);

    /**
     * 根据阶段查询各个阶段的交易数
     */
    List<Chart> queryCountOfTranGroupByStage();
}
