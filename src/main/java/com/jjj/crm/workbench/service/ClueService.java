package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.Clue;

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
}
