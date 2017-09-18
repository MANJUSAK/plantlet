package com.goodsoft.plantlet.controller;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingInfo;
import com.goodsoft.plantlet.service.SeedlingService;
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

    @CrossOrigin(origins = "*", maxAge = 3600, methods = RequestMethod.GET)
    @RequestMapping("/find/seedling.action.do")
    public Object querySeedlingDao(HttpServletRequest request, SeedlingParam msg) {
        return this.service.querySeedlingService(request, msg);
    }

    @RequestMapping(value = "/add/seedling.action.do", method = RequestMethod.POST)
    public Status addSeedlingDao(@RequestParam("files") MultipartFile[] files, SeedlingInfo msg) {
        return this.service.addSeedlingService(files, msg);
    }
}
