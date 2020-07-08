package com.zxw.admin.website.news.service;

import com.zxw.admin.common.CommonResult;
import com.zxw.admin.website.news.entity.NewsEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.news.service
 * @date 2020/6/12 9:22
 */
public interface NewsService {

    public CommonResult postNews(HttpServletRequest request, MultipartFile file);

    public CommonResult putNewsById(HttpServletRequest request, MultipartFile file);

    public CommonResult deleteNewsById(String newsId);

    public CommonResult getAllNews(Integer pageNum) throws IOException, ParseException;
}
