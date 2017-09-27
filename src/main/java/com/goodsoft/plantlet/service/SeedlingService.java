package com.goodsoft.plantlet.service;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingStatistics;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SupplyInfo;
import com.goodsoft.plantlet.domain.entity.param.SeedlingOfferParam;
import com.goodsoft.plantlet.domain.entity.param.SeedlingStatisticsParam;
import com.goodsoft.plantlet.domain.entity.result.Status;
import com.goodsoft.plantlet.domain.entity.param.SupplyParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * function 苗木管理业务接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public interface SeedlingService {
    //苗木信息数据查询
    public <T> T querySeedlingService(SeedlingStatisticsParam msg);

    //供需信息数据查询
    public <T> T querySeedlingService(HttpServletRequest request, SupplyParam msg);

    //苗木造价数据查询
    public <T> T querySeedlingService(SeedlingOfferParam msg);

    //苗木造价统计年月份数据查询
    public <T> T querySeedlingOfferStatisticsService(SeedlingOfferParam msg);

    //苗木统计数据查询
    public <T> T querySeedlingStatisticsService(SeedlingStatisticsParam msg);

    //苗木统计数据详情查询dao方法
    public <T> T querySeedlingStatisticsDetailService(SeedlingStatisticsParam msg);

    //查询苗木统计所有苗木名称
    public <T> T querySeedlingAllNameService();

    //苗木信息数据添加
    public Status addSeedlingService(SeedlingStatistics msg);

    //供需信息数据添加
    public Status addSeedlingService(MultipartFile[] files, SupplyInfo msg);

    //苗木造价数据添加
    public Status addSeedlingService(MultipartFile[] files, SeedlingOffer msg);

}
