package com.jjj.crm.workbench.controller;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @className: com.jjj.crm.workbench.controller.ActivityController
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-02 9:11
 */
@Controller
public class ActivityController {

    @Autowired
    UserService userService;

    /**
     * @return 市场活动视图转发
     */
    @RequestMapping("/workbench/activity/toIndex")
    public ModelAndView toIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workbench/activity/index");
        List<User> users = userService.queryAllUsers();
        mv.addObject(Constant.REQUEST_ALL_USERS, users);
        return mv;
    }

}
