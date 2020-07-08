package com.zxw.admin.website.news.controller;

import com.alibaba.fastjson.JSON;
import com.zxw.admin.common.CommonResult;
import com.zxw.admin.common.Utils;
import com.zxw.admin.website.news.service.NewsService;
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

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.news.controller
 * @date 2020/6/12 9:51
 */
@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Resource
    private NewsService newsService;
    @Resource
    private Utils utils;

    @PostMapping(value = "/postNews")
    public CommonResult postNews(HttpServletRequest request,
                                 @RequestParam("file") MultipartFile file){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return newsService.postNews(request, file);
    }

    @PostMapping(value = "/putNewsById")
    public CommonResult putNewsById(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        System.out.println(JSON.toJSONString(utils.getParameterMap(request)));
        MultipartFile file = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart){
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            file = multipartRequest.getFile("file");
        }
        return newsService.putNewsById(request, file);
    }

    @DeleteMapping(value = "/deleteNewsById/{newsId}")
    public CommonResult deleteNewsById(@PathVariable("newsId") String newsId){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return newsService.deleteNewsById(newsId);
    }

    @GetMapping(value = "/getAllNews/{pageNum}")
    public CommonResult getAllNews(@PathVariable(value = "pageNum") Integer pageNum) throws IOException, ParseException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return newsService.getAllNews(pageNum);
    }
}
