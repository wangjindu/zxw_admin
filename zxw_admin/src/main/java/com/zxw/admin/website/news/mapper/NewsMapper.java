package com.zxw.admin.website.news.mapper;

import com.zxw.admin.website.news.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.news.mapper
 * @date 2020/6/12 8:47
 */
@Mapper
public interface NewsMapper {

    public int postNews(NewsEntity newsEntity);

    public int putNewsById(NewsEntity newsEntity);

    public int deleteNewsById(String newsId);

    public List<NewsEntity> getAllNews();
}
