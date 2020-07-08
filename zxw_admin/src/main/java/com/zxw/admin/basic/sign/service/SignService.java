package com.zxw.admin.basic.sign.service;

import com.zxw.admin.common.CommonResult;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.sign.service
 * @date 2020/6/28 17:48
 */
public interface SignService {

    public CommonResult getAllSign(Integer pageNum);

    public CommonResult deleteSignById(String signId);
}
