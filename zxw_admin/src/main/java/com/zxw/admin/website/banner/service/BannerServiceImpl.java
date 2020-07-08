package com.zxw.admin.website.banner.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.admin.common.CommonResult;
import com.zxw.admin.common.Utils;
import com.zxw.admin.website.banner.entity.BannerEntity;
import com.zxw.admin.website.banner.mapper.BannerMapper;
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

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private Utils utils;

    private final String uploadDir = "D:\\test";

    @Override
    synchronized public CommonResult postBanner(HttpServletRequest request, MultipartFile file) {
        if ((null == request)||(null == file)){
            return new CommonResult(400, "传入数据为空");
        }
        Map<String,String> reqmap = utils.requestToMap(request);
        String bannerId = utils.getUUID();
        String bannerTitle = reqmap.get("bannerTitle");
        String bannerUrl = reqmap.get("bannerUrl");
        String bannerFlag = "true";
        Date addTime = new Date();
        int bannerType = (reqmap.get("bannerType").hashCode()-48);
        if (1 != bannerType && 2!= bannerType ){
            return new CommonResult(400, "添加失败，传入数据有误");
        }
        String bannerIcon = null;
        try {
            String filename = utils.upload(file, uploadDir, file.getOriginalFilename());
            if (!filename.isEmpty()){
                bannerIcon ="/" +filename;
            }else {
                return new CommonResult(400, "图片上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400, "图片上传失败");
        }
        BannerEntity bannerEntity = new BannerEntity(bannerId, bannerTitle, bannerIcon, bannerUrl, bannerFlag, addTime, bannerType);
        int result = 0;
        try {
            result = bannerMapper.postBanner(bannerEntity);
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
    synchronized public CommonResult putBannerById(HttpServletRequest request, MultipartFile file) {
        if (null == request){
            return new CommonResult(400, "传入数据为空");
        }
        Map<String,String> reqmap = utils.requestToMap(request);
        System.out.println("====="+JSON.toJSONString(reqmap));
        String bannerIcon = reqmap.get("bannerIcon");
        if (null != file){
            File oldfile = new File(bannerIcon);
            oldfile.delete();
            String filename;
            try {
                filename = Utils.upload(file, uploadDir, file.getOriginalFilename());
                if (!filename.isEmpty()) {
                    bannerIcon = "/" +filename;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new CommonResult(400, "更新图片失败");
            }
        }
        String bannerId = reqmap.get("bannerId");
        String bannerTitle = reqmap.get("bannerTitle");
        String bannerUrl = reqmap.get("bannerUrl");
        Date addTime = new Date();
        int bannerType = (reqmap.get("bannerType").hashCode()-48);
        BannerEntity bannerEntity = new BannerEntity(bannerId, bannerTitle, bannerIcon, bannerUrl,addTime, bannerType);
        int result = 0;
        try {
            result = bannerMapper.putBannerById(bannerEntity);
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
    synchronized public CommonResult putBannerFlagById(String bannerId,String bannerFlag) {

        if ((null == bannerId)&&(null == bannerFlag)){
            return new CommonResult(400, "传入数据有误");
        }
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setBannerId(bannerId);
        bannerEntity.setBannerFlag(bannerFlag);
        int result = 0;
        try {
            result = bannerMapper.putBannerFlagById(bannerEntity);
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
    synchronized public CommonResult getAllPhBanner() throws IOException {
        List<BannerEntity> list = null;
        try {
            list = bannerMapper.getAllPhBanner();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"查询失败");
        }
        if (null != list) {
            for(int i = 0; i < list.size(); i++){
                list.get(i).setImg("http://192.168.1.114/test/"+list.get(i).getBannerIcon());
            }
            return new CommonResult(200,"查到数据", list);
        }else {
            return new CommonResult(400,"未查到数据");
        }
    }

    @Override
    synchronized public CommonResult getAllPcBanner() throws IOException {
        List<BannerEntity> list = null;
        try {
            list = bannerMapper.getAllPcBanner();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"查询失败");
        }
        if (null != list) {
            for(int i = 0; i < list.size(); i++){
                list.get(i).setImg("http://192.168.1.114/test/"+list.get(i).getBannerIcon());
            }
            return new CommonResult(200,"查到数据", JSON.toJSONString(list));
        }else {
            return new CommonResult(400,"未查到数据");
        }
    }

    @Override
    synchronized public CommonResult getAllBanner(Integer pageNum) throws IOException {
        PageHelper.startPage(pageNum,6);
        List<BannerEntity> list = null;
        try {
            list = bannerMapper.getAllBanner();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"查询失败");
        }
        if (null != list) {
            for(int i = 0; i < list.size(); i++){
                list.get(i).setImg("http://192.168.1.114/test/"+list.get(i).getBannerIcon());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int j = 0; j < list.size(); j++){
                list.get(j).setAddtime(sdf .format(list.get(j).getAddTime()));
            }
            PageInfo<BannerEntity> pageInfo = new PageInfo<>(list);
            return new CommonResult(200,"查到数据", JSON.toJSONString(pageInfo));
        }else {
            return new CommonResult(400,"未查到数据");
        }
    }

    @Override
    synchronized public CommonResult deleteBannerById(String bannerId) {
        if(null == bannerId) {
            return new CommonResult(400, "删除失败，传入id为空");
        }
        int result = 0;
        try {
            result = bannerMapper.deleteBannerById(bannerId);
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
}
