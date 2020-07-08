package com.zxw.admin.website.news.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.admin.common.CommonResult;
import com.zxw.admin.common.Utils;
import com.zxw.admin.website.news.entity.NewsEntity;
import com.zxw.admin.website.news.mapper.NewsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.news.service
 * @date 2020/6/12 9:23
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;
    @Resource
    private Utils utils;

    private static final String uploadDir = "D:\\test";

    @Override
    synchronized public CommonResult postNews(HttpServletRequest request, MultipartFile file) {
        if ((null == request)||(null == file)){
            return new CommonResult(400, "传入数据为空");
        }
        Map<String,String> reqmap = utils.requestToMap(request);
        String newsId = utils.getUUID();
        String newsTitle = reqmap.get("newsTitle");
        if ((null == reqmap.get("newsScource"))||(null == reqmap.get("newsType"))){
            return new CommonResult(400, "传入数据有误");
        }
        String newsIcon = null;
        try {
            String filename = utils.upload(file, uploadDir, file.getOriginalFilename());
            if (!filename.isEmpty()){
                newsIcon ="/" +filename;
            }else {
                return new CommonResult(400, "图片上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400, "图片上传失败");
        }
        String newsSource = reqmap.get("newsScource");
        int newsType = (reqmap.get("newsType").hashCode()-48);
        String newsSummary = reqmap.get("newsSummary");
        String newsContent  = reqmap.get("newsContent");
        Date addTime = new Date();
        Integer searchKey = 2;
        long readCount = 0;
        NewsEntity newsEntity = new NewsEntity(newsId, newsSource, newsType, newsTitle, newsIcon, newsSummary, newsContent,addTime,readCount,searchKey);
        int result = 0;
        try {
            result = newsMapper.postNews(newsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400, "添加失败");
        }
        if (result > 0){
            return new CommonResult(200, "添加成功");
        }else {
            return new CommonResult(400, "添加失败");
        }
    }

    @Override
    synchronized public CommonResult putNewsById(HttpServletRequest request, MultipartFile file) {
        if (null == request){
            return new CommonResult(400, "传入数据为空");
        }
        Map<String,String> reqmap = utils.requestToMap(request);
        String newsIcon = reqmap.get("newsIcon");
        if (null != file){
            File oldfile = new File(newsIcon);
            oldfile.delete();
            String filename;
            try {
                filename = Utils.upload(file, uploadDir, file.getOriginalFilename());
                if (!filename.isEmpty()) {
                    newsIcon = "/" +filename;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new CommonResult(400, "更新图片失败");
            }
        }
        String newsId = reqmap.get("newsId");
        String newsTitle = reqmap.get("newsTitle");
        if ((null == reqmap.get("newsScource"))||(null == reqmap.get("newsType"))){
            return new CommonResult(400, "传入数据有误");
        }
        String newsSource = reqmap.get("newsScource");
        int newsType = (reqmap.get("newsType").hashCode()-48);
        String newsSummary = reqmap.get("newsSummary");
        String newsContent  = reqmap.get("newsContent");
        Integer searchKey = 2;
        Date addTime = new Date();
        NewsEntity newsEntity = new NewsEntity(newsId, newsSource, newsType, newsTitle, newsIcon, newsSummary, newsContent,addTime,searchKey);
        int result = 0;
        try {
            result = newsMapper.putNewsById(newsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400, "更新失败");
        }
        if (result > 0){
            return new CommonResult(200, "更新成功");
        }else {
            return new CommonResult(400, "更新失败");
        }
    }

    @Override
    synchronized public CommonResult deleteNewsById(String newsId) {
        if(null == newsId) {
            return new CommonResult(400, "删除失败，传入id为空");
        }
        int result = 0;
        try {
            result = newsMapper.deleteNewsById(newsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400, "删除失败");
        }
        if (result > 0){
            return new CommonResult(200, "删除成功");
        }else {
            return new CommonResult(400, "删除失败");
        }
    }

    @Override
    synchronized public CommonResult getAllNews(Integer pageNum) {
        PageHelper.startPage(pageNum,6);
        List<NewsEntity> list = null;
        try {
            list = newsMapper.getAllNews();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"查询失败");
        }
        if (null != list) {
                for(int i = 0; i < list.size(); i++){
                    list.get(i).setImg("http://192.168.1.114/test/"+list.get(i).getNewsIcon());
                }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int j = 0; j < list.size(); j++){
                list.get(j).setAddtime(sdf .format(list.get(j).getAddTime()));
            }
            PageInfo<NewsEntity> pageInfo =new PageInfo<NewsEntity>(list);
            return new CommonResult(200,"查到数据", pageInfo);
        }else {
            return new CommonResult(400,"未查到数据");
        }
    }
}
