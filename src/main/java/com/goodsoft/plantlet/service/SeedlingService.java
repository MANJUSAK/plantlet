package com.goodsoft.plantlet.service;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.util.result.SeedlingParm;
import com.goodsoft.plantlet.util.result.Status;
import org.springframework.web.multipart.MultipartFile;

/**
 * function 苗木管理业务接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public interface SeedlingService {
    //查询苗木数据Service方法
    public <T> T querySeedlingService(SeedlingParm msg);

    //添加苗木数据Service方法
    public Status addSeedlingService(MultipartFile[] files, SeedlingOffer msg);
}
