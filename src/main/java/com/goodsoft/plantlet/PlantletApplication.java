package com.goodsoft.plantlet;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * function 系统启动程序入口
 * Created by 严彬荣 on 2017/8/4.
 * version v1.0
 */
@ComponentScan(basePackages = "com.goodsoft.plantlet")
@ServletComponentScan(basePackages = "com.goodsoft.plantlet.config")
@SpringBootApplication
public class PlantletApplication {
    //系统日志管理
    private Logger logger = Logger.getLogger(PlantletApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PlantletApplication.class, args);
    }
}
