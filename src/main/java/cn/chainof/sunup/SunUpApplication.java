package cn.chainof.sunup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("cn.chainof.sunup.mapper")
@SpringBootApplication
public class SunUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunUpApplication.class, args);
    }
}
