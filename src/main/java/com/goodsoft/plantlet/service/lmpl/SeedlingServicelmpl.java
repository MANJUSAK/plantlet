package com.goodsoft.plantlet.service.lmpl;

import com.goodsoft.plantlet.domain.dao.FileDao;
import com.goodsoft.plantlet.domain.dao.SeedlingDao;
import com.goodsoft.plantlet.domain.entity.file.FileData;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingInfo;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingStatistics;
import com.goodsoft.plantlet.service.FileService;
import com.goodsoft.plantlet.service.SeedlingService;
import com.goodsoft.plantlet.util.DeleteFileUtil;
import com.goodsoft.plantlet.util.DomainNameUtil;
import com.goodsoft.plantlet.util.ExcelUtil;
import com.goodsoft.plantlet.util.UUIDUtil;
import com.goodsoft.plantlet.util.result.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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
    //实例化服务器域名地址工具类
    private DomainNameUtil domainName = DomainNameUtil.getInstance();
    //实例化文件删除工具类
    private DeleteFileUtil deleteFile = DeleteFileUtil.getInstance();

    /**
     * 苗木数据检索业务处理方法
     *
     * @param request 请求
     * @param msg     检索条件
     * @param <T>     泛型
     * @return 查询结果
     * @throws Exception
     */
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

    /**
     * 苗木造价数据检索业务处理方法
     *
     * @param msg 检索条件
     * @param <T> 泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T querySeedlingService(SeedlingOfferParam msg) {
        //初始化msg.getNum() start
        int page = msg.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        msg.setNum(page);
        //初始化msg.getNum() end
        List<SeedlingOffer> data = null;
        try {
            data = this.dao.querySeedlingOfferDao(msg);
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
        if (data.size() > 0) {
            return (T) new Result(0, data);
        } else {
            return (T) new Status(StatusEnum.NO_DATA.getCODE(), StatusEnum.NO_DATA.getEXPLAIN());
        }
    }

    /**
     * 苗木数据统计检索业务处理方法
     *
     * @param keyWord 检索条件
     * @param <T>     泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T querySeedlingStatisticsService(SeedlingStatisticsParam param) {
        //初始化msg.getNum() start
        int page = param.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        param.setNum(page);
        //初始化msg.getNum() end
        List<SeedlingStatistics> data = null;
        try {
            data = this.dao.querySeedlingStatisticsDao(param);
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
        if (data.size() > 0) {
            return (T) new Result(0, data);
        } else {
            return (T) new Status(StatusEnum.NO_DATA.getCODE(), StatusEnum.NO_DATA.getEXPLAIN());
        }
    }

    /**
     * 苗木造价数据检索详情业务处理方法
     *
     * @param keyWord 检索条件
     * @param <T>     泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T querySeedlingStatisticsDetailService(SeedlingStatisticsParam param) {
        //初始化msg.getNum() start
        int page = param.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        param.setNum(page);
        //初始化msg.getNum() end
        List<SeedlingStatistics> data = null;
        try {
            data = this.dao.querySeedlingStatisticsDetailDao(param);
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
        if (data.size() > 0) {
            return (T) new Result(0, data);
        } else {
            return (T) new Status(StatusEnum.NO_DATA.getCODE(), StatusEnum.NO_DATA.getEXPLAIN());
        }
    }

    /**
     * 查询苗木信息统计数据所有植物名称业务处理方法
     *
     * @param <T> 泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T querySeedlingAllNameService() {
        List<String> data = null;
        try {
            data = this.dao.querySeedlingAllNameDao();
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
        if (data.size() > 0) {
            return (T) new Result(0, data);
        } else {
            return (T) new Status(StatusEnum.NO_DATA.getCODE(), StatusEnum.NO_DATA.getEXPLAIN());
        }
    }

    /**
     * 苗木信息发布业务处理方法
     *
     * @param files   数据文件
     * @param msg发布数据
     * @return 发布结果
     * @throws Exception
     */
    @Override
    @Transactional
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
            if ("≤".equals(specStr1) || "≥".equals(specStr1) || "<".equals(specStr1)
                    || ">".equals(specStr1) || "=".equals(specStr1)) {
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

    /**
     * 苗木造价数据添加业务处理方法
     *
     * @param msg发布数据
     * @return 发布结果
     * @throws Exception
     */
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
                    //获取植物规格
                    String specStr = msg.getSpec();
                    //去掉空格
                    String str = specStr.replaceAll(" ", "");
                    //获取规格前缀
                    String specStr1 = str.substring(1, 2);
                    if ("≤".equals(specStr1) || "≥".equals(specStr1) || "<".equals(specStr1)
                            || ">".equals(specStr1) || "=".equals(specStr1)) {
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
                    this.dao.addSeedlingOfferOneDao(msg);
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
        }
        try {
            //获取上传文件路径
            FileData file = this.fileDao.queryFileOneDao(uuid);
            StringBuilder sb = new StringBuilder(file.getBases());
            sb.append(file.getPath());
            //获取上传excel文件数据
            List<List<Object>> list = this.excelUtil.readExcel(sb.toString(), uuid);
            if (list == null) {
                return new Status(StatusEnum.NO_EXCEL.getCODE(), StatusEnum.NO_EXCEL.getEXPLAIN());
            }
            //实例化数据保存集合类
            List<SeedlingOffer> sdData = sdData = new ArrayList<SeedlingOffer>();
            for (int i = 0, len = list.size(); i < len; ++i) {
                List<Object> data = list.get(i);
                int d = data.size();
                SeedlingOffer sd = new SeedlingOffer();
                for (int j = 0; j < d; ++j) {
                    //判断读取字段是否为空字符串
                    if ("".equals(data.get(j))) {
                        continue;
                    }
                    switch (j) {
                        case 0:
                            sd.setId((String) data.get(j));
                            break;
                        case 1:
                            sd.setSdName((String) data.get(j));
                            break;
                        case 2:
                            //获取植物规格
                            String specStr = (String) data.get(j);
                            //去掉空格
                            String str = specStr.replaceAll(" ", "");
                            //获取规格前缀
                            String specStr1 = str.substring(1, 2);
                            if ("≤".equals(specStr1) || "≥".equals(specStr1) || "<".equals(specStr1)
                                    || ">".equals(specStr1) || "=".equals(specStr1)) {
                                //含有特殊字符则获取特殊字符
                                sd.setSpec(specStr.substring(0, 2));
                                double min = 0;
                                try {
                                    min = Double.parseDouble(specStr.substring(2));
                                    sd.setSpecMin(min);
                                } catch (NumberFormatException e) {
                                    sd.setSpecMin(min);
                                    System.out.println(e.toString());
                                    this.logger.error(e);
                                }
                            } else {
                                //将获取植物规格以“-”进行拆分
                                String[] spec = str.split("-");
                                //判断是否满足拆分条件
                                if (spec.length > 1) {
                                    double min = 0;
                                    double max = 0;
                                    try {
                                        //获取前缀
                                        sd.setSpec(spec[0].substring(0, 1));
                                        //获取规格范围
                                        min = Double.parseDouble(spec[0].substring(1));
                                        max = Double.parseDouble(spec[1]);
                                        sd.setSpecMin(min);
                                        sd.setSpecMax(max);
                                    } catch (NumberFormatException e) {
                                        this.logger.error(e);
                                        sd.setSpecMin(min);
                                        sd.setSpecMax(max);
                                    }
                                } else {
                                    double min = 0;
                                    sd.setSpec(spec[0].substring(0, 1));
                                    try {
                                        min = Double.parseDouble(spec[0].substring(1));
                                        sd.setSpecMin(min);
                                    } catch (NumberFormatException e) {
                                        sd.setSpecMin(min);
                                        System.out.println(e.toString());
                                        this.logger.error(e);
                                    }
                                }
                            }
                            break;
                        case 3:
                            sd.setUnit((String) data.get(j));
                            break;
                        case 4:
                            try {
                                double price = Double.parseDouble((String) data.get(j));
                                sd.setSdOffer(price);
                            } catch (NumberFormatException e) {
                                sd.setSdOffer(0);
                                this.logger.error(e);
                                System.out.println(e.toString());
                            }
                            break;
                        case 5:
                            String yera = null;
                            String month = null;
                            try {
                                yera = new SimpleDateFormat("yyyy").format(data.get(j));
                                month = new SimpleDateFormat("MM").format(data.get(j));
                            } catch (Exception e) {
                                this.logger.error(e);
                                System.out.println(e.toString());
                            }
                            sd.setYear(yera);
                            sd.setMonth(month);
                            break;
                        case 6:
                            sd.setComment((String) data.get(j));
                            break;
                        default:
                            break;
                    }
                }
                sdData.add(sd);
            }
            //判断保存数据是否有null值，是移除空值
            int len = sdData.size();
            for (int i = 0; i < len; ++i) {
                if (sdData.get(i).getSdName() == null || sdData.get(i).getSdOffer() == 0 || sdData.get(i).getUnit() == null ||
                        sdData.get(i).getYear() == null || sdData.get(i).getMonth() == null) {
                    sdData.remove(i);
                    --i;
                    len = sdData.size();
                }
            }//判断读取数据是否满足正确格式数据，是存库，否删除原始文件
            if (len > 0) {
                this.dao.addSeedlingOfferDao(sdData);
                return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
            } else {
                List<FileData> fileData = this.fileDao.queryFileDao(uuid);
                //删除硬盘上的文件
                this.deleteFile.deleteFile(fileData);
                //删除数据库文件数据
                this.fileDao.deleteFileDao(uuid);
                return new Status(StatusEnum.EXCEL_NO_DATA.getCODE(), StatusEnum.EXCEL_NO_DATA.getEXPLAIN());
            }
        } catch (Exception e) {
            //代码异常删除原始文件，避免数据冗余
            List<FileData> fileData = null;
            try {
                fileData = this.fileDao.queryFileDao(uuid);
                //删除硬盘上的文件
                this.deleteFile.deleteFile(fileData);
                //删除数据库文件数据
                this.fileDao.deleteFileDao(uuid);
            } catch (Exception e1) {
                System.out.println(e.toString());
                this.logger.error(e);
            }
            System.out.println(e.toString());
            this.logger.error(e);
            return new Status(StatusEnum.EXCEL_ERROR.getCODE(), StatusEnum.EXCEL_ERROR.getEXPLAIN());
        }
    }
}
