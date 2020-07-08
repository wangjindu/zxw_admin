package com.zxw.admin.basic.sign.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.admin.basic.sign.entity.SignEntity;
import com.zxw.admin.basic.sign.mapper.SignMapper;
import com.zxw.admin.common.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.sign.service
 * @date 2020/6/28 17:49
 */
@Service
@Transactional
public class SignServiceImpl implements SignService {

    @Resource
    private SignMapper signMapper;

    @Override
    synchronized public CommonResult getAllSign(Integer pageNum) {
        PageHelper.startPage(pageNum,8);
        List<SignEntity> list = null;
        try {
            list = signMapper.getAllSign();
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
            PageInfo<SignEntity> pageInfo = new PageInfo<>(list);
            return new CommonResult(200,"查到数据", pageInfo);
        }
    }

    @Override
    synchronized public CommonResult deleteSignById(String signId) {
        if (null == signId){
            return new CommonResult(400,"传入数据有误");
        }
        int result = 0;
        try {
            result = signMapper.deleteSignById(signId);
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
}
