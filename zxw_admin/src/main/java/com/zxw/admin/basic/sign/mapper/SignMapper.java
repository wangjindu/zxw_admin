package com.zxw.admin.basic.sign.mapper;

import com.zxw.admin.basic.sign.entity.SignEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.sign.mapper
 * @date 2020/6/28 17:30
 */
@Mapper
public interface SignMapper {

    public List<SignEntity> getAllSign();

    public int deleteSignById(String signId);
}
