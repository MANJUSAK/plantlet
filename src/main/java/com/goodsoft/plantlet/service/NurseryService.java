package com.goodsoft.plantlet.service;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.Nursery;
import com.goodsoft.plantlet.util.result.NurseryParam;
import com.goodsoft.plantlet.util.result.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * function 苗圃管理业务接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public interface NurseryService {

    //苗圃管理数据查询
    public <T> T queryNurseryService(HttpServletRequest request, NurseryParam msg);

    //苗圃管理多数据添加
    public Status addNurseryService(MultipartFile[] files, Nursery msg);

    //苗圃管理单条数据添加
    public Status addNurseryOneService(MultipartFile[] files, Nursery msg);
}
