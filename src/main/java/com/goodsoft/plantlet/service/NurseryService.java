package com.goodsoft.plantlet.service;

import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import com.goodsoft.plantlet.util.result.NurseryParam;
import com.goodsoft.plantlet.util.result.NurseryOutParam;
import com.goodsoft.plantlet.util.result.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * function 苗圃管理业务接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public interface NurseryService {

    //省内苗圃数据查询
    public <T> T queryNurseryService(HttpServletRequest request, NurseryParam msg);

    //省内苗圃数据多数据添加
    public Status addNurseryService(MultipartFile[] files, Nursery msg);

    //省内苗圃数据单条数据添加
    public Status addNurseryOneService(MultipartFile[] files, Nursery msg);

    //省外苗圃数据查询
    public <T> T queryNurseryOutService(NurseryOutParam msg);

    //省外苗圃数据多数据添加
    public Status addNurseryOutService(MultipartFile[] files, NurseryOut msg);
}
