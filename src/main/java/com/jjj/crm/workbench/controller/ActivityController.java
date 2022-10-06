package com.jjj.crm.workbench.controller;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.commons.pojo.ReturnMsg;
import com.jjj.crm.commons.util.DateUtils;
import com.jjj.crm.commons.util.UUIDUtils;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.settings.service.UserService;
import com.jjj.crm.workbench.pojo.Activity;
import com.jjj.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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

    @Autowired
    ReturnMsg returnMsg;
    @Autowired
    ActivityService activityService;

    /**
     * @return 市场活动视图转发
     */
    @RequestMapping("/workbench/activity/toIndex")
    public ModelAndView toIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workbench/activity/index");
        // 查询所有的用户
        List<User> users = userService.queryAllUsers();
        // 放在请求域中
        mv.addObject(Constant.REQUEST_ALL_USERS, users);
        return mv;
    }

    /**
     * 创建市场活动控制层
     * @param activity 市场活动对象
     * @param session 会话域
     * @return 响应信息json
     */
    @PostMapping("/workbench/activity/createActivity")
    @ResponseBody
    public Object createActivity(Activity activity, HttpSession session) {
        // 进一步封装创建时间
        activity.setCreateTime(DateUtils.formatDateTime());
        // 获取会话域的user对象,并封装创建者ID,因为name可能重复
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        activity.setCreateBy(user.getId());
        // 设置市场活动id
        activity.setId(UUIDUtils.getUUID());
        // 用户可能导致异常,先进行异常处理
        try{
            // 必须放在里面,异常是这个方法导致的
            // 调用业务层代码,得到影响函数
            int reflectRows = activityService.saveActivity(activity);
            if (reflectRows == 0) {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg("系统繁忙,请稍后!");
            }else {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_SUCCESS);
            }
        }catch (Exception e) {
            e.printStackTrace();
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg("系统繁忙,请稍后!");
            return returnMsg;
        }
        return returnMsg;
    }

    @GetMapping("/workbench/activity/query")
    @ResponseBody
    public Object query(Integer pageSize, Integer pageNo, Activity condition) {
        return activityService.queryActivitiesForPages(pageSize, pageNo, condition);
    }



}
