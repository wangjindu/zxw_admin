package com.zxw.admin.basic.letter.controller;

import com.zxw.admin.basic.letter.service.LetterService;
import com.zxw.admin.common.CommonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.letter.controller
 * @date 2020/6/14 13:46
 */
@RestController
@RequestMapping(value = "/letter")
public class LetterController {

    @Resource
    private LetterService letterService;

    @DeleteMapping(value = "/deleteLetterById/{letterId}")
    public CommonResult deleteLetterById(@PathVariable(value = "letterId") String letterId){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return letterService.deleteLetterById(letterId);
    }

    @GetMapping(value = "/getAllLetter/{pageNum}")
    public CommonResult getAllLetter(@PathVariable(value = "pageNum") Integer pageNum){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return letterService.getAllLetter(pageNum);
    }

}
