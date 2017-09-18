package com.goodsoft.plantlet.domain.dao;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingInfo;
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

    //苗木信息数据查询dao方法
    public List<SeedlingInfo> querySeedlingDao(SeedlingParam msg) throws Exception;

    //苗木信息数据添加dao方法
    public void addSeedlingDao(SeedlingInfo msg) throws Exception;

}
