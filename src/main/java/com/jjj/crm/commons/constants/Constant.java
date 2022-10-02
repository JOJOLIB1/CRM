package com.jjj.crm.commons.constants;

/**
 * @className: com.jjj.crm.commons.constants.Constant
 * @description:
 * @author: 江骏杰
 * @create: 2022-09-30 20:58
 */
public class Constant {
    /**
     * RETURN_MSG_CODE_SUCCESS 用户登录成功代码
     */
    public static final String RETURN_MSG_CODE_SUCCESS = "1";
    /**
     * RETURN_MSG_CODE_FAIL 用户登录失败代码
     */
    public static final String RETURN_MSG_CODE_FAIL = "0";
    /**
     * SESSION_USER 会话域user对象的key
     */
    public static final String SESSION_USER = "sessionUser";
    /**
     * LOGIN_ACT cookie保存的用户账号
     */
    public static final String LOGIN_ACT = "loginAct";
    /**
     * LOGIN_PWD cookie保存的用户密码
     */
    public static final String LOGIN_PWD = "loginPwd";
    /**
     * DEFAULT_VALUE 一般的默认值
     */
    public static final String DEFAULT_VALUE = "";

    /**
     * REQUEST_ALL_USERS 请求域中存放所有用户的key
     */
    public static final String REQUEST_ALL_USERS = "reqUsers";
}
