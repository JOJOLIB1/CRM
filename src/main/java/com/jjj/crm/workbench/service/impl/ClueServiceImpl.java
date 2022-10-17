package com.jjj.crm.workbench.service.impl;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.commons.constants.ConstantConvert;
import com.jjj.crm.commons.util.DateUtils;
import com.jjj.crm.commons.util.UUIDUtils;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.workbench.mapper.ClueMapper;
import com.jjj.crm.workbench.mapper.ClueRemarkMapper;
import com.jjj.crm.workbench.mapper.ContactsMapper;
import com.jjj.crm.workbench.mapper.CustomerMapper;
import com.jjj.crm.workbench.pojo.*;
import com.jjj.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    ContactsMapper contactsMapper;
    @Autowired
    ClueRemarkMapper clueRemarkMapper;



    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertSelective(clue);
    }

    @Override
    public Clue queryClueById(String id) {
        return clueMapper.queryClueById(id);
    }

    @Override
    @Transactional
    public void doConvert(Map<String, Object> map) throws Exception{
        String clueId = (String) map.get(ConstantConvert.CLUE_ID);
        // 1. 根据clueId把数据原原本本找出来
        Clue clue = clueMapper.selectByPrimaryKey(clueId);
        // 2. 获取当前正在操作的用户
        User user = (User) map.get(ConstantConvert.CHANGE_USER);
        // 3. 抽取封装Customer对象
        Customer customer = new Customer();
        customer.setId(UUIDUtils.getUUID()); // id
        customer.setOwner(user.getId()); // 所有者
        customer.setName(clue.getCompany()); // 公司名
        customer.setWebsite(clue.getWebsite()); // 网址
        customer.setPhone(clue.getPhone());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateUtils.formatDateTime());
        customer.setContactSummary(clue.getContactSummary());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setDescription(clue.getDescription());
        customer.setAddress(clue.getAddress());
        customerMapper.insertSelective(customer);
        //--------------------
        // 4. 抽取封装Contacts对象
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtils.getUUID());
        contacts.setOwner(user.getId());
        contacts.setSource(clue.getSource());
        contacts.setCustomerId(customer.getId());
        contacts.setFullname(clue.getFullname());
        contacts.setAppellation(clue.getAppellation());
        contacts.setEmail(clue.getEmail());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateUtils.formatDateTime());
        contacts.setDescription(clue.getDescription());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setAddress(clue.getAddress());
        contactsMapper.insertSelective(contacts);
        // -----------
        // 5. 根据clueId把对应的线索备注原原本本查出来
        ClueRemarkExample clueRemarkExample = new ClueRemarkExample();
        clueRemarkExample.createCriteria().andClueIdEqualTo(clueId);
        List<ClueRemark> clueRemarks = clueRemarkMapper.selectByExample(clueRemarkExample);
        // ---------------------------------------------
        ContactsRemark contactsRemark = null;
        CustomerRemark customerRemark = null;
        List<ContactsRemark> contactsRemarkList = new ArrayList<>();
        List<CustomerRemark> customerRemarkList = new ArrayList<>();
        for (ClueRemark clueRemark : clueRemarks) {
            // 6. 封装多个ContactsRemark
            contactsRemark = ConstantConvert.convertRemark(ContactsRemark.class, clueRemark, customer, contacts);
            contactsRemarkList.add(contactsRemark);
            // 7. 封装多个CustomerRemark
            customerRemark = ConstantConvert.convertRemark(CustomerRemark.class, clueRemark, customer, contacts);
            customerRemarkList.add(customerRemark);
        }

    }
}
