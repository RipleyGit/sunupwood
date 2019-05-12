package cn.chainof.sunup.service;

import cn.chainof.sunup.controller.dto.data.BannerDTO;

import java.util.List;

public interface ProjectBannerService {
    String addBanner(BannerDTO banner);

    String deleteBannerById(String id);

    BannerDTO getBannerById(String id);

    List<BannerDTO> queryList(Integer pageIndex, Integer pageSize);

    String updateBanner(BannerDTO banner);
}
