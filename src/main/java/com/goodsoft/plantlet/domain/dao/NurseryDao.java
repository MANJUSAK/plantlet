package com.goodsoft.plantlet.domain.dao;

import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import com.goodsoft.plantlet.domain.entity.param.NurseryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * function 苗圃管理dao层接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@Repository
public interface NurseryDao {
    //省内苗圃数据主页展示dao方法
    public List<Nursery> queryIndexNurseryDao(NurseryParam param) throws Exception;

    //省内苗圃数据查询dao方法
    public List<Nursery> queryNurseryDao(NurseryParam param) throws Exception;

    //省内苗圃所有数据查询dao方法
    public List<Nursery> queryNurseryAllDao(NurseryParam param) throws Exception;

    //省内苗圃多数据添加dao方法
    public void addNurseryDao(List msg) throws Exception;

    //省内苗圃单条数据添加dao方法
    public void addNurseryOneDao(Nursery msg) throws Exception;

    //省外苗圃数据主页展示dao方法
    public List<NurseryOut> queryIndexNurseryOutDao(NurseryParam param) throws Exception;

    //省外苗圃数据查询dao方法
    public List<NurseryOut> queryNurseryOutDao(NurseryParam param) throws Exception;

    //省外苗圃所有数据查询dao方法
    public List<NurseryOut> queryNurseryOutAllDao(NurseryParam param) throws Exception;

    //省外苗圃多数据添加dao方法
    public void addNurseryOutDao(List<NurseryOut> msg) throws Exception;

    //省外苗圃单条数据dao方法
    public void addNurseryOutOneDao(NurseryOut msg) throws Exception;

    //省内苗圃多数据更新dao方法
    public void updateNurseryDao(List<Nursery> msg) throws Exception;

    //省外苗圃多数据更新dao方法
    public void updateNurseryOutDao(List<NurseryOut> msg) throws Exception;
}
