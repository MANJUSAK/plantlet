package com.goodsoft.plantlet.service.lmpl;

import com.goodsoft.plantlet.domain.dao.FileDao;
import com.goodsoft.plantlet.domain.dao.SeedlingDao;
import com.goodsoft.plantlet.domain.entity.file.FileData;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.service.FileService;
import com.goodsoft.plantlet.service.SeedlingService;
import com.goodsoft.plantlet.util.ExcelUtil;
import com.goodsoft.plantlet.util.UUIDUtil;
import com.goodsoft.plantlet.util.result.Result;
import com.goodsoft.plantlet.util.result.SeedlingParm;
import com.goodsoft.plantlet.util.result.Status;
import com.goodsoft.plantlet.util.result.StatusEnum;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
    //实例化excel工具类
    private ExcelUtil excelUtil = ExcelUtil.getInstance();

    /**
     * 苗木数据查询业务逻辑处理方法
     *
     * @param msg 条件查询参数
     * @param <T> 泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T querySeedlingService(SeedlingParm msg) {
        //初始化msg.getNum() start
        int page = msg.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        msg.setNum(page);
        //初始化msg.getNum() start
        List<SeedlingOffer> data = null;
        try {
            data = this.dao.querySeedlingDao(msg);
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
        if (data.size() > 0) {
            return (T) new Result(0, data);
        }
        return (T) new Status(StatusEnum.NO_DATA.getCODE(), StatusEnum.NO_DATA.getEXPLAIN());
    }

    /**
     * 苗木管理数据录入业务处理方法
     *
     * @param files 录入数据文件
     * @param msg   录入数据
     * @return 录入结果
     * @throws Exception
     */
    @Transactional
    @Override
    public Status addSeedlingService(MultipartFile[] files, SeedlingOffer msg) {
        //设置文件编号
        String uuid = this.uuid.getUUID().toString();
        //文件上传
        int arg = this.fileService.fileUploadService(files, "excel", uuid);
        switch (arg) {
            case 604:
                msg.setId(this.uuid.getUUID().toString());
                try {
                    this.dao.addSeedlingOneDao(msg);
                    return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
                } catch (Exception e) {
                    System.out.println(e.toString());
                    this.logger.error(e);
                    return new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
                }
            case 603:
                return new Status(StatusEnum.FILE_FORMAT.getCODE(), StatusEnum.FILE_FORMAT.getEXPLAIN());
            case 601:
                return new Status(StatusEnum.FILE_SIZE.getCODE(), StatusEnum.FILE_SIZE.getEXPLAIN());
            case 600:
                return new Status(StatusEnum.FILE_UPLOAD.getCODE(), StatusEnum.FILE_UPLOAD.getEXPLAIN());
            default:
                try {
                    //获取上传文件路径
                    FileData file = this.fileDao.queryFileOneDao(uuid);
                    StringBuilder sb = new StringBuilder(file.getBases());
                    sb.append(file.getPath());
                    //获取上传excel文件数据
                    List<List<Object>> list = this.excelUtil.readExcel(sb.toString(), uuid);
                    if (list == null) {
                        return new Status(StatusEnum.NO_EXCEL_DATA.getCODE(), StatusEnum.NO_EXCEL_DATA.getEXPLAIN());
                    }
                    List<SeedlingOffer> sdData = sdData = new ArrayList<SeedlingOffer>();
                    for (int i = 0, len = list.size(); i < len; ++i) {
                        List<Object> data = list.get(i);
                        int d = data.size();
                        SeedlingOffer sd = new SeedlingOffer();
                        for (int j = 0; j < d; ++j) {
                            if ("".equals(data.get(j))) {
                                continue;
                            }
                            switch (j) {
                                case 0:
                                    sd.setId((String) data.get(j));
                                    break;
                                case 1:
                                    sd.setProvince((String) data.get(j));
                                    break;
                                case 2:
                                    sd.setCompany((String) data.get(j));
                                    break;
                                case 3:
                                    sd.setTel(Long.parseLong((String) data.get(j)));
                                    break;
                                case 4:
                                    sd.setSeedlingName((String) data.get(j));
                                    break;
                                case 5:
                                    sd.setSpecification((String) data.get(j));
                                    break;
                                case 6:
                                    sd.setUnit((String) data.get(j));
                                    break;
                                case 7:
                                    sd.setNum(Integer.parseInt((String) data.get(j)));
                                    break;
                                case 8:
                                    sd.setPrice(Integer.parseInt((String) data.get(j)));
                                    break;
                                case 9:
                                    sd.setComment((String) data.get(j));
                                    break;
                                default:
                                    break;
                            }
                        }
                        sdData.add(sd);
                    }
                    int len = sdData.size();
                    for (int i = 0; i < len; ++i) {
                        if (0 == sdData.get(i).getPrice() || null == sdData.get(i).getId()
                                || sdData.get(i).getNum() == 0 || sdData.get(i).getSeedlingName() == null
                                || sdData.get(i).getCompany() == null || sdData.get(i).getUnit() == null) {
                            sdData.remove(i);
                            --i;
                            len = sdData.size();
                        }
                    }
                    this.dao.addSeedlingDao(sdData);
                    sdData.clear();
                    return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
                } catch (Exception e) {
                    System.out.println(e.toString());
                    this.logger.error(e);
                    return new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
                }
        }

    }
}
