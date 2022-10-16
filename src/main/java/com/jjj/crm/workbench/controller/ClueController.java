package com.jjj.crm.workbench.controller;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.commons.pojo.ReturnMsg;
import com.jjj.crm.commons.util.DateUtils;
import com.jjj.crm.commons.util.UUIDUtils;
import com.jjj.crm.settings.pojo.DicValue;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.settings.service.DicValueService;
import com.jjj.crm.settings.service.UserService;
import com.jjj.crm.workbench.pojo.Activity;
import com.jjj.crm.workbench.pojo.Clue;
import com.jjj.crm.workbench.pojo.ClueActivityRelation;
import com.jjj.crm.workbench.pojo.ClueRemark;
import com.jjj.crm.workbench.service.ActivityService;
import com.jjj.crm.workbench.service.ClueActivityRelationService;
import com.jjj.crm.workbench.service.ClueRemarkService;
import com.jjj.crm.workbench.service.ClueService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    private ReturnMsg returnMsg;
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClueService clueService;
    @Autowired
    private ClueRemarkService clueRemarkService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ClueActivityRelationService clueActivityRelationService;
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

    /**
     * 保存线索
     * @param clue
     * @param session
     * @return
     */
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

    /**
     * 跳转详情页面
     * @param id
     * @return
     */
    @RequestMapping("/workbench/clue/toDetail.do")
    public ModelAndView toDetail(String id) {
        Clue clue = clueService.queryClueById(id);
        List<ClueRemark> clueRemarks = clueRemarkService.queryClueRemarkByClueId(id);
        List<Activity> activities = activityService.queryActivityByClueId(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject(Constant.REQUEST_CLUE, clue);
        mv.addObject(Constant.REQUEST_CLUE_REMARK, clueRemarks);
        mv.addObject(Constant.REQUEST_ACTIVITY, activities);
        mv.setViewName("workbench/clue/detail");
        return mv;
    }

    /**
     * 查询未被联系的市场活动
     * @param name 模糊查询参数
     * @param clueId clueId
     * @return
     */
    @RequestMapping("/workbench/clue/queryUnboundedActivities.do")
    @ResponseBody
    public Object queryUnboundedActivities(String name, String clueId) {
        return activityService.queryUnboundedActivityByName(clueId, name);
    }

    /**
     * 保存联系
     * @return
     */
    @PostMapping("/workbench/clue/saveCreateRelation.do")
    @ResponseBody
    public Object saveCreateRelation(@RequestParam("activityId") String[] activitiesId, String clueId) {
//        进一步封装
        ClueActivityRelation clueActivityRelation = null;
        List<ClueActivityRelation> clueActivityRelations = new ArrayList<>();
        for (String activityId : activitiesId) {
            clueActivityRelation = new ClueActivityRelation();
            clueActivityRelation.setId(UUIDUtils.getUUID());
            clueActivityRelation.setActivityId(activityId);
            clueActivityRelation.setClueId(clueId);
            clueActivityRelations.add(clueActivityRelation);
        }
        try {
            int affectRows = clueActivityRelationService.saveCreateCLueActivityRelation(clueActivityRelations);
            if (affectRows == 0) {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            }else {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_SUCCESS);
                // 查询联系新联系的市场活动
                List<Activity> activities = activityService.queryActivitiesByIds(activitiesId);
                returnMsg.setOthMsg(activities);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setOthMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            return returnMsg;
        }
        return returnMsg;
    }

    /**
     * 删除联系
     * @param clueActivityRelation 待删除的条件封装的对象
     * @return
     */
    @DeleteMapping("/workbench/clue/deleteRelation.do")
    @ResponseBody
    public Object deleteRelation(ClueActivityRelation clueActivityRelation) {
        try {
            int affectRows = clueActivityRelationService.deleteClueActivityRelation(clueActivityRelation);
            if (affectRows == 0) {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            }else {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            return returnMsg;
        }
        return returnMsg;
    }

    /**
     * 跳转转换页面
     * @param id 线索id
     * @return modelAndView
     */
    @RequestMapping("/workbench/clue/toConvert.do")
    public ModelAndView toConvert(String id) {
        Clue clue = clueService.queryClueById(id);
        List<DicValue> stages = dicValueService.queryDicValueForCLue(Constant.DIC_CLUE_CONVERT_STAGE);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workbench/clue/convert");
        mv.addObject(Constant.REQUEST_CLUE, clue);
        mv.addObject(Constant.DIC_CLUE_CONVERT_STAGE, stages);
        return mv;
    }

    /**
     * 查找转换线索的市场活动数据源
     * @param name 模糊查询条件
     * @param clueId 线索id
     * @return json
     */
    @RequestMapping("/workbench/clue/showActivityDataSource.do")
    @ResponseBody
    public Object showActivityDataSource(String name, String clueId) {
        return activityService.queryBoundedActivityByName(clueId, name);
    }

}
