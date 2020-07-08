package com.zxw.admin.system.user.mapper;

import com.zxw.admin.system.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.system.user.mapper
 * @date 2020/6/12 11:27
 */
@Mapper
public interface Usermapper {

    public List<UserEntity> getAllUser();

    public UserEntity getUserByName(String userName);

    public UserEntity getUserById(String userId);

    public int postUser(UserEntity userEntity);
}
