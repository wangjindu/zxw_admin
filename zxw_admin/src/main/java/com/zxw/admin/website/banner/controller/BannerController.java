package com.zxw.admin.website.banner.controller;

import com.zxw.admin.common.CommonResult;
import com.zxw.admin.website.banner.service.BannerService;
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
import java.util.List;

@RestController
@RequestMapping(value = "/banner")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @PostMapping(value = "/postBanner")
    public CommonResult postBanner(HttpServletRequest request,
                                   @RequestParam("file") MultipartFile file){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return bannerService.postBanner(request, file);
    }

    @PostMapping(value = "/putBannerById")
    public CommonResult putBannerById(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        MultipartFile file = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart){
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            file = multipartRequest.getFile("file");
            }
        return bannerService.putBannerById(request, file);
    }

    @PutMapping(value = "/putBannerFlagById/{bannerId}/{bannerFlag}")
    public CommonResult putBannerFlagById(@PathVariable("bannerId") String bannerId,@PathVariable("bannerFlag") String bannerFlag){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return bannerService.putBannerFlagById(bannerId,bannerFlag);
    }

    @GetMapping(value = "/getAllPhBanner")
    public CommonResult getAllPhBanner() throws IOException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return bannerService.getAllPhBanner();
    }

    @GetMapping(value = "/getAllPcBanner")
    public CommonResult getAllPcBanner() throws IOException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return bannerService.getAllPcBanner();
    }

    @GetMapping(value = "/getAllBanner/{pageNum}")
    public CommonResult getAllBanner(@PathVariable(value = "pageNum") Integer pageNum) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return bannerService.getAllBanner(pageNum);
    }

    @DeleteMapping(value = "/deleteBannerById/{bannerId}")
    public CommonResult deleteBannerById(@PathVariable("bannerId") String bannerId){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return bannerService.deleteBannerById(bannerId);
    }

}
