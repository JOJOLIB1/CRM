package com.jjj.crm.workbench.controller;

import com.jjj.crm.commons.constants.Constant;
import com.jjj.crm.settings.pojo.DicValue;
import com.jjj.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping("/workbench/transaction/toIndex.do")
    public ModelAndView toIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workbench/transaction/index");
        mv.addObject(Constant.DIC_TRAN_SOURCE, dicValueService.queryDicValueForCLue(Constant.DIC_TRAN_SOURCE));
        mv.addObject(Constant.DIC_TRAN_STAGE, dicValueService.queryDicValueForCLue(Constant.DIC_TRAN_STAGE));
        mv.addObject(Constant.DIC_TRAN_TRANSACTION_TYPE, dicValueService.queryDicValueForCLue(Constant.DIC_TRAN_TRANSACTION_TYPE));
        return mv;
    }
}
