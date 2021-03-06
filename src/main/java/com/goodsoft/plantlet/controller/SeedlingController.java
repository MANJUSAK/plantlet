package com.goodsoft.plantlet.controller;

import com.goodsoft.plantlet.domain.entity.param.SeedlingOfferParam;
import com.goodsoft.plantlet.domain.entity.param.SeedlingStatisticsParam;
import com.goodsoft.plantlet.domain.entity.param.SupplyParam;
import com.goodsoft.plantlet.domain.entity.result.Status;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingStatistics;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SupplyInfo;
import com.goodsoft.plantlet.service.SeedlingService;
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
     * 苗木信息数据查询接口
     *
     * @param msg 查询条件
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/info/seedling.action.do")
    public Object querySeedlingInfoController(SeedlingStatisticsParam msg) {
        return this.service.querySeedlingService(msg);
    }

    /**
     * 供需信息发布数据查询接口
     *
     * @param request 请求
     * @param msg     查询条件
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/supply/seedling.action.do")
    public Object querySeedlingController(HttpServletRequest request, SupplyParam msg) {
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
    public Object querySeedlingController(SeedlingOfferParam msg) {
        return this.service.querySeedlingService(msg);
    }

    /**
     * 苗木造价统计年月日数据查询接口
     *
     * @param msg 查询条件
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/offer/statistics/seedling.action.do")
    public Object querySeedlingOfferController(SeedlingOfferParam msg) {
        return this.service.querySeedlingOfferStatisticsService(msg);
    }

    /**
     * 苗木统计数据查询接口
     *
     * @param param 查询条件
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/statistics/seedling.action.do")
    public Object querySeedlingController(SeedlingStatisticsParam param) {
        return this.service.querySeedlingStatisticsService(param);
    }

    /**
     * 苗木统计数据详情查询接口
     *
     * @param param 查询条件
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/detail/statistics/seedling.action.do")
    public Object querySeedlingDetailController(SeedlingStatisticsParam param) {
        return this.service.querySeedlingStatisticsDetailService(param);
    }

    /**
     * 苗木统计数据查询苗木名称接口
     *
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/all/sd_name/seedling.action.do")
    public Object querySeedlingController() {
        return this.service.querySeedlingAllNameService();
    }

    /**
     * 苗木信息添加接口
     *
     * @param msg 添加数据
     * @return 响应结果
     */
    @RequestMapping(value = "/add/info/seedling.action.do", method = RequestMethod.POST)
    public Status addSeedlingController(SeedlingStatistics msg) {
        return this.service.addSeedlingService(msg);
    }

    /**
     * 供需信息发布接口
     *
     * @param files 发布文件
     * @param msg   发布数据
     * @return 响应结果
     */
    @RequestMapping(value = "/add/supply/seedling.action.do", method = RequestMethod.POST)
    public Status addSeedlingController(@RequestParam("files") MultipartFile[] files, SupplyInfo msg) {
        return this.service.addSeedlingService(files, msg);
    }

    /**
     * 苗木造价数据添加接口
     *
     * @param msg 添加数据
     * @return 响应结果
     */
    @RequestMapping(value = "/add/offer/seedling.action.do", method = RequestMethod.POST)
    public Status addSeedlingController(@RequestParam("files") MultipartFile[] files, SeedlingOffer msg) {
        return this.service.addSeedlingService(files, msg);
    }
}
