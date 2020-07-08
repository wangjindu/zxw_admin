package com.zxw.admin.website.zsjz.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.admin.common.CommonResult;
import com.zxw.admin.common.DateUtil;
import com.zxw.admin.common.Utils;
import com.zxw.admin.website.zsjz.entity.ZsjzEntity;
import com.zxw.admin.website.zsjz.mapper.ZsjzMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.zsjz.service
 * @date 2020/6/11 16:21
 */
@Service
@Transactional
public class ZsjzServiceImpl implements ZsjzService {

    @Resource
    private ZsjzMapper zsjzMapper;
    @Resource
    private Utils utils;

    private static final String uploadDir = "D:\\test";

    @Override
    synchronized public CommonResult postZsjz(List<MultipartFile> files, String zsjzEntity) throws Exception {
        Map reqmap = JSONObject.parseObject(zsjzEntity);
        String zsjzIcona = null;
        String zsjzIconb = null;
        String zsjzIconc = null;
        String zsjzIcond = null;
        String filename = null;
        System.out.println("共有"+files.size()+"图片传入");
        int length = files.size();
        for (int i = 0;i < length;i++){
            try {
                filename = utils.upload(files.get(i),uploadDir,files.get(i).getOriginalFilename());
                if (!filename.isEmpty()){
                    switch (i){
                        case 0:
                            zsjzIcona ="/" +filename;
                            break;
                        case 1:
                            zsjzIconb ="/" +filename;
                            break;
                        case 2:
                            zsjzIconc ="/" +filename;
                            break;
                        case 3:
                            zsjzIcond ="/" +filename;
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new CommonResult(400, "图片上传失败");
            }
        }
        if ((null == zsjzIcona)||(null == zsjzIcona)||(null == zsjzIcona)||(null == zsjzIcona)){
            return new CommonResult(400,"请上传4张图片");
        }
        String zsjzId = utils.getUUID();
        String zsjzTitle = (String) reqmap.get("zsjzTitle");
        String zsjzSummary = (String) reqmap.get("zsjzSummary");
        String zsjzContent = (String) reqmap.get("zsjzContent");
        String zsjzClass = (String) reqmap.get("zsjzClass");
        String zsjzJob = (String) reqmap.get("zsjzJob");
        System.out.println("========="+reqmap.get("endTime"));
        String endTime = (String) reqmap.get("endTime");
        String zsjzUrl = (String) reqmap.get("zsjzUrl");
        String zsjzName = (String) reqmap.get("zsjzName");
        //Integer zsjzTime = (reqmap.get("zsjzTime").hashCode()-48);
        Integer zsjzTime = 3;
        String zsjzA = (String) reqmap.get("zsjzA");
        String zsjzB = (String) reqmap.get("zsjzB");
        String zsjzC = (String) reqmap.get("zsjzC");
        Integer searchKey = 1;
        Date addTime = new Date();
        long enCount = 0;
        if(null == reqmap.get("zsjzType")){
            return new CommonResult(400, "传入数据有误");
        }
        int zsjzType = reqmap.get("zsjzType").hashCode();
        System.out.println("图片地址:"+zsjzIcona);
        ZsjzEntity zsjzentity = new ZsjzEntity(zsjzId,zsjzTitle,zsjzIcona,zsjzIconb,zsjzIconc,zsjzIcond,zsjzSummary,zsjzContent,zsjzClass,zsjzJob,endTime,zsjzUrl,zsjzName,zsjzTime,zsjzA,zsjzB,zsjzC,addTime,enCount,zsjzType,searchKey);
        int result = 0;
        try {
            result = zsjzMapper.postZsjz(zsjzentity);
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
    synchronized public CommonResult putZsjzById(List<MultipartFile> files, String zsjzEntity) throws ParseException {
        Map reqmap = JSONObject.parseObject(zsjzEntity);
        String zsjzIcona = null;
        String zsjzIconb = null;
        String zsjzIconc = null;
        String zsjzIcond = null;
        String filename = null;
        int length = files.size();
        for (int i = 0;i < length;i++){
            try {
                filename = utils.upload(files.get(i),uploadDir,files.get(i).getOriginalFilename());
                if (!filename.isEmpty()){
                    switch (i){
                        case 0:
                            zsjzIcona ="/" +filename;
                            break;
                        case 1:
                            zsjzIconb ="/" +filename;
                            break;
                        case 2:
                            zsjzIconc ="/" +filename;
                            break;
                        case 3:
                            zsjzIcond ="/" +filename;
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new CommonResult(400, "图片上传失败");
            }
        }
        String zsjzId = (String) reqmap.get("zsjzId");
        String zsjzTitle = (String) reqmap.get("zsjzTitle");
        String zsjzSummary = (String) reqmap.get("zsjzSummary");
        String zsjzContent = (String) reqmap.get("zsjzContent");
        String zsjzClass = (String) reqmap.get("zsjzClass");
        String zsjzJob = (String) reqmap.get("zsjzJob");
        String endTime = (String) reqmap.get("endTime");
        String zsjzUrl = (String) reqmap.get("zsjzUrl");
        String zsjzName = (String) reqmap.get("zsjzName");
        Integer zsjzTime = (reqmap.get("zsjzTime").hashCode()-48);
        String zsjzA = (String) reqmap.get("zsjzA");
        String zsjzB = (String) reqmap.get("zsjzB");
        String zsjzC = (String) reqmap.get("zsjzC");
        Integer searchKey = 1;
        long enCount = 0;
        if ((null != reqmap.get("enCount"))&&(reqmap.get("enCount").equals("0"))){
            enCount = (reqmap.get("enCount").hashCode()-48);
        }
        if(null == reqmap.get("zsjzType")){
            return new CommonResult(400, "传入数据有误");
        }
        int zsjzType = reqmap.get("zsjzType").hashCode();
        Date addTime = new Date();
        ZsjzEntity zsjzentity = new ZsjzEntity(zsjzId,zsjzTitle,zsjzIcona,zsjzIconb,zsjzIconc,zsjzIcond,zsjzSummary,zsjzContent,zsjzClass,zsjzJob,endTime,zsjzUrl,zsjzName,zsjzTime,zsjzA,zsjzB,zsjzC,addTime,enCount,zsjzType,searchKey);
        int result = 0;
        try {
            result = zsjzMapper.putZsjzById(zsjzentity);
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
    synchronized public CommonResult deleteZsjzById(String zsjzId) {
        if(null == zsjzId) {
            return new CommonResult(400, "删除失败，传入id为空");
        }
        int result = 0;
        try {
            result = zsjzMapper.deleteZsjzById(zsjzId);
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
    synchronized public CommonResult getAllZsjz(Integer pageNum) throws IOException {
        PageHelper.startPage(pageNum,6);
        List<ZsjzEntity> list = null;
        try {
            list = zsjzMapper.getAllZsjz();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"查询失败");
        }
        if (null != list) {
                for(int i = 0; i < list.size(); i++){
                    list.get(i).setImga("http://192.168.1.114/test/"+list.get(i).getZsjzIcona());
                    list.get(i).setImgb("http://192.168.1.114/test/"+list.get(i).getZsjzIconb());
                    list.get(i).setImgc("http://192.168.1.114/test/"+list.get(i).getZsjzIconc());
                    list.get(i).setImgd("http://192.168.1.114/test/"+list.get(i).getZsjzIcond());
                }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int j = 0; j < list.size(); j++){
                list.get(j).setAddtime(sdf .format(list.get(j).getAddTime()));
            }
            PageInfo<ZsjzEntity> pageInfo = new PageInfo<ZsjzEntity>(list);
            return new CommonResult(200,"查到数据", pageInfo);
        }else {
            return new CommonResult(400,"未查到数据");
        }
    }
}
