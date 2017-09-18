package com.goodsoft.plantlet.service.lmpl;

import com.goodsoft.plantlet.domain.dao.FileDao;
import com.goodsoft.plantlet.domain.dao.SeedlingDao;
import com.goodsoft.plantlet.domain.entity.file.FileData;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingInfo;
import com.goodsoft.plantlet.service.FileService;
import com.goodsoft.plantlet.service.SeedlingService;
import com.goodsoft.plantlet.util.DomainNameUtil;
import com.goodsoft.plantlet.util.UUIDUtil;
import com.goodsoft.plantlet.util.result.Result;
import com.goodsoft.plantlet.util.result.SeedlingParam;
import com.goodsoft.plantlet.util.result.Status;
import com.goodsoft.plantlet.util.result.StatusEnum;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * function 苗木管理业务接口实现类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Service
public class SeedlingServicelmpl implements SeedlingService {
    @Resource
    private SeedlingDao dao;
    @Resource
    private FileService fileService;
    @Resource
    private FileDao fileDao;
    //实例化日志管理工具类
    private Logger logger = Logger.getLogger(SeedlingServicelmpl.class);
    //实例化UUID工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();
    //实例化服务器域名地址工具类
    private DomainNameUtil domainName = DomainNameUtil.getInstance();


    @Override
    public <T> T querySeedlingService(HttpServletRequest request, SeedlingParam msg) {
        //初始化msg.getNum() start
        int page = msg.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        msg.setNum(page);
        //初始化msg.getNum() end
        List<SeedlingInfo> data = null;
        try {
            data = this.dao.querySeedlingDao(msg);
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
        int sd = data.size();
        if (sd > 0) {
            //获取服务器域名地址
            String var = this.domainName.getServerDomainName(request).toString();
            StringBuilder sb = new StringBuilder();
            try {
                for (int i = 0; i < sd; ++i) {
                    //查询数据对应的图片信息
                    List<FileData> path = this.fileDao.queryFileDao(data.get(i).getFileId());
                    int p = path.size();
                    if (p > 0) {
                        //封装域名地址以及图片相对路径
                        List url = new ArrayList();
                        for (int j = 0; j < p; ++j) {
                            sb.append(var);
                            sb.append(path.get(j).getPath());
                            url.add(sb.toString());
                            sb.delete(0, sb.length());
                        }
                        //将图片完整地址封装到数据中
                        data.get(i).setPicture(url);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                this.logger.error(e);
            }
            return (T) new Result(0, data);
        } else {
            return (T) new Status(StatusEnum.NO_DATA.getCODE(), StatusEnum.NO_DATA.getEXPLAIN());
        }
    }

    @Override
    public Status addSeedlingService(MultipartFile[] files, SeedlingInfo msg) {
        //设置文件编号
        String uuid = this.uuid.getUUID().toString();
        //文件上传
        int arg = this.fileService.fileUploadService(files, "seedling", uuid);
        switch (arg) {
            case 604:
                return new Status(StatusEnum.NO_FILE.getCODE(), StatusEnum.NO_FILE.getEXPLAIN());
            case 603:
                return new Status(StatusEnum.FILE_FORMAT.getCODE(), StatusEnum.FILE_FORMAT.getEXPLAIN());
            case 601:
                return new Status(StatusEnum.FILE_SIZE.getCODE(), StatusEnum.FILE_SIZE.getEXPLAIN());
            case 600:
                return new Status(StatusEnum.FILE_UPLOAD.getCODE(), StatusEnum.FILE_UPLOAD.getEXPLAIN());
        }
        msg.setFileId(uuid);
        msg.setId(this.uuid.getUUID().toString());
        try {
            //获取植物规格
            String specStr = msg.getSpec();
            //去掉空格
            String str = specStr.replaceAll(" ", "");
            //获取规格前缀
            String specStr1 = str.substring(1, 2);
            if ("≤".equals(specStr1) || "≥".equals(specStr1) || "<".equals(specStr1) || ">".equals(specStr1)) {
                //含有特殊字符则获取特殊字符
                msg.setSpec(specStr.substring(0, 2));
                double min = Double.parseDouble(specStr.substring(2));
                msg.setSpecMin(min);
            } else {
                //将获取植物规格以“-”进行拆分
                String[] spec = str.split("-");
                //判断是否满足拆分条件
                if (spec.length > 1) {
                    double min = Double.parseDouble(spec[0].substring(1));
                    double max = Double.parseDouble(spec[1]);
                    //获取前缀
                    msg.setSpec(spec[0].substring(0, 1));
                    //获取规格范围
                    msg.setSpecMin(min);
                    msg.setSpecMax(max);
                } else {
                    double min = Double.parseDouble(spec[0].substring(1));
                    msg.setSpecMin(min);
                    msg.setSpec(spec[0].substring(0, 1));
                }
            }
            this.dao.addSeedlingDao(msg);
            return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
    }
}
