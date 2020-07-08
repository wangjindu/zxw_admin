package com.zxw.admin.website.banner.service;

import com.zxw.admin.common.CommonResult;
import com.zxw.admin.website.banner.entity.BannerEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface BannerService {

    public CommonResult postBanner(HttpServletRequest request, MultipartFile file);

    public CommonResult putBannerById(HttpServletRequest request, MultipartFile file);

    public CommonResult putBannerFlagById(String bannerId,String bannerFlag);

    public CommonResult getAllPhBanner() throws IOException;

    public CommonResult getAllPcBanner() throws IOException;

    public CommonResult getAllBanner(Integer pageNum) throws IOException;

    public  CommonResult deleteBannerById(String bannerId);
}
