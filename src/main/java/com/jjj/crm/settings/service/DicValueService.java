package com.jjj.crm.settings.service;

import com.jjj.crm.settings.pojo.DicValue;

import java.util.List;
import java.util.Map;

/**
 * @className: com.jjj.crm.settings.service.DicValueService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-14 0:21
 */
public interface DicValueService {
    /**
     * 按照typeCode导入想要的数据字典纸
     * @param typeCode
     * @return
     */
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
