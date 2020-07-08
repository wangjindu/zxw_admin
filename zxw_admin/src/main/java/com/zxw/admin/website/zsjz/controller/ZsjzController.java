package com.zxw.admin.website.zsjz.controller;

import com.zxw.admin.common.CommonResult;
import com.zxw.admin.common.Utils;
import com.zxw.admin.website.zsjz.service.ZsjzService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.zsjz.controller
 * @date 2020/6/11 16:45
 */
@RestController
@RequestMapping(value = "/zsjz")
public class ZsjzController {

    @Resource
    private ZsjzService zsjzService;
    @Resource
    private Utils utils;

    @PostMapping(value = "/postZsjz")
    public CommonResult postZsjz(@RequestParam(value = "files") List<MultipartFile> files,
                                 @RequestParam(value = "zsjzEntity") String zsjzEntity) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        System.out.println("====="+zsjzEntity);
        if (null != files){
            return zsjzService.postZsjz(files, zsjzEntity);
        }else {
            return new CommonResult(400,"请上传图片");
        }
    }

    @PutMapping(value = "/putZsjzById")
    public CommonResult putZsjzById(@RequestParam(value = "files") List<MultipartFile> files,
                                 @RequestParam(value = "zsjzEntity") String zsjzEntity) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        System.out.println("====="+zsjzEntity);
        if (null != files){
            return zsjzService.putZsjzById(files, zsjzEntity);
        }else {
            return new CommonResult(400,"请上传图片");
        }
    }

    @DeleteMapping(value = "/deleteZsjzById/{zsjzId}")
    public CommonResult deleteZsjzById(@PathVariable("zsjzId") String zsjzId){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return zsjzService.deleteZsjzById(zsjzId);
    }

    @GetMapping(value = "/getAllZsjz/{pageNum}")
    public CommonResult getAllZsjz(@PathVariable(value = "pageNum") Integer pageNum) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return zsjzService.getAllZsjz(pageNum);
    }
}
