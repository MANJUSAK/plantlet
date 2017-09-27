package com.goodsoft.plantlet.controller;

import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import com.goodsoft.plantlet.domain.entity.param.NurseryParam;
import com.goodsoft.plantlet.domain.entity.result.Status;
import com.goodsoft.plantlet.service.NurseryService;
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
     * 省内苗圃查询接口
     *
     * @param request 请求
     * @param msg     条件查询参数
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/province/find/seedling.action.do")
    public Object queryNurseryController(HttpServletRequest request, NurseryParam msg) {
        return this.service.queryNurseryService(request, msg);
    }

    /**
     * 省内苗圃多数据添加接口
     *
     * @param files 数据文件
     * @param msg   录入数据
     * @return 响应结果
     */
    @RequestMapping(value = "/province/add/seedling.action.do", method = RequestMethod.POST)
    public Status addNurseryController(@RequestParam("files") MultipartFile[] files, Nursery msg) {
        return this.service.addNurseryService(files, msg);
    }

    /**
     * 省内苗圃单条数据添加接口
     *
     * @param files 数据文件
     * @param msg   录入数据
     * @return 响应结果
     */
    @RequestMapping(value = "/province/add/cont/seedling.action.do", method = RequestMethod.POST)
    public Status addNurseryOneController(@RequestParam("files") MultipartFile[] files, Nursery msg) {
        return this.service.addNurseryOneService(files, msg);
    }

    /**
     * 省内苗圃导出excel接口
     *
     * @return 响应结果
     */
    @RequestMapping(value = "/province/export/excel/seedling.action.do", method = RequestMethod.POST)
    public Object excelNurseryController(HttpServletRequest request) {
        return this.service.excelNurseryService(request);
    }

    /**
     * 省内苗圃多数据更新接口
     *
     * @param files excel文件
     * @return 响应结果
     */
    @RequestMapping(value = "/province/update/cont/seedling.action.do", method = RequestMethod.POST)
    public Object updateNurseryController(@RequestParam("files") MultipartFile[] files) {
        return this.service.updateNurseryService(files);
    }

    /**
     * 省外苗圃查询接口
     *
     * @param msg 条件查询参数
     * @return 响应结果
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/outside/find/seedling.action.do")
    public Object queryNurseryOutController(NurseryParam msg) {
        return this.service.queryNurseryOutService(msg);
    }

    /**
     * 省外苗圃添加接口
     *
     * @param msg   录入数据
     * @param files 文件数据
     * @return 响应结果
     */
    @RequestMapping(value = "/outside/add/seedling.action.do", method = RequestMethod.POST)
    public Object addNurseryOutController(@RequestParam("files") MultipartFile[] files, NurseryOut msg) {
        return this.service.addNurseryOutService(files, msg);
    }

    /**
     * 省外苗圃导出excel接口
     *
     * @return 响应结果
     */
    @RequestMapping(value = "/outside/export/excel/seedling.action.do", method = RequestMethod.POST)
    public Object excelNurseryOutController(HttpServletRequest request) {
        return this.service.excelNurseryOutService(request);
    }

    /**
     * 省内苗圃多数据更新接口
     *
     * @param files excel文件
     * @return 响应结果
     */
    @RequestMapping(value = "/outside/update/cont/seedling.action.do", method = RequestMethod.POST)
    public Object updateNurseryOutController(@RequestParam("files") MultipartFile[] files) {
        return this.service.updateNurseryOutService(files);
    }
}
