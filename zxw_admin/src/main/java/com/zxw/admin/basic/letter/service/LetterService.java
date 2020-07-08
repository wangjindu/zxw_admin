package com.zxw.admin.basic.letter.service;

import com.zxw.admin.common.CommonResult;


/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.letter.service
 * @date 2020/6/14 13:33
 */
public interface LetterService {

    public CommonResult deleteLetterById(String letterId);

    public CommonResult getAllLetter(Integer pageNum);
}
