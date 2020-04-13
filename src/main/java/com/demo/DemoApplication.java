package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@MapperScan(basePackages="com.demo.dao")
@EnableScheduling
@ServletComponentScan //在springBoot启动时会扫描@WebServlet，并将该类实例化  用于生产验证码图片的
public class DemoApplication {
    //@EnableScheduling 开启定时任务
    //@MapperScan 添加扫描注解
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
