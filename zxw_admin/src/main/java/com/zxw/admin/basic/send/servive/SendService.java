package com.zxw.admin.basic.send.servive;

import com.zxw.admin.common.CommonResult;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.send.servive
 * @date 2020/6/12 15:03
 */
public interface SendService {

    public CommonResult deleteSendById(String sendId);

    public CommonResult getAllSend(Integer pageNum);
}
