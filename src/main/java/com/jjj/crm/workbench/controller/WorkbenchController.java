package com.jjj.crm.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: com.jjj.crm.workbench.controller.WorkbenchController
 * @description:
 * @author: 江骏杰
 * @create: 2022-09-30 21:02
 */

@Controller
public class WorkbenchController {

    @RequestMapping("/workbench/toIndex")
    public String toIndex() {
        return "workbench/index";
    }



}
