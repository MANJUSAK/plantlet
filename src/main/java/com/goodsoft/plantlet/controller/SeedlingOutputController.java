package com.goodsoft.plantlet.controller;

import com.goodsoft.plantlet.domain.entity.param.SeedlingStatisticsParam;
import com.goodsoft.plantlet.service.SeedlingOutputService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * function 苗木数据导出访问入口类
 * Created by ASUS on 2017/10/9.
 * version v1.0
 */
@RestController
@RequestMapping("/plant")
public class SeedlingOutputController {
    @Resource
    private SeedlingOutputService service;

    /**
     * 造价信息数据导出接口
     *
     * @param request 请求
     * @param year    年份
     * @param month   月份
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/output/offer/seedling.action.do")
    public Object outputSeedlingOfferController(HttpServletRequest request, int year, int month) {
        return this.service.outputSeedlingOfferService(request, year, month);
    }

    /**
     * 造价统计信息数据导出接口
     *
     * @param request 请求
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/output/offer/statistics/seedling.action.do")
    public Object outputSeedlingOfferController(HttpServletRequest request, String year) {
        return this.service.outputSeedlingOfferService(request, year);
    }

    /**
     * 苗木信息统计数据导出接口
     *
     * @param request 请求
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/output/seedling/statistics/seedling.action.do")
    public Object outputSeedlingStatisticsController(HttpServletRequest request, SeedlingStatisticsParam param) {
        return this.service.outputSeedlingStatisticsService(request, param);
    }

    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/output/supply/seedling.action.do")
    public Object outputSupplyController(HttpServletRequest request, String stp) {
        return this.service.outputSeedlingSupplyService(request, stp);
    }
}
