package com.goodsoft.plantlet.domain.dao;

import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import com.goodsoft.plantlet.util.result.NurseryParam;
import com.goodsoft.plantlet.util.result.NurseryOutParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * function 苗圃管理dao层接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@Repository
public interface NurseryDao {

    //省内苗圃数据查询dao方法
    public List<Nursery> queryNurseryDao(NurseryParam msg) throws Exception;

    //省内苗圃多数据添加dao方法
    public void addNurseryDao(List<Nursery> msg) throws Exception;

    //省内苗圃单条数据添加dao方法
    public void addNurseryOneDao(Nursery msg) throws Exception;

    //省外苗圃数据查询dao方法
    public List<NurseryOut> queryNurseryOutDao(NurseryOutParam msg) throws Exception;

    //省外苗圃多数据添加dao方法
    public void addNurseryOutDao(List<NurseryOut> msg) throws Exception;

    //省外苗圃单条数据dao方法
    public void addNurseryOutOneDao(NurseryOut msg) throws Exception;
}
