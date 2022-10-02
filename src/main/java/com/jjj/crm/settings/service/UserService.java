package com.jjj.crm.settings.service;

import com.jjj.crm.settings.pojo.User;

import java.util.List;

/**
 * @className: com.jjj.crm.settings.service.UserService
 * @description:
 * @author: 江骏杰
 * @create: 2022-09-29 23:20
 */
public interface UserService {
    /**
     * 根据账号密码查询用户
     * @return 返回应该实体类对象,用于封装json
     */
    User queryByLoginActAndPwd(String loginAct, String loginPwd);

    /**
     * 查询所有的用户
     * @return 返回所有用户的集合
     */
    List<User> queryAllUsers();
}