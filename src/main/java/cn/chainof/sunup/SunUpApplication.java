package cn.chainof.sunup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SunUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunUpApplication.class, args);
    }
}
