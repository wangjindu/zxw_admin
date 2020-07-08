package com.zxw.admin.basic.letter.mapper;

import com.zxw.admin.basic.letter.entity.LetterEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.letter.mapper
 * @date 2020/6/13 11:38
 */
@Mapper
public interface LetterMapper {

    public int deleteLetterById(String letterId);

    public List<LetterEntity> getAllLetter();
}
