package com.jjj.crm.workbench.controller;

import com.jjj.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: com.jjj.crm.workbench.controller.ChartController
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-21 18:16
 */
@Controller
public class ChartController {

    @Autowired
    TranService tranService;

    @RequestMapping("/workbench/chart/transaction/toTranChartIndex.do")
    public String toTranChartIndex() {
        return "workbench/chart/transaction/index";
    }

    @RequestMapping("/workbench/chart/transaction/getTranChart.do")
    @ResponseBody
    public Object getTranChart() {
        return tranService.queryCountOfTranGroupByStage();
    }

}
