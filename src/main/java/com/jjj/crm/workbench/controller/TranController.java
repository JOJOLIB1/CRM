package com.jjj.crm.workbench.controller;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.commons.constants.ConstantConvert;
import com.jjj.crm.commons.constants.ConstantTranCreate;
import com.jjj.crm.commons.pojo.ReturnMsg;
import com.jjj.crm.settings.service.DicValueService;
import com.jjj.crm.settings.service.UserService;
import com.jjj.crm.workbench.mapper.TranHistoryMapper;
import com.jjj.crm.workbench.pojo.Tran;
import com.jjj.crm.workbench.service.CustomerService;
import com.jjj.crm.workbench.service.TranHistoryService;
import com.jjj.crm.workbench.service.TranRemarkService;
import com.jjj.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @className: com.jjj.crm.workbench.controller.TranController
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-18 21:36
 */
@Controller
public class TranController {
    @Autowired
    DicValueService dicValueService;
    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;
    @Autowired
    TranService tranService;
    @Autowired
    TranRemarkService tranRemarkService;
    @Autowired
    TranHistoryService tranHistoryService;
    @Autowired
    ReturnMsg returnMsg;

    /**
     * 跳转到交易主页面
     * @return mv对象
     */
    @RequestMapping("/workbench/transaction/toIndex.do")
    public ModelAndView toIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workbench/transaction/index");
        mv.addObject(Constant.DIC_TRAN_SOURCE, dicValueService.queryDicValueByTypeCode(Constant.DIC_TRAN_SOURCE));
        mv.addObject(Constant.DIC_TRAN_STAGE, dicValueService.queryDicValueByTypeCode(Constant.DIC_TRAN_STAGE));
        mv.addObject(Constant.DIC_TRAN_TRANSACTION_TYPE, dicValueService.queryDicValueByTypeCode(Constant.DIC_TRAN_TRANSACTION_TYPE));
        return mv;
    }

    /**
     * 跳转到保存主页面
     * @return
     */
    @RequestMapping("/workbench/transaction/toSave.do")
    public ModelAndView toSave() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workbench/transaction/save");
        mv.addObject(Constant.REQUEST_ALL_USERS, userService.queryAllUsers());
        mv.addObject(Constant.DIC_TRAN_STAGE, dicValueService.queryDicValueByTypeCode(Constant.DIC_TRAN_STAGE));
        mv.addObject(Constant.DIC_TRAN_SOURCE, dicValueService.queryDicValueByTypeCode(Constant.DIC_TRAN_SOURCE));
        mv.addObject(Constant.DIC_TRAN_TRANSACTION_TYPE, dicValueService.queryDicValueByTypeCode(Constant.DIC_TRAN_TRANSACTION_TYPE));
        return mv;
    }

    /**
     * 获取可能性
     * @return json
     */
    @ResponseBody
    @RequestMapping("/workbench/transaction/getPossibility.do")
    public Object getPossibility(String stageName) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("possibility.properties");
        assert is != null;
        InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        Properties properties = new Properties();
        properties.load(reader);
        return properties.getProperty(stageName);
    }

    /**
     * 实现对客户的自动补全
     */
    @RequestMapping("/workbench/transaction/autoFillCustomer.do")
    @ResponseBody
    public Object autoFillCustomer(String name) {
        return customerService.queryCustomerByName(name);
    }

    /**
     * 保存交易
     */
    @RequestMapping("/workbench/transaction/doSaveTran.do")
    @ResponseBody
    public Object doSaveTran(Tran tran, String customerName, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put(ConstantTranCreate.TRAN_OBJECT, tran);
        map.put(Constant.SESSION_USER, session.getAttribute(Constant.SESSION_USER));
        map.put(ConstantTranCreate.CUSTOMER_NAME, customerName);
        try {
            tranService.doSaveTran(map);
            returnMsg.setCode(Constant.RETURN_MSG_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnMsg.setCode(Constant.RETURN_MSG_CODE_FAIL);
            returnMsg.setMsg(Constant.DEFAULT_FAILURE_MESSAGE);
            return returnMsg;
        }
        return returnMsg;
    }

    @RequestMapping("/workbench/transaction/toDetail.do")
    public ModelAndView toDetail(String tranId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workbench/transaction/detail");
        mv.addObject(ConstantTranCreate.TRAN_OBJECT, tranService.queryTranById(tranId));
        mv.addObject(ConstantTranCreate.TRAN_HISTORIES, tranHistoryService.queryTranHistoryByTranId(tranId));
        mv.addObject(ConstantTranCreate.TRAN_REMARKS, tranRemarkService.queryTranRemarkByTranId(tranId));
        mv.addObject(ConstantTranCreate.TRAN_STAGES, dicValueService.queryDicValueByTypeCode("stage"));
        return mv;
    }


}
