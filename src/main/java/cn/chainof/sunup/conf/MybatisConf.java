package cn.chainof.sunup.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"cn.chainof.sunup.mapper"})
public class MybatisConf {
}
