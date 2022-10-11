package com.jjj.crm.workbench.controller;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.commons.pojo.ReturnMsg;
import com.jjj.crm.commons.util.DateUtils;
import com.jjj.crm.commons.util.PoiUtils;
import com.jjj.crm.commons.util.UUIDUtils;
import com.jjj.crm.settings.pojo.User;
import com.jjj.crm.settings.service.UserService;
import com.jjj.crm.workbench.pojo.Activity;
import com.jjj.crm.workbench.pojo.ActivityRemark;
import com.jjj.crm.workbench.service.ActivityRemarkService;
import com.jjj.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @Autowired
    ActivityRemarkService activityRemarkService;

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
                returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            }else {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_SUCCESS);
            }
        }catch (Exception e) {
            e.printStackTrace();
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            return returnMsg;
        }
        return returnMsg;
    }

    /**
     * 分页查询
     * @param pageSize 每一页的记录数
     * @param pageNo 页码号
     * @param condition 条件
     * @return pageInfo对象
     */
    @GetMapping("/workbench/activity/query/{pageNo}")
    @ResponseBody
    public Object query(Integer pageSize, @PathVariable("pageNo") Integer pageNo, Activity condition) {
        return activityService.queryActivitiesForPages(pageSize, pageNo, condition);
    }

    /**
     * 批量删除
     * @param ids 待删除的id
     * @return returnMsg
     */
    @DeleteMapping("/workbench/activity/delete")
    @ResponseBody
    public Object delete(@RequestParam("id") String[] ids) {
        int affectRows = activityService.deleteActivities(ids);
        try {
            if (affectRows < ids.length) {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            } else {
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
     * 给待更新的元素查询
     * @param id 待更新的元素id
     * @return 数据
     */
    @GetMapping("/workbench/activity/updateForSelect")
    @ResponseBody
    public Object updateForSelect(String id) {
        return activityService.queryForUpdate(id);
    }

    /**
     * 修改市场活动数据
     * @param activity 修改内容
     * @param session 会话域
     * @return returnMsg
     */
    @PutMapping("/workbench/activity/update")
    @ResponseBody
    public Object update(Activity activity, HttpSession session) {
        // 进一步封装
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        activity.setEditBy(user.getId());
        activity.setEditTime(DateUtils.formatDateTime());

        int affectRows = activityService.updateActivity(activity);
        try {
            if (affectRows == 0) {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            }else {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_SUCCESS);
            }
        }catch (Exception e) {
            e.printStackTrace();
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
        }
        return returnMsg;
    }

    /**
     * 批量导出数据与选择性导出数据
     * @return 响应体
     * @throws IOException io异常
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/exportExcel.do")
    public ResponseEntity<byte[]> exportExcel(@RequestParam(value = "id", required = false) String[] ids) throws IOException {
        // 状态行信息
        HttpStatus status = HttpStatus.OK;
        // 响应头信息
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Content-Disposition", "attachment;filename=activities.xls");
        // 响应体信息
        // 调用业务层
        List<Activity> activities = activityService.queryActivitiesByIds(ids);
        HSSFWorkbook workbook = PoiUtils.getHSSFWorkbook();
        PoiUtils.createActivityHeader(workbook);
        PoiUtils.createActivityDynamicData(activities, workbook);
        // 字节数组输出流,相当于中间层
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 先将对象输出中间层,然后再转换为byte[]
        workbook.write(outputStream);
        // 封装响应体
        return new ResponseEntity<byte[]>(outputStream.toByteArray(), header, status);
    }

    /**
     * 导入excel文件
     * @param excelFile
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/importExcel.do")
    public Object importExcel(MultipartFile excelFile, HttpSession session){
        InputStream is = null;
        try {
            is = excelFile.getInputStream();
            HSSFWorkbook workbook = PoiUtils.getHSSFWorkbook(is);
            User user = (User) session.getAttribute(Constant.SESSION_USER);
            // 获取list集合
            List<Activity> list = PoiUtils.getListFromExcel(workbook, user.getId());
            int affectRows = activityService.insertByList(list);
            returnMsg.setCode(Constant.RETURN_MSG_CODE_SUCCESS);
            returnMsg.setMsg("成功插入" + affectRows + "行");
        } catch (IOException e) {
            e.printStackTrace();
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            return returnMsg;
        }
        return returnMsg;
    }

    /**
     * 详情页面
     * @param id 市场活动id
     * @return
     */
    @RequestMapping("/workbench/activity/toDetail")
    public ModelAndView toDetail(String id) {
        // 查询市场活动相关的数据
        Activity activity = activityService.queryDetailOfActivity(id);
        // 查询该市场活动的留言信息
        List<ActivityRemark> activityRemarks = activityRemarkService.queryRemarkFromActivity(id);
        // 塞到域对象里面
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workbench/activity/detail");
        mv.addObject(Constant.REQUEST_ACTIVITY, activity);
        mv.addObject(Constant.REQUEST_ACTIVITY_REMARK, activityRemarks);
        return mv;
    }

    /**
     * 添加留言
     * @param remark 留言实体类对象
     * @param session 会话域
     * @return json
     */
    @PostMapping("/workbench/activity/addActivityRemark")
    @ResponseBody
    public Object addActivityRemark(HttpSession session, ActivityRemark remark) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        // 进一步封装
        remark.setCreateTime(DateUtils.formatDateTime());
        remark.setEditFlag(Constant.DEFAULT_EDIT_FLAG);
        remark.setCreateBy(user.getId());
        remark.setId(UUIDUtils.getUUID());
        try {
            int affectRows = activityRemarkService.addRemarkForActivity(remark);
            if (affectRows == 0) {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            }else {
                returnMsg.setMsg(Constant.RETURN_MSG_CODE_SUCCESS);
                // 成功了把名字重新封装
                remark.setCreateBy(user.getName());
                returnMsg.setOthMsg(remark);
            }
        }catch (Exception e) {
            e.printStackTrace();
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            return returnMsg;
        }
        return returnMsg;
    }

    @DeleteMapping("/workbench/activity/deleteActivityRemark")
    @ResponseBody
    public Object deleteActivityRemark(String id) {
        try {
            int affectRows = activityRemarkService.deleteRemarkById(id);
            if (affectRows == 0) {
                returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
                returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            }else {
                returnMsg.setMsg(Constant.RETURN_MSG_CODE_SUCCESS);
            }
        }catch (Exception e) {
            e.printStackTrace();
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            return returnMsg;
        }
        return returnMsg;
    }
}
