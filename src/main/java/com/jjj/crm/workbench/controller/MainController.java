package com.jjj.crm.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: com.jjj.crm.workbench.controller.MainController
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-02 8:50
 */
@Controller
public class MainController {
    /**
     *
     * @return 返回工作台主页面
     */
    @RequestMapping("/workbench/main/toIndex.do")
    public String toIndex() {
        return "workbench/main/index";
    }
}
