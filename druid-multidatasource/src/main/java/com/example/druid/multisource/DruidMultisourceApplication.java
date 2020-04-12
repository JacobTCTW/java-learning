package com.example.druid.multisource;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication
//exclude = PageHelperAutoConfiguration.class 去掉spirngboot自动配置分页插件
@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class DruidMultisourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DruidMultisourceApplication.class, args);
    }
}
