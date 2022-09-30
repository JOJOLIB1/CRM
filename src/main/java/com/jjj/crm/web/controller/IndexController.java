package com.jjj.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: com.jjj.crm.web.contoller.IndexContrller
 * @description:
 * @author: 江骏杰
 * @create: 2022-09-29 8:47
 */
@Controller
public class IndexController {
    /**
     *
     * @return 视图名称,返回首页
     */
    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

}
