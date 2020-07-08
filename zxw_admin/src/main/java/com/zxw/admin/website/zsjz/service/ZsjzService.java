package com.zxw.admin.website.zsjz.service;

import com.zxw.admin.common.CommonResult;
import com.zxw.admin.website.zsjz.entity.ZsjzEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.zsjz.service
 * @date 2020/6/11 16:20
 */
public interface ZsjzService {

    public CommonResult postZsjz(List<MultipartFile> files, String zsjzEntity) throws Exception;

    public CommonResult putZsjzById(List<MultipartFile> files, String zsjzEntity) throws ParseException;

    public CommonResult deleteZsjzById(String zsjzId);

    public CommonResult getAllZsjz(Integer pageNum) throws IOException;
}
