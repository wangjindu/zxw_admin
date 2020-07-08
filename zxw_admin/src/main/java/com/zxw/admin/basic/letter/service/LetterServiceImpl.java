package com.zxw.admin.basic.letter.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.admin.basic.letter.entity.LetterEntity;
import com.zxw.admin.basic.letter.mapper.LetterMapper;
import com.zxw.admin.common.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.letter.service
 * @date 2020/6/14 13:34
 */
@Service
@Transactional
public class LetterServiceImpl implements LetterService {

    @Resource
    private LetterMapper letterMapper;

    @Override
    synchronized public CommonResult deleteLetterById(String letterId) {
        if (null == letterId){
            return new CommonResult(400,"传入数据有误");
        }
        int result = 0;
        try {
            result = letterMapper.deleteLetterById(letterId);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"删除失败");
        }
        if (result > 0){
            return new CommonResult(200,"删除成功");
        }else {
            return new CommonResult(400,"删除失败");
        }
    }

    @Override
    synchronized public CommonResult getAllLetter(Integer pageNum) {
        PageHelper.startPage(pageNum,8);
        List<LetterEntity> list = null;
        try {
            list = letterMapper.getAllLetter();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"查询失败");
        }
        if (null == list){
            return new CommonResult(400,"未查到数据");
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int j = 0; j < list.size(); j++){
                list.get(j).setAddtime(sdf .format(list.get(j).getAddTime()));
            }
            PageInfo<LetterEntity> pageInfo = new PageInfo<>(list);
            return new CommonResult(200,"查到数据", pageInfo);
        }
    }
}
