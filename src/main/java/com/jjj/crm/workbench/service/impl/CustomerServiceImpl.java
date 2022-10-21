package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.workbench.mapper.CustomerMapper;
import com.jjj.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.impl.CustomerServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-20 21:46
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<String> queryCustomerByName(String name) {
        return customerMapper.selectCustomerByName(name);
    }

}
