package cn.chainof.sunup.service.module;

import lombok.Data;

import java.util.List;

@Data
public class ProductDesignModule {
    private String name;
    private String egName;
    private String ItemName;
    private String lordImg;
    private Integer isLarge;
    private List<String> imgUrls;

}
