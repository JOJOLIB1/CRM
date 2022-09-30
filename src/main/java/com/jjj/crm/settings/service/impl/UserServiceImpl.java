package com.jjj.crm.settings.service.impl;

import com.jjj.crm.settings.mapper.UserMapper;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.settings.pojo.UserExample;
import com.jjj.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @className: com.jjj.crm.settings.service.impl.UserServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-09-29 23:22
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;
    // 0. 创建对应的条件查询对象
    @Autowired
    UserExample userExample;

    public User queryByLoginActAndPwd(String loginAct, String loginPwd) {
        // 1. 编辑查询条件
        // where loginAct = ? and loginPwd = ?
        userExample.createCriteria().andLoginActEqualTo(loginAct).andLoginPwdEqualTo(loginPwd);
        // 2. 执行查询语句
        List<User> users = mapper.selectByExample(userExample);
        // criteria是单例的,所以要clear避免影响
        userExample.clear();
        // 3.:如果查询不到是空集合
        // 获取对应的对象
        return users.isEmpty() ? null : users.get(0);
    }
}
