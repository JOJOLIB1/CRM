package com.jjj.crm.workbench.controller;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.settings.service.DicValueService;
import com.jjj.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.ResourceBundle;

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
}
