package com.goodsoft.plantlet.domain.dao;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.util.result.SeedlingParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * function 苗木管理dao层接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@Repository
public interface SeedlingDao {
    //查询苗木数据dao方法
    public List<SeedlingOffer> querySeedlingDao(SeedlingParam msg) throws Exception;

    //添加苗木数据dao方法
    public void addSeedlingDao(List<SeedlingOffer> msg) throws Exception;

    //添加苗木数据dao方法
    public void addSeedlingOneDao(SeedlingOffer msg) throws Exception;
}
