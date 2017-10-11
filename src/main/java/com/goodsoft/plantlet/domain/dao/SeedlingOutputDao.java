package com.goodsoft.plantlet.domain.dao;

import com.goodsoft.plantlet.domain.entity.param.SeedlingStatisticsParam;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOfferStatistics;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingStatistics;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SupplyInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * function 苗木数据导出dao层接口类
 * Created by 严彬荣 on 2017/10/9.
 * version v1.0
 */
@Repository
public interface SeedlingOutputDao {
    //造价数据导出
    public List<SeedlingOffer> querySeedlingOfferDao(@Param("year") int year, @Param("month") int month) throws Exception;

    //造价统计数据导出
    public List<SeedlingOfferStatistics> querySeedlingOfferStatisticsDao(@Param("year") int year) throws Exception;

    //苗木信息统计数据导出
    public List<SeedlingStatistics> querySeedlingStatisticsDao(SeedlingStatisticsParam param) throws Exception;

    //供需数据导出
    public List<SupplyInfo> querySeedlingSupplyDao(@Param("stp") String stp) throws Exception;
}
