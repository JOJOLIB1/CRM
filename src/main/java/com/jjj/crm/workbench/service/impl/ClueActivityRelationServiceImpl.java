package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.ClueActivityRelationMapper;
import com.jjj.crm.workbench.pojo.ClueActivityRelation;
import com.jjj.crm.workbench.pojo.ClueActivityRelationExample;
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
    @Autowired
    ClueActivityRelationExample example;
    @Override
    public int saveCreateCLueActivityRelation(List<ClueActivityRelation> clueActivityRelations) {
        return clueActivityRelationMapper.insertAllClueActivityRelation(clueActivityRelations);
    }

    @Override
    public int deleteClueActivityRelation(ClueActivityRelation clueActivityRelation) {
        // 因为是单例的,所以每一次调用都要清理一下,实际上没啥用
        example.clear();
        example.createCriteria().andActivityIdEqualTo(clueActivityRelation.getActivityId()).andClueIdEqualTo(clueActivityRelation.getClueId());
        return clueActivityRelationMapper.deleteByExample(example);
    }
}
