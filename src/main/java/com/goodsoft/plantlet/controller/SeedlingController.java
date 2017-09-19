package com.goodsoft.plantlet.controller;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingInfo;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.service.SeedlingService;
import com.goodsoft.plantlet.util.result.SeedlingOfferParam;
import com.goodsoft.plantlet.util.result.SeedlingParam;
import com.goodsoft.plantlet.util.result.Status;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * function 苗木管理访问入口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@RestController
@RequestMapping("/plant")
public class SeedlingController {
    @Resource
    private SeedlingService service;

    /**
     * 苗木信息发布数据查询接口
     *
     * @param request 请求
     * @param msg     查询条件
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/seedling.action.do")
    public Object querySeedlingDao(HttpServletRequest request, SeedlingParam msg) {
        return this.service.querySeedlingService(request, msg);
    }

    /**
     * 苗木造价数据查询接口
     *
     * @param msg 查询条件
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/offer/seedling.action.do")
    public Object querySeedlingDao(SeedlingOfferParam msg) {
        return this.service.querySeedlingService(msg);
    }

    /**
     * 苗木统计数据查询接口
     *
     * @param keyWord 查询条件
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/statistics/seedling.action.do")
    public Object querySeedlingDao(String keyWord) {
        return this.service.querySeedlingStatisticsDao(keyWord);
    }

    /**
     * 苗木统计数据查询接口
     *
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/all/sd_name/seedling.action.do")
    public Object querySeedlingDao() {
        return this.service.querySeedlingAllNameService();
    }

    /**
     * 苗木信息发布接口
     *
     * @param files 发布文件
     * @param msg   发布数据
     * @return 响应结果
     */
    @RequestMapping(value = "/add/seedling.action.do", method = RequestMethod.POST)
    public Status addSeedlingDao(@RequestParam("files") MultipartFile[] files, SeedlingInfo msg) {
        return this.service.addSeedlingService(files, msg);
    }

    /**
     * 苗木造价数据添加接口
     *
     * @param msg 添加数据
     * @return 响应结果
     */
    @RequestMapping(value = "/add/offer/seedling.action.do", method = RequestMethod.POST)
    public Status addSeedlingDao(SeedlingOffer msg) {
        return this.service.addSeedlingService(msg);
    }
}
