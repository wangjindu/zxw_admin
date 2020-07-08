package com.zxw.admin.basic.phone.service;

import com.zxw.admin.common.CommonResult;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.phone.service
 * @date 2020/6/16 16:19
 */
public interface PhoneService {

    public CommonResult deletePhoneById(String phoneId);

    public CommonResult getAllPhone(Integer pageNum);
}
