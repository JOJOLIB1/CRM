package com.jjj.crm.settings.service.impl;

import com.jjj.crm.settings.mapper.DicValueMapper;
import com.jjj.crm.settings.pojo.DicValue;
import com.jjj.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @className: com.jjj.crm.settings.service.impl.DicValueServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-14 0:22
 */
@Service
public class DicValueServiceImpl implements DicValueService {
    @Autowired
    private DicValueMapper mapper;

    @Override
    public List<DicValue> queryDicValueForCLue(String typeCode) {
        return mapper.selectClueDicByTypeCodes(typeCode);
    }
}
