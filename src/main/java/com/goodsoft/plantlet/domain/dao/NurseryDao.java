package com.goodsoft.plantlet.domain.dao;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.Nursery;
import com.goodsoft.plantlet.util.result.NurseryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * function 苗圃管理dao层接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@Repository
public interface NurseryDao {

    //苗圃管理数据查询dao方法
    public List<Nursery> queryNurseryDao(NurseryParam msg) throws Exception;

    //苗圃管理多数据添加dao方法
    public void addNurseryDao(List<Nursery> msg) throws Exception;

    //苗圃管理单条数据添加dao方法
    public void addNurseryOneDao(Nursery msg) throws Exception;
}
