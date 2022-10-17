package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.Clue;

import java.util.Map;

/**
 * @className: com.jjj.crm.workbench.service.ClueService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-14 21:40
 */
public interface ClueService {
    /**
     * 保存线索
     * @param clue 线索对象
     * @return 影响行数
     */
    int saveCreateClue(Clue clue);

    /**
     * 根据id查找线索
     * @param id clueId
     * @return
     */
    Clue queryClueById(String id);

    /**
     * 转换线索
     * @param map 封装的map集合,含有Tran实体类对象,clueId线索id,isTran是否创建交易
     */
    void doConvert(Map<String, Object> map) throws Exception;
}
