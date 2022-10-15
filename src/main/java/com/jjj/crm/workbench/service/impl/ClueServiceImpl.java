package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.ClueMapper;
import com.jjj.crm.workbench.pojo.Clue;
import com.jjj.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: com.jjj.crm.workbench.service.impl.ClueServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-14 21:41
 */
@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    ClueMapper clueMapper;

    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertSelective(clue);
    }

    @Override
    public Clue queryClueById(String id) {
        return clueMapper.queryClueById(id);
    }
}
