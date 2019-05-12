package cn.chainof.sunup.service.module;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class BannerModule {
    private Integer rank;
    private String title;
    private String imgUrl;
}
