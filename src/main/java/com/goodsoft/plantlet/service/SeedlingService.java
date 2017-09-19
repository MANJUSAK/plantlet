package com.goodsoft.plantlet.service;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingInfo;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.util.result.SeedlingOfferParam;
import com.goodsoft.plantlet.util.result.SeedlingParam;
import com.goodsoft.plantlet.util.result.SeedlingStatisticsParam;
import com.goodsoft.plantlet.util.result.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * function 苗木管理业务接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public interface SeedlingService {
    //苗木信息数据查询
    public <T> T querySeedlingService(HttpServletRequest request, SeedlingParam msg);

    //苗木造价数据查询
    public <T> T querySeedlingService(SeedlingOfferParam msg);

    //苗木统计数据查询
    public <T> T querySeedlingStatisticsService(SeedlingStatisticsParam msg);

    //苗木统计数据详情查询dao方法
    public <T> T querySeedlingStatisticsDetailService(SeedlingStatisticsParam msg);

    //查询苗木统计所有苗木名称
    public <T> T querySeedlingAllNameService();

    //苗木信息数据添加
    public Status addSeedlingService(MultipartFile[] files, SeedlingInfo msg);

    //苗木造价数据添加
    public Status addSeedlingService(MultipartFile[] files, SeedlingOffer msg);

}
