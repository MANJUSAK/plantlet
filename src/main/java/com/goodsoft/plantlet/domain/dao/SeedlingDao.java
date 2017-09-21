package com.goodsoft.plantlet.domain.dao;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingStatistics;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SupplyInfo;
import com.goodsoft.plantlet.util.result.SeedlingOfferParam;
import com.goodsoft.plantlet.util.result.SeedlingStatisticsParam;
import com.goodsoft.plantlet.util.result.SupplyParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * function 苗木管理dao层接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@Repository
public interface SeedlingDao {

    //苗木信息数据查询dao方法
    public List<SeedlingStatistics> querySeedlingInfoDao(SeedlingStatisticsParam msg) throws Exception;

    //供需信息数据查询dao方法
    public List<SupplyInfo> querySeedlingSupplyDao(SupplyParam msg) throws Exception;

    //苗木造价数据查询dao方法
    public List<SeedlingOffer> querySeedlingOfferDao(SeedlingOfferParam msg) throws Exception;

    //苗木造价统计年月数据查询dao方法
    public List<SeedlingOffer> querySeedlingOfferStatisticsDetailDao(SeedlingOfferParam msg) throws Exception;

    //苗木统计数据查询dao方法
    public List<SeedlingStatistics> querySeedlingStatisticsDao(SeedlingStatisticsParam msg) throws Exception;

    //苗木统计数据详情查询dao方法
    public List<SeedlingStatistics> querySeedlingStatisticsDetailDao(SeedlingStatisticsParam msg) throws Exception;

    //查询苗木统计所有苗木名称dao方法
    public List<String> querySeedlingAllNameDao() throws Exception;

    //苗木信息数据添加dao方法
    public void addSeedlingInfoDao(SeedlingStatistics msg) throws Exception;

    //供需信息数据添加dao方法
    public void addSeedlingSupplyDao(SupplyInfo msg) throws Exception;

    //苗木造价单条数据添加dao方法
    public void addSeedlingOfferOneDao(SeedlingOffer msg) throws Exception;

    //苗木造价多条数据添加dao方法
    public void addSeedlingOfferDao(List<SeedlingOffer> msg) throws Exception;

}
