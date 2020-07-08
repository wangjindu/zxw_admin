package com.zxw.admin.website.zsjz.mapper;

import com.zxw.admin.website.zsjz.entity.ZsjzEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.zsjz.mapper
 * @date 2020/6/11 15:46
 */
@Mapper
public interface ZsjzMapper {

    public int postZsjz(ZsjzEntity zsjzEntity);

    public int putZsjzById(ZsjzEntity zsjzEntity);

    public int deleteZsjzById(String zsjzId);

    public List<ZsjzEntity> getAllZsjz();

}
