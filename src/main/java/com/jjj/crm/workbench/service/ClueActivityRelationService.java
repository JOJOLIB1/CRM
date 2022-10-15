package com.jjj.crm.workbench.service;

import com.jjj.crm.workbench.pojo.ClueActivityRelation;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.ClueActivityRelationService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-15 10:57
 */
@Service
public interface ClueActivityRelationService {
    /**
     * 保存市场活动和线索的联系关系
     * @param clueActivityRelation 联系对象
     * @return 印象行数
     */
    int saveCreateCLueActivityRelation(List<ClueActivityRelation> clueActivityRelation);

    /**
     * 删除市场活动和线索的关系
     * @param clueActivityRelation 待删除的条件
     */
    int deleteClueActivityRelation(ClueActivityRelation clueActivityRelation);
}
