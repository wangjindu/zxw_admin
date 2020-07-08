package com.zxw.admin.basic.send.mapper;

import com.zxw.admin.basic.send.entity.SendEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.send.mapper
 * @date 2020/6/12 10:53
 */
@Mapper
public interface SendMapper {

    public int deleteSendById(String sendId);

    public List<SendEntity> getAllSend();
}
