package com.goodsoft.plantlet.domain.dao;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingInfo;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingStatistics;
import com.goodsoft.plantlet.util.result.SeedlingOfferParam;
import com.goodsoft.plantlet.util.result.SeedlingParam;
import org.apache.ibatis.annotations.Param;
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
    public List<SeedlingInfo> querySeedlingDao(SeedlingParam msg) throws Exception;

    //苗木造价数据查询dao方法
    public List<SeedlingOffer> querySeedlingOfferDao(SeedlingOfferParam msg) throws Exception;

    //苗木统计数据查询dao方法
    public List<SeedlingStatistics> querySeedlingStatisticsDao(@Param("keyWord") String keyWord) throws Exception;

    //查询苗木统计所有苗木名称dao方法
    public List<String> querySeedlingAllNameDao() throws Exception;

    //苗木信息数据添加dao方法
    public void addSeedlingDao(SeedlingInfo msg) throws Exception;

    //苗木造价数据添加dao方法
    public void addSeedlingOfferDao(SeedlingOffer msg) throws Exception;

}
