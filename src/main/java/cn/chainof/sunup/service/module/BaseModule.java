package cn.chainof.sunup.service.module;

import cn.chainof.sunup.constant.ModuleConst;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "module")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BannerModule.class, name = ModuleConst.BANNER)
})
public abstract class BaseModule {

}
