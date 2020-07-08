package com.zxw.admin.basic.send.servive;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.admin.basic.send.entity.SendEntity;
import com.zxw.admin.basic.send.mapper.SendMapper;
import com.zxw.admin.common.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.send.servive
 * @date 2020/6/12 15:04
 */
@Service
@Transactional
public class SendServiceImpl implements SendService {

    @Resource
    private SendMapper sendMapper;

    @Override
    synchronized public CommonResult deleteSendById(String sendId) {
        if (null == sendId){
            return new CommonResult(400, "删除失败，传入id为空");
        }
        int result = 0;
        try {
            result = sendMapper.deleteSendById(sendId);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400, "删除失败");
        }
        if (result > 0){
            return new CommonResult(200, "删除成功");
        }else {
            return new CommonResult(400, "删除失败");
        }
    }

    @Override
    synchronized public CommonResult getAllSend(Integer pageNum) {
        PageHelper.startPage(pageNum,8);
        List<SendEntity> list = null;
        try {
            list = sendMapper.getAllSend();
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
            PageInfo<SendEntity> pageInfo = new PageInfo<>(list);
            return new CommonResult(200,"查到数据", pageInfo);
        }
    }
}
