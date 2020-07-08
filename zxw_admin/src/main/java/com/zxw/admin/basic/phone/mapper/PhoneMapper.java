package com.zxw.admin.basic.phone.mapper;

import com.zxw.admin.basic.phone.entity.PhoneEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.phone.mapper
 * @date 2020/6/16 16:12
 */
@Mapper
public interface PhoneMapper {

    public int deletePhoneById(String phoneId);

    public List<PhoneEntity> getAllPhone();
}
