package com.goodsoft.plantlet.service.lmpl;

import com.goodsoft.plantlet.domain.dao.FileDao;
import com.goodsoft.plantlet.domain.dao.NurseryDao;
import com.goodsoft.plantlet.domain.entity.file.FileData;
import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import com.goodsoft.plantlet.domain.entity.param.AnalysisParam;
import com.goodsoft.plantlet.domain.entity.param.NurseryParam;
import com.goodsoft.plantlet.domain.entity.result.Result;
import com.goodsoft.plantlet.domain.entity.result.Status;
import com.goodsoft.plantlet.domain.entity.result.StatusEnum;
import com.goodsoft.plantlet.service.FileService;
import com.goodsoft.plantlet.service.NurseryService;
import com.goodsoft.plantlet.service.supp.NurseryServiceSupp;
import com.goodsoft.plantlet.service.supp.ServicelmplGetFileSupp;
import com.goodsoft.plantlet.util.DataAnalysisUtil;
import com.goodsoft.plantlet.util.DeleteFileUtil;
import com.goodsoft.plantlet.util.ExcelUtil;
import com.goodsoft.plantlet.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * function 苗圃管理业务接口实现类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Service
public class NurseryServicelmpl implements NurseryService {
    @Resource
    private NurseryDao dao;
    @Resource
    private FileService fileService;
    @Resource
    private FileDao fileDao;
    @Resource
    private NurseryServiceSupp serviceSupp;
    @Resource
    ServicelmplGetFileSupp getFileSupp;
    //实例化日志管理工具类
    private Logger logger = LoggerFactory.getLogger(SeedlingServicelmpl.class);
    //实例化UUID工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();
    //实例化excel工具类
    private ExcelUtil excelUtil = ExcelUtil.getInstance();
    //实例化文件删除工具类
    private DeleteFileUtil deleteFile = DeleteFileUtil.getInstance();
    //实例化数据解析工具类
    private DataAnalysisUtil analysisUtil = DataAnalysisUtil.getInstance();


    /**
     * 省内苗圃数据查询业务处理方法
     *
     * @param request http请求
     * @param msg     条件查询参数
     * @param <T>     泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T queryNurseryService(HttpServletRequest request, NurseryParam param) {
        //初始化msg.getNum() start
        int page = param.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        param.setNum(page);
        //初始化msg.getNum() end
        List<Nursery> data = null;
        try {
            data = this.dao.queryNurseryDao(param);
            int sd = data.size();
            if (sd > 0) {
                for (int i = 0; i < sd; ++i) {
                    //获取文件数据
                    List<String> url = this.getFileSupp.getFileData(request, data.get(i).getFileId());
                    data.get(i).setPicture(url);
                }
                return (T) new Result(0, data);
            } else {
                return (T) new Status(StatusEnum.NO_DATA.getCODE(), StatusEnum.NO_DATA.getEXPLAIN());
            }
        } catch (Exception e) {
            this.logger.error(e.toString());
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }

    }

    /**
     * 省内苗圃多数据录入业务处理方法
     *
     * @param files 文件数据
     * @param msg   录入数据
     * @return 录入结果
     * @throws Exception
     */
    @Override
    @Transactional
    public Status addNurseryService(MultipartFile[] files, Nursery msg) {
        //设置文件编号
        String uuid = this.uuid.getUUID().toString();
        //文件上传
        int arg = this.fileService.fileUploadService(files, "excel", uuid);
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
        FileData file = null;
        try {
            //获取上传文件路径
            file = this.fileDao.queryFileOneDao(uuid);
            StringBuilder sb = new StringBuilder(file.getBases());
            sb.append(file.getPath());
            /*获取上传excel文件数据 start*/
            List<List<Object>> list = this.excelUtil.readExcel(sb.toString(), uuid);
            if (list == null) {
                return new Status(StatusEnum.NO_EXCEL.getCODE(), StatusEnum.NO_EXCEL.getEXPLAIN());
            }
            List<Nursery> sdData = this.serviceSupp.getNurseryExcelData(list);
            /*获取上传excel文件数据 end*/
            //解析excel数据有效性
            int len = this.serviceSupp.getNurseryExcelDataAnalysis(sdData);
            //判断解析数据是否满足正确格式数据，是存库，否删除原始文件
            if (len > 0) {
                this.dao.addNurseryDao(sdData);
                return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
            } else {
                //删除硬盘上的文件
                this.deleteFile.deleteFile(file);
                //删除数据库文件数据
                this.fileDao.deleteFileDao(uuid);
                return new Status(StatusEnum.EXCEL_NO_DATA.getCODE(), StatusEnum.EXCEL_NO_DATA.getEXPLAIN());
            }
        } catch (Exception e) {
            //代码异常删除原始文件，避免数据冗余
            try {
                //删除硬盘上的文件
                this.deleteFile.deleteFile(file);
                //删除数据库文件数据
                this.fileDao.deleteFileDao(uuid);
            } catch (Exception e1) {

                this.logger.error(e.toString());
            }
            this.logger.error(e.toString());
            return new Status(StatusEnum.EXCEL_ERROR.getCODE(), StatusEnum.EXCEL_ERROR.getEXPLAIN());
        }
    }

    /**
     * 省内苗圃数据单条数据录入业务处理方法
     *
     * @param files 文件数据
     * @param msg   录入数据
     * @return 录入结果
     * @throws Exception
     */
    @Override
    @Transactional
    public Status addNurseryOneService(MultipartFile[] files, Nursery msg) {
        //设置文件编号
        String uuid = this.uuid.getUUID().toString();
        //文件上传
        int arg = this.fileService.fileUploadService(files, "nursery", uuid);
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
        //获取规格数据并进行解析
        if (null != msg.getSpec()) {
            AnalysisParam var = this.analysisUtil.getSpecAnalysis(msg.getSpec());
            msg.setSpec(var.getStr());
            msg.setSpecMin(var.getNum());
            msg.setSpecMax(var.getNum_1());
        }
        try {
            this.dao.addNurseryOneDao(msg);
            return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
        } catch (Exception e) {
            this.logger.error(e.toString());
            return new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
    }

    /**
     * 省外苗圃数据查询业务逻辑处理方法
     *
     * @param msg 条件查询参数
     * @param <T> 泛型
     * @return 查询结果
     * @throws Exception
     */
    @Override
    public <T> T queryNurseryOutService(NurseryParam param) {
        //初始化msg.getNum() start
        int page = param.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        param.setNum(page);
        //初始化msg.getNum() end
        List<NurseryOut> data = null;
        try {
            data = this.dao.queryNurseryOutDao(param);
        } catch (Exception e) {
            this.logger.error(e.toString());
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
        if (data.size() > 0) {
            return (T) new Result(0, data);
        }
        return (T) new Status(StatusEnum.NO_DATA.getCODE(), StatusEnum.NO_DATA.getEXPLAIN());
    }

    /**
     * 省外苗圃数据录入业务处理方法
     *
     * @param files 录入数据文件
     * @param msg   录入数据
     * @return 录入结果
     * @throws Exception
     */
    @Override
    @Transactional
    public Status addNurseryOutService(MultipartFile[] files, NurseryOut msg) {
        //设置文件编号
        String uuid = this.uuid.getUUID().toString();
        //文件上传
        int arg = this.fileService.fileUploadService(files, "excel", uuid);
        switch (arg) {
            case 604:
                msg.setId(this.uuid.getUUID().toString());
                //获取规格数据并进行解析
                if (null != msg.getSpec()) {
                    AnalysisParam var = this.analysisUtil.getSpecAnalysis(msg.getSpec());
                    msg.setSpec(var.getStr());
                    msg.setSpecMin(var.getNum());
                    msg.setSpecMax(var.getNum_1());
                }
                try {
                    this.dao.addNurseryOutOneDao(msg);
                    return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
                } catch (Exception e) {

                    this.logger.error(e.toString());
                    return new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
                }
            case 603:
                return new Status(StatusEnum.FILE_FORMAT.getCODE(), StatusEnum.FILE_FORMAT.getEXPLAIN());
            case 601:
                return new Status(StatusEnum.FILE_SIZE.getCODE(), StatusEnum.FILE_SIZE.getEXPLAIN());
            case 600:
                return new Status(StatusEnum.FILE_UPLOAD.getCODE(), StatusEnum.FILE_UPLOAD.getEXPLAIN());
            default:
                FileData file = null;
                try {
                    //获取上传文件路径
                    file = this.fileDao.queryFileOneDao(uuid);
                    StringBuilder sb = new StringBuilder(file.getBases());
                    sb.append(file.getPath());
                    /*获取上传excel文件数据 start*/
                    List<List<Object>> list = this.excelUtil.readExcel(sb.toString(), uuid);
                    if (list == null) {
                        return new Status(StatusEnum.NO_EXCEL.getCODE(), StatusEnum.NO_EXCEL.getEXPLAIN());
                    }
                    List<NurseryOut> sdData = this.serviceSupp.getNurseryOutExcelData(list);
                    /*获取上传excel文件数据 end*/
                    //解析excel数据有效性
                    int len = this.serviceSupp.getNurseryOutExcelDataAnalysis(sdData);
                    System.out.println(sdData.get(0).getSpec());
                    //判断解析数据是否满足正确格式数据，是存库，否删除原始文件
                    if (len > 0) {
                        this.dao.addNurseryOutDao(sdData);
                        return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
                    } else {
                        //删除硬盘上的文件
                        this.deleteFile.deleteFile(file);
                        //删除数据库文件数据
                        this.fileDao.deleteFileDao(uuid);
                        return new Status(StatusEnum.EXCEL_NO_DATA.getCODE(), StatusEnum.EXCEL_NO_DATA.getEXPLAIN());
                    }
                } catch (Exception e) {
                    //代码异常删除文件，避免数据冗余
                    try {
                        //删除硬盘上的文件
                        this.deleteFile.deleteFile(file);
                        //删除数据库文件数据
                        this.fileDao.deleteFileDao(uuid);
                    } catch (Exception e1) {

                        this.logger.error(e.toString());
                    }
                    this.logger.error(e.toString());
                    return new Status(StatusEnum.EXCEL_ERROR.getCODE(), StatusEnum.EXCEL_ERROR.getEXPLAIN());
                }
        }
    }
}
