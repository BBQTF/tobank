package com.bbq.tobank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liutf
 * @date 2020-04-20
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.bbq.tobank.mapper")
public class ToBankApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToBankApplication.class, args);
    }
}
