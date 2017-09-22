package com.goodsoft.plantlet.config.serviceaop;

import com.goodsoft.plantlet.util.GetIP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * function 系统访问数据信息增强工具类
 * Created by 严彬荣 on 2017/9/22.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Component
@Aspect
public class ServicelmplAop {

    private Logger logger = LoggerFactory.getLogger(ServicelmplAop.class);

    //匹配com.goodsoft.plantlet.service.lmpl包及其子包下的所有类的所有方法
    @Pointcut("execution(* com.goodsoft.plantlet.controller.*..*(..))")
    public void executeService() {
    }

    @After("executeService()")
    public void doAfter(JoinPoint pjp) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String ip = GetIP.getIP(request);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logger.info("{},ip:{} 请求信息：,url:{},method:{},params:{}", date, ip, url, method, queryString);
    }

}
