package com.jjj.crm.workbench.service;


import java.util.List;

/**
 * @className: com.jjj.crm.workbench.service.CustomerService
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-20 21:44
 */
public interface CustomerService {
    /**
     * 根据name模糊查询市场活动名字
     */
    List<String> queryCustomerByName(String name);

}
