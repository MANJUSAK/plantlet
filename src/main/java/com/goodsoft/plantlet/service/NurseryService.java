package com.goodsoft.plantlet.service;

import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import com.goodsoft.plantlet.domain.entity.param.NurseryParam;
import com.goodsoft.plantlet.domain.entity.result.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * function 苗圃管理业务接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public interface NurseryService {
    //省内苗圃数据主页展示
    public <T> T queryIndexNurseryService(HttpServletRequest request, NurseryParam msg);

    //省内苗圃数据查询
    public <T> T queryNurseryService(HttpServletRequest request, NurseryParam msg);

    //省内苗圃excel导出
    public <T> T excelNurseryService(HttpServletRequest request);

    //省内苗圃数据多数据添加
    public Status addNurseryService(MultipartFile[] files, Nursery msg);

    //省内苗圃数据单条数据添加
    public Status addNurseryOneService(MultipartFile[] files, Nursery msg);

    //省外苗圃数据主页展示查询
    public <T> T queryIndexNurseryOutService(NurseryParam msg);

    //省外苗圃数据查询
    public <T> T queryNurseryOutService(NurseryParam msg);

    //省外苗圃excel导出
    public <T> T excelNurseryOutService(HttpServletRequest request);

    //省外苗圃数据多数据添加
    public Status addNurseryOutService(MultipartFile[] files, NurseryOut msg);

    //省内苗圃数据多数据更新
    public Status updateNurseryService(MultipartFile[] files);

    //省外苗圃数据多数据更新
    public Status updateNurseryOutService(MultipartFile[] files);
}
