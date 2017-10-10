package com.goodsoft.plantlet.service;

import javax.servlet.http.HttpServletRequest;

/**
 * function 苗木数据导出业务接口类
 * Created by 严彬荣 on 2017/10/9.
 * version v1.0
 */
public interface SeedlingOutputService {
    //苗木造价数据导出业务方法
    public <T> T outputSeedlingOfferService(HttpServletRequest request, int year, int month);

    //苗木造价统计数据导出业务方法
    public <T> T outputSeedlingOfferService(HttpServletRequest request);

    //苗木信息统计数据导出
    public <T> T outputSeedlingStatisticsService(HttpServletRequest request);

    public <T> T outputSeedlingSupplyService(HttpServletRequest request);
}
