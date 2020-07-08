package com.zxw.admin.website.banner.mapper;

import com.zxw.admin.website.banner.entity.BannerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {

    public int postBanner(BannerEntity bannerEntity);

    public int putBannerById(BannerEntity bannerEntity);

    public int putBannerFlagById(BannerEntity bannerEntity);

    public List<BannerEntity> getAllPhBanner();

    public List<BannerEntity> getAllPcBanner();

    public List<BannerEntity> getAllBanner();

    public int deleteBannerById(String bannerId);
}
