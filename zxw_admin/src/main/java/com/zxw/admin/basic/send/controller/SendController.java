package com.zxw.admin.basic.send.controller;

import com.zxw.admin.basic.send.servive.SendService;
import com.zxw.admin.common.CommonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.send.controller
 * @date 2020/6/12 15:12
 */
@RestController
@RequestMapping(value = "/send")
public class SendController {

    @Resource
    private SendService sendService;

    @DeleteMapping(value = "/deleteSendById/{sendId}")
    public CommonResult deleteSendById(@PathVariable(value = "sendId") String sendId){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return sendService.deleteSendById(sendId);
    }

    @GetMapping(value = "/getAllSend/{pageNum}")
    public CommonResult getAllSend(@PathVariable(value = "pageNum") Integer pageNum){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return sendService.getAllSend(pageNum);
    }
}
