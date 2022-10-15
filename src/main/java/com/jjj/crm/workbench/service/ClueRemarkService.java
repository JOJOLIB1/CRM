package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.ClueRemark;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.ClueRemarkService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-15 9:05
 */
public interface ClueRemarkService {
    /**
     * 根据clueId查询对应的备注信息
     * @param clueId
     * @return
     */
    List<ClueRemark> queryClueRemarkByClueId(String clueId);
}
