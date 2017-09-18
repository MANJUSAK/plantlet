package com.goodsoft.plantlet.controller;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.Nursery;
import com.goodsoft.plantlet.service.NurseryService;
import com.goodsoft.plantlet.util.result.NurseryParam;
import com.goodsoft.plantlet.util.result.Status;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * function 苗圃管理访问入口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@RestController
@RequestMapping("/nursery")
public class NurseryController {

    @Resource
    private NurseryService service;

    /**
     * 苗圃管理查询接口
     *
     * @param request 请求
     * @param msg     条件查询参数
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/seedling.action.do")
    public Object queryNurseryController(HttpServletRequest request, NurseryParam msg) {
        return this.service.queryNurseryService(request, msg);
    }

    /**
     * 苗圃管理多数据添加接口
     *
     * @param files 数据文件
     * @param msg   录入数据
     * @return 响应结果
     */
    @RequestMapping(value = "/add/seedling.action.do", method = RequestMethod.POST)
    public Status addNurseryController(@RequestParam("files") MultipartFile[] files, Nursery msg) {
        return this.service.addNurseryService(files, msg);
    }

    /**
     * 苗圃管理单条数据添加接口
     *
     * @param files 数据文件
     * @param msg   录入数据
     * @return 响应结果
     */
    @RequestMapping(value = "/add/cont/seedling.action.do", method = RequestMethod.POST)
    public Status addNurseryOneController(@RequestParam("files") MultipartFile[] files, Nursery msg) {
        return this.service.addNurseryOneService(files, msg);
    }
}
