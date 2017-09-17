package com.goodsoft.plantlet;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * function 外部服务器启动程序
 * Created by 严彬荣 on 2017/8/4.
 * version v1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PlantletApplication.class);
    }

}
