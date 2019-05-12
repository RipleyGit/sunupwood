package cn.chainof.sunup.service;

import cn.chainof.sunup.controller.dto.data.AdornDesignDTO;

import java.util.List;

public interface AdornDesignService {
    String addAdorn(AdornDesignDTO adornDto);

    String deleteById(String id);

    AdornDesignDTO getAdornInfoById(String id);

    List<AdornDesignDTO> queryAdornList(Integer pageIndex, Integer pageSize);

    String updateAdorn(AdornDesignDTO adornDto);
}
