package com.goodsoft.plantlet.controller;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.service.SeedlingService;
import com.goodsoft.plantlet.util.result.SeedlingParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
    public Object querySeedlingController(SeedlingParam msg) {
        return this.service.querySeedlingService(msg);
    }

    @RequestMapping(value = "/add/seedling.action.do", method = RequestMethod.POST)
    public Object addSeedlingController(@RequestParam("files") MultipartFile[] files, SeedlingOffer msg) {
        return this.service.addSeedlingService(files, msg);
    }
}
