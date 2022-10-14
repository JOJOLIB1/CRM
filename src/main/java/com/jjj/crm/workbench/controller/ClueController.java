package com.jjj.crm.workbench.controller;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.commons.pojo.ReturnMsg;
import com.jjj.crm.commons.util.DateUtils;
import com.jjj.crm.commons.util.UUIDUtils;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.settings.service.DicValueService;
import com.jjj.crm.settings.service.UserService;
import com.jjj.crm.workbench.pojo.Clue;
import com.jjj.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @className: com.jjj.crm.workbench.controller.ClueController
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-14 0:24
 */
@Controller
public class ClueController {
    @Autowired
    ReturnMsg returnMsg;
    @Autowired
    DicValueService dicValueService;
    @Autowired
    UserService userService;
    @Autowired
    ClueService clueService;
    /**
     * 跳转线索页面
     * @return
     */
    @RequestMapping("/workbench/clue/toIndex.do")
    public ModelAndView toIndex() {
        // 获取user相关的信息
        List<User> users = userService.queryAllUsers();
        ModelAndView mv = new ModelAndView();
        // 获取对应数据字典相关的信息
        Map<String, Object> dicMap = null;
        for (String dic : Constant.DIC_CLUE_CREATE_LIST) {
            mv.addObject(dic, dicValueService.queryDicValueForCLue(dic));
        }
        mv.addObject(Constant.REQUEST_ALL_USERS, users);
        mv.setViewName("workbench/clue/index");
        return mv;
    }

    @PostMapping("/workbench/clue/saveClue.do")
    @ResponseBody
    public Object saveClue(Clue clue, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        // 进一步封装
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateBy(user.getId());
        clue.setCreateTime(DateUtils.formatDateTime());
        // 调用业务层
        try {
            int affectRows = clueService.saveCreateClue(clue);
            if (affectRows == 0) {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            }else {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            return returnMsg;
        }
        return returnMsg;
    }
}
