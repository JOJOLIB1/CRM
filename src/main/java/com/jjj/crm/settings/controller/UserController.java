package com.jjj.crm.settings.controller;

import com.jjj.crm.commons.pojo.ReturnMsg;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: com.jjj.crm.settings.controller.UserController
 * @description:
 * @author: 江骏杰
 * @create: 2022-09-29 8:46
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ReturnMsg returnMsg;

    /**
     *
     * @return 视图名称,返回登录页面
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {
        return "settings/qx/user/login";
    }

    /**
     *
     * @param loginAct 账号
     * @param loginPwd 密码
     * @param request 请求域
     * @return object可以增强通用性
     */
    @RequestMapping("/settings/qx/user/doLogin.do")
    @ResponseBody
    public Object doLogin(String loginAct, String loginPwd, HttpServletRequest request) {
        // 1.调用业务层代码,获取查询到的实体类对象
        User user = userService.queryByLoginActAndPwd(loginAct, loginPwd);
        if (user == null) {
            // 2.1 账号或密码错误
            returnMsg.setCode("0");
            returnMsg.setMsg("账号或密码错误");
        }else {
            // 2.2 有对应的账号密码,仍需排除其他条件
            // 获取对应的年份
            String expireTime = user.getExpireTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String curTime = format.format(new Date());
            // 通过比较字符串来判断时间
            if (curTime.compareTo(expireTime) > 0) {
                // 已过期
                returnMsg.setCode("0");
                returnMsg.setMsg("账号已过有效期,请联系管理员");
            }else if ("0".equals(user.getLockState())) {
                // 已被锁定,1表示未被锁定
                returnMsg.setCode("0");
                returnMsg.setMsg("账号已被锁定,请联系管理员");
            }else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                // ip被锁定,允许ips在数据库中存储
                returnMsg.setCode("0");
                returnMsg.setMsg("IP受限,请到有效地区");
            }else {
                // 登录成功
                returnMsg.setCode("1");
            }
        }
        return returnMsg;
    }
}
