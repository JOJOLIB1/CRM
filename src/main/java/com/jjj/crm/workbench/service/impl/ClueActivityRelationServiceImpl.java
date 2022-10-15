package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.ClueActivityRelationMapper;
import com.jjj.crm.workbench.pojo.ClueActivityRelation;
import com.jjj.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.impl.ClueActivityRelationServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-15 10:59
 */
@Service
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {
    @Autowired
    ClueActivityRelationMapper clueActivityRelationMapper;
    @Override
    public int saveCreateCLueActivityRelation(List<ClueActivityRelation> clueActivityRelations) {
        return clueActivityRelationMapper.insertAllClueActivityRelation(clueActivityRelations);
    }
}
