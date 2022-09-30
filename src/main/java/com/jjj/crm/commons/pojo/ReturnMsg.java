package com.jjj.crm.commons.pojo;

import org.springframework.stereotype.Component;

/**
 * @className: com.jjj.crm.commons.pojo.ReturnMesg
 * @description:
 * @author: 江骏杰
 * @create: 2022-09-30 8:33
 */
@Component
public class ReturnMsg {
    // String为了前后端通用性,code是状态码
    private String code;
    // 响应信息
    private String msg;
    // 其他信息,如需要可嵌套进来
    private Object othMsg;

    public ReturnMsg() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getOthMsg() {
        return othMsg;
    }

    public void setOthMsg(Object othMsg) {
        this.othMsg = othMsg;
    }
}
