package com.jjj.crm.settings.controller;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.commons.pojo.ReturnMsg;
import com.jjj.crm.commons.util.DateUtils;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
     * 登录页面
     * @param loginAct cookie信息,存放用户名
     * @param loginPwd cookie信息,存放密码
     * @return 返回mv对象
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public ModelAndView toLogin(@CookieValue(value = "loginAct", defaultValue = Constant.DEFAULT_VALUE) String loginAct, @CookieValue(value = "loginPwd", defaultValue = Constant.DEFAULT_VALUE) String loginPwd) {
        ModelAndView mv = new ModelAndView();
        // 设置视图名称
        mv.setViewName("settings/qx/user/login");
        // 往请求域放用户名和密码,为了记住密码功能
        mv.addObject(Constant.LOGIN_ACT, loginAct);
        mv.addObject(Constant.LOGIN_PWD, loginPwd);
        return mv;
    }

    /**
     * 登录
     * @param loginAct 用户名
     * @param loginPwd 密码
     * @param isRemPwd 是否记住密码
     * @param request 请求对象
     * @param session 会话对象
     * @param response 响应对象
     * @return 返回一个json格式字符串,给ajax处理
     */
    @RequestMapping("/settings/qx/user/doLogin.do")
    @ResponseBody
    public Object doLogin(String loginAct, String loginPwd, boolean isRemPwd, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        // 1.调用业务层代码,获取查询到的实体类对象
        User user = userService.queryByLoginActAndPwd(loginAct, loginPwd);
        if (user == null) {
            // 2.1 账号或密码错误
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg("账号或密码错误");
        }else {
            // 2.2 有对应的账号密码,仍需排除其他条件
            // 获取对应的年份
            String expireTime = user.getExpireTime();
            String curTime = DateUtils.formatDateTime();
            // 通过比较字符串来判断时间
            if (curTime.compareTo(expireTime) > 0) {
                // 已过期
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg("账号已过有效期,请联系管理员");
            }else if ("0".equals(user.getLockState())) {
                // 已被锁定,1表示未被锁定
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg("账号已被锁定,请联系管理员");
            }else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                // ip被锁定,允许ips在数据库中存储
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg("IP受限,请到有效地区");
            }else {
                // 登录成功
                returnMsg.setCode(Constant.RETURN_MSG_CODE_SUCCESS);
                // 往会话域对象塞用户信息,以便于后面展示
                session.setAttribute(Constant.SESSION_USER, user);
                // 如果需要记住密码,应该响应对应的cookie
                if (isRemPwd) {
                    // 创建对应的Cookie对象
                    Cookie loginActCookie = new Cookie(Constant.LOGIN_ACT, loginAct);
                    Cookie loginPwdCookie = new Cookie(Constant.LOGIN_PWD, loginPwd);
                    // 设置最大生命周期为10天,过期自动销毁
                    loginActCookie.setMaxAge(864000);
                    loginPwdCookie.setMaxAge(864000);
                    // 不设置路径,路径默认是user及其一下
                    // 响应Cookie
                    response.addCookie(loginActCookie);
                    response.addCookie(loginPwdCookie);
                }else {
                    // 为了安全,不论之前有没有对应的cookie信息,都应该干掉
                    Cookie loginActCookie = new Cookie(Constant.LOGIN_ACT, Constant.DEFAULT_VALUE);
                    Cookie loginPwdCookie = new Cookie(Constant.LOGIN_PWD, Constant.DEFAULT_VALUE);
                    // 设置最大生命周期为0,干掉cookie
                    loginActCookie.setMaxAge(0);
                    loginPwdCookie.setMaxAge(0);
                    // 响应cookie
                    response.addCookie(loginActCookie);
                    response.addCookie(loginPwdCookie);
                }
            }
        }
        return returnMsg;
    }
}
