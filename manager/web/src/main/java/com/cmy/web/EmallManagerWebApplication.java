package com.cmy.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Lankidd on 2017/2/15.
 */
@MapperScan("com.cmy.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class EmallManagerWebApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmallManagerWebApplication.class, args);
        //context.getBean(UserMapper.class);
    }
}
