package com.goodsoft.plantlet.service.lmpl;

import com.goodsoft.plantlet.domain.dao.FileDao;
import com.goodsoft.plantlet.domain.dao.SeedlingDao;
import com.goodsoft.plantlet.domain.entity.file.FileData;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingStatistics;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SupplyInfo;
import com.goodsoft.plantlet.service.FileService;
import com.goodsoft.plantlet.service.SeedlingService;
import com.goodsoft.plantlet.service.supp.SeedlingServiceSupp;
import com.goodsoft.plantlet.service.supp.ServicelmplGetFileSupp;
import com.goodsoft.plantlet.util.DataAnalysisUtil;
import com.goodsoft.plantlet.util.DeleteFileUtil;
import com.goodsoft.plantlet.util.ExcelUtil;
import com.goodsoft.plantlet.util.UUIDUtil;
import com.goodsoft.plantlet.util.result.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private ServicelmplGetFileSupp getFileSupp;
    @Resource
    private SeedlingServiceSupp serviceSupp;
    //实例化日志管理工具类
    private Logger logger = Logger.getLogger(SeedlingServicelmpl.class);
    //实例化UUID工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();
    //实例化excel工具类
    private ExcelUtil excelUtil = ExcelUtil.getInstance();
    //实例化文件删除工具类
    private DeleteFileUtil deleteFile = DeleteFileUtil.getInstance();
    //实例化数据解析工具类
    private DataAnalysisUtil analysisUtil = DataAnalysisUtil.getInstance();

    /**
     * 苗木数据检索业务处理方法
     *
     * @param msg 检索条件
     * @param <T> 泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T querySeedlingService(SeedlingStatisticsParam param) {
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
            data = this.dao.querySeedlingInfoDao(param);
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
     * 供需发布数据检索业务处理方法
     *
     * @param request 请求
     * @param msg     检索条件
     * @param <T>     泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T querySeedlingService(HttpServletRequest request, SupplyParam msg) {
        //初始化msg.getNum() start
        int page = msg.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        msg.setNum(page);
        //初始化msg.getNum() end
        List<SupplyInfo> data = null;
        try {
            data = this.dao.querySeedlingSupplyDao(msg);
            int sd = data.size();
            if (sd > 0) {
                for (int i = 0; i < sd; ++i) {
                    List<String> url = this.getFileSupp.getFileData(request, data.get(i).getFileId());
                    data.get(i).setPicture(url);
                }
                return (T) new Result(0, data);
            } else {
                return (T) new Status(StatusEnum.NO_DATA.getCODE(), StatusEnum.NO_DATA.getEXPLAIN());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
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
     * 苗木造价统计年月份数据检索业务处理方法
     *
     * @param msg 检索条件
     * @param <T> 泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T querySeedlingOfferStatisticsService(SeedlingOfferParam msg) {
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
            data = this.dao.querySeedlingOfferStatisticsDetailDao(msg);
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
     * 苗木信息添加业务处理方法
     *
     * @param msg 添加数据
     * @return 添加结果
     * @throws Exception
     */
    @Override
    @Transactional
    public Status addSeedlingService(SeedlingStatistics msg) {
        try {
            msg.setId(this.uuid.getUUID().toString());
            AnalysisParam var = this.analysisUtil.getSpecAnalysis((String) msg.getSpec());
            msg.setSpec(var.getStr());
            msg.setSpecMin(var.getNum());
            msg.setSpecMax(var.getNum_1());
            this.dao.addSeedlingInfoDao(msg);
            return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
    }

    /**
     * 供需信息发布业务处理方法
     *
     * @param files 数据文件
     * @param msg   发布数据
     * @return 发布结果
     * @throws Exception
     */
    @Override
    @Transactional
    public Status addSeedlingService(MultipartFile[] files, SupplyInfo msg) {
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
            AnalysisParam var = this.analysisUtil.getSpecAnalysis((String) msg.getSpec());
            msg.setSpec(var.getStr());
            msg.setSpecMin(var.getNum());
            msg.setSpecMax(var.getNum_1());
            this.dao.addSeedlingSupplyDao(msg);
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
                    AnalysisParam var = this.analysisUtil.getSpecAnalysis((String) msg.getSpec());
                    msg.setSpec(var.getStr());
                    msg.setSpecMin(var.getNum());
                    msg.setSpecMax(var.getNum_1());
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
            /*获取上传excel文件数据 start*/
            List<List<Object>> list = this.excelUtil.readExcel(sb.toString(), uuid);
            if (list == null) {
                return new Status(StatusEnum.NO_EXCEL.getCODE(), StatusEnum.NO_EXCEL.getEXPLAIN());
            }
            List<SeedlingOffer> sdData = this.serviceSupp.getSeedlingOfferExcelData(list);
            /*获取上传excel文件数据 end*/
            //解析excel保存数据是否有无效，是移除
            int len = this.serviceSupp.getSeedlingOfferExcelDataAnalysis(sdData);
            //判断读取数据是否满足正确格式数据，是存库，否删除原始文件
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
