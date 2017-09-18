package com.goodsoft.plantlet.service.lmpl;

import com.goodsoft.plantlet.domain.dao.FileDao;
import com.goodsoft.plantlet.domain.dao.NurseryDao;
import com.goodsoft.plantlet.domain.entity.file.FileData;
import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import com.goodsoft.plantlet.service.FileService;
import com.goodsoft.plantlet.service.NurseryService;
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
import java.util.ArrayList;
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
    //实例化日志管理工具类
    private Logger logger = Logger.getLogger(SeedlingServicelmpl.class);
    //实例化UUID工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();
    //实例化excel工具类
    private ExcelUtil excelUtil = ExcelUtil.getInstance();
    //实例化服务器域名地址工具类
    private DomainNameUtil domainName = DomainNameUtil.getInstance();


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
    public <T> T queryNurseryService(HttpServletRequest request, NurseryParam msg) {
        //初始化msg.getNum() start
        int page = msg.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        msg.setNum(page);
        //初始化msg.getNum() end
        List<Nursery> data = null;
        try {
            data = this.dao.queryNurseryDao(msg);
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
            //实例化数据保存集合类
            List<Nursery> sdData = sdData = new ArrayList<Nursery>();
            for (int i = 0, len = list.size(); i < len; ++i) {
                List<Object> data = list.get(i);
                int d = data.size();
                Nursery sd = new Nursery();
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
                            sd.setProvince((String) data.get(j));
                            break;
                        case 2:
                            sd.setDistricts((String) data.get(j));
                            break;
                        case 3:
                            sd.setNurseryName((String) data.get(j));
                            break;
                        case 4:
                            sd.setNurseryAdd((String) data.get(j));
                            break;
                        case 5:
                            try {
                                int code = Integer.parseInt((String) data.get(j));
                                sd.setPostCode(code);
                            } catch (NumberFormatException e) {
                                this.logger.error(e);
                                System.out.println(e.toString());
                                sd.setPostCode(0);
                            }
                            break;
                        case 6:
                            try {
                                long tel = Long.parseLong((String) data.get(j));
                                sd.setTel(tel);
                            } catch (NumberFormatException e) {
                                this.logger.error(e);
                                System.out.println(e.toString());
                                sd.setTel(0);
                            }
                            break;
                        case 7:
                            sd.setFax((String) data.get(j));
                            break;
                        case 8:
                            sd.setContact((String) data.get(j));
                            break;
                        case 9:
                            sd.setEmail((String) data.get(j));
                            break;
                        case 10:
                            sd.setPlantName((String) data.get(j));
                            break;
                        case 11:
                            //获取植物规格
                            String specStr = (String) data.get(j);
                            //去掉空格
                            String str = specStr.replaceAll(" ", "");
                            //获取规格前缀
                            String specStr1 = str.substring(1, 2);
                            if ("≤".equals(specStr1) || "≥".equals(specStr1)) {
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
                        case 12:
                            int num = 0;
                            try {
                                num = Integer.parseInt((String) data.get(j));
                                sd.setNum(num);
                            } catch (NumberFormatException e) {
                                sd.setNum(num);
                                this.logger.error(e);
                                System.out.println(e.toString());
                            }
                            break;
                        case 13:
                            try {
                                double price = Double.parseDouble((String) data.get(j));
                                sd.setPrice(price);
                            } catch (NumberFormatException e) {
                                sd.setPrice(0);
                                this.logger.error(e);
                                System.out.println(e.toString());
                            }
                            break;
                        case 14:
                            sd.setTypes((String) data.get(j));
                            break;
                        case 15:
                            try {
                                double area = Double.parseDouble((String) data.get(j));
                                sd.setArea(area);
                            } catch (NumberFormatException e) {
                                this.logger.error(e);
                                System.out.println(e.toString());
                                sd.setArea(0);
                            }
                            break;
                        case 16:
                            sd.setProLicenseNum((String) data.get(j));
                            break;
                        case 17:
                            sd.setOperLicenseNum((String) data.get(j));
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
                if (sdData.get(i).getProvince() == null || sdData.get(i).getNurseryName() == null || sdData.get(i).getArea() == 0 ||
                        sdData.get(i).getPostCode() == 0 || sdData.get(i).getTel() == 0 || sdData.get(i).getPlantName() == null
                        || sdData.get(i).getSpec() == null || sdData.get(i).getPrice() == 0 ||
                        sdData.get(i).getNum() == 0 || sdData.get(i).getTypes() == null) {
                    sdData.remove(i);
                    --i;
                    len = sdData.size();
                }
            }//判断读取数据是否满足正确格式数据
            if (len > 0) {
                this.dao.addNurseryDao(sdData);
                sdData.clear();
                return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
            } else {
                return new Status(StatusEnum.EXCEL_ERROR.getCODE(), StatusEnum.EXCEL_ERROR.getEXPLAIN());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
            return new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
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
        try {
            this.dao.addNurseryOneDao(msg);
            return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
        } catch (Exception e) {
            System.out.println(e.toString());
            this.logger.error(e);
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
    public <T> T queryNurseryOutService(NurseryOutParam msg) {
        //初始化msg.getNum() start
        int page = msg.getNum();
        if (page < 0) {
            page = 0;
        }
        page *= 20;
        msg.setNum(page);
        //初始化msg.getNum() end
        List<NurseryOut> data = null;
        try {
            data = this.dao.queryNurseryOutDao(msg);
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
                try {
                    this.dao.addNurseryOutOneDao(msg);
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
                    //实例化数据保存集合类
                    List<NurseryOut> sdData = sdData = new ArrayList<NurseryOut>();
                    for (int i = 0, len = list.size(); i < len; ++i) {
                        List<Object> data = list.get(i);
                        int d = data.size();
                        NurseryOut sd = new NurseryOut();
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
                                    sd.setProvince((String) data.get(j));
                                    break;
                                case 2:
                                    sd.setCompany((String) data.get(j));
                                    break;
                                case 3:
                                    sd.setAddress((String) data.get(j));
                                    break;
                                case 4:
                                    try {
                                        long tel = Long.parseLong((String) data.get(j));
                                        sd.setTel(tel);
                                    } catch (NumberFormatException e) {
                                        this.logger.error(e);
                                        System.out.println(e.toString());
                                        sd.setTel(0);
                                    }
                                    break;
                                case 5:
                                    sd.setFax((String) data.get(j));
                                    break;
                                case 6:
                                    sd.setWebSite((String) data.get(j));
                                    break;
                                case 7:
                                    sd.setEmail((String) data.get(j));
                                case 8:
                                    sd.setSeedlingName((String) data.get(j));
                                    break;
                                case 9:
                                    //获取植物规格
                                    String specStr = (String) data.get(j);
                                    //去掉空格
                                    String str = specStr.replaceAll(" ", "");
                                    //获取规格前缀
                                    String specStr1 = str.substring(1, 2);
                                    if ("≤".equals(specStr1) || "≥".equals(specStr1)) {
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
                                            try {
                                                min = Double.parseDouble(spec[0].substring(1));
                                                sd.setSpecMin(min);
                                            } catch (NumberFormatException e) {
                                                sd.setSpecMin(min);
                                                System.out.println(e.toString());
                                                this.logger.error(e);
                                            }
                                            sd.setSpec(spec[0].substring(0, 1));
                                        }
                                    }
                                    break;
                                case 10:
                                    sd.setUnit((String) data.get(j));
                                    break;
                                case 11:
                                    int num = 0;
                                    try {
                                        num = Integer.parseInt((String) data.get(j));
                                        sd.setNum(num);
                                    } catch (NumberFormatException e) {
                                        sd.setNum(num);
                                        this.logger.error(e);
                                        System.out.println(e.toString());
                                    }
                                    break;
                                case 12:
                                    try {
                                        double price = Double.parseDouble((String) data.get(j));
                                        sd.setPrice(price);
                                    } catch (NumberFormatException e) {
                                        sd.setPrice(0);
                                        this.logger.error(e);
                                        System.out.println(e.toString());
                                    }
                                    break;
                                case 13:
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
                        if (0 == sdData.get(i).getPrice() || sdData.get(i).getNum() == 0 || sdData.get(i).getSeedlingName() == null
                                || sdData.get(i).getCompany() == null || sdData.get(i).getUnit() == null ||
                                sdData.get(i).getSpec() == null || sdData.get(i).getSpecMin() == 0) {
                            sdData.remove(i);
                            --i;
                            len = sdData.size();
                        }
                    }
                    //判断读取数据是否满足正确格式数据
                    if (len > 0) {
                        this.dao.addNurseryOutDao(sdData);
                        sdData.clear();
                        return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
                    } else {
                        return new Status(StatusEnum.EXCEL_ERROR.getCODE(), StatusEnum.EXCEL_ERROR.getEXPLAIN());
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                    this.logger.error(e);
                    return new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
                }
        }
    }
}
