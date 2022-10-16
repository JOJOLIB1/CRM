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
     * LOGIN_ACT cookie 保存的用户账号
     */
    public static final String LOGIN_ACT = "loginAct";
    /**
     * LOGIN_PWD cookie 保存的用户密码
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

    /**
     * DEFAULT_FAILURE_MESSAGE 通用报错信息
     */
    public static final String DEFAULT_FAILURE_MESSAGE = "系统繁忙,请稍后!";

    /**
     * REQUEST_ACTIVITY 请求域中的市场活动详情信息key
     */
    public static final String REQUEST_ACTIVITY = "activity";

    /**
     * REQUEST_ACTIVITY_REMARK 请求域中的某市场活动的所有留言信息key
     */
    public static final String REQUEST_ACTIVITY_REMARK = "activityRemarks";

    /**
     * DEFAULT_EDIT_FLAG 修改备注的默认情况
     */
    public static final String DEFAULT_EDIT_FLAG = "0";

    /**
     * CHANGED_EDIT_FLAG 修改备注后的情况
     */
    public static final String CHANGED_EDIT_FLAG = "1";

    /**
     * DIC_CLUE_CREATE_LIST 线索主页面所需要的数据字典类型
     */
    public static final String[] DIC_CLUE_CREATE_LIST = new String[] {"appellation", "clueState", "source"};

    /**
     * 请求域及其供给转换页面的数据字典
     */
    public static final String DIC_CLUE_CONVERT_STAGE = "stage";

    /**
     * REQUEST_CLUE 请求域中的clue线索信息
     */
    public static final String REQUEST_CLUE = "clue";

    /**
     * REQUEST_CLUE_REMARK 请求域中的线索备注信息
     */
    public static final String REQUEST_CLUE_REMARK = "clueRemarks";

}
