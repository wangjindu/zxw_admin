package com.zxw.admin.system.user.service;

import com.zxw.admin.common.CommonResult;
import com.zxw.admin.system.user.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.system.user.service
 * @date 2020/6/12 11:31
 */
public interface UserService {

    public CommonResult login(UserEntity userEntity);

    public CommonResult logout();

    public CommonResult getAllUser(Integer pageNum);

    public UserEntity getUserByName(String userName);

    public CommonResult getUserById(String userId);

    public CommonResult postUser(HttpServletRequest request);
}
