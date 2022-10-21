package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.commons.constants.ConstantTranCreate;
import com.jjj.crm.commons.util.DateUtils;
import com.jjj.crm.commons.util.UUIDUtils;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.workbench.mapper.CustomerMapper;
import com.jjj.crm.workbench.mapper.TranHistoryMapper;
import com.jjj.crm.workbench.mapper.TranMapper;
import com.jjj.crm.workbench.mapper.TranRemarkMapper;
import com.jjj.crm.workbench.pojo.Customer;
import com.jjj.crm.workbench.pojo.Tran;
import com.jjj.crm.workbench.pojo.TranHistory;
import com.jjj.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

/**
 * @className: com.jjj.crm.workbench.service.impl.TranServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-21 8:51
 */
@Service
public class TranServiceImpl implements TranService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    TranMapper tranMapper;
    @Autowired
    TranHistoryMapper tranHistoryMapper;

    @Transactional
    @Override
    public void doSaveTran(Map<String, Object> map) {
        String customerName = (String) map.get(ConstantTranCreate.CUSTOMER_NAME);
        User user = (User) map.get(Constant.SESSION_USER);
        // 先把id查出来
        String customerId = customerMapper.selectCustomerIdByName(customerName);
        // 如果没有就创建
        if (customerId == null) {
            Customer customer = new Customer();
            customer.setId(UUIDUtils.getUUID());
            customer.setOwner(user.getId());
            customer.setName(customerName);
            customer.setCreateBy(user.getId());
            customer.setCreateTime(DateUtils.formatDateTime());
            customerId = customer.getId();
            customerMapper.insertSelective(customer);
        }
        // 创建交易
        Tran tran = (Tran) map.get(ConstantTranCreate.TRAN_OBJECT);
        tran.setId(UUIDUtils.getUUID());
        tran.setCustomerId(customerId);
        tran.setCreateBy(user.getId());
        tran.setCreateTime(DateUtils.formatDateTime());
        tranMapper.insertSelective(tran);
        // 创建交易历史信息
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(UUIDUtils.getUUID());
        tranHistory.setStage(tran.getStage());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setTranId(tran.getId());
        tranHistory.setCreateBy(user.getId());
        tranHistory.setCreateTime(DateUtils.formatDateTime());
        tranHistoryMapper.insertSelective(tranHistory);
    }

    @Override
    public Tran queryTranById(String id) {
        return tranMapper.selectTranById(id);
    }
}
