package com.goodsoft.plantlet.service.lmpl;

import com.goodsoft.plantlet.domain.dao.FileDao;
import com.goodsoft.plantlet.domain.dao.SeedlingDao;
import com.goodsoft.plantlet.domain.dao.SeedlingOutputDao;
import com.goodsoft.plantlet.domain.entity.file.FileData;
import com.goodsoft.plantlet.domain.entity.param.NewestOfferParam;
import com.goodsoft.plantlet.domain.entity.result.Result;
import com.goodsoft.plantlet.domain.entity.result.Status;
import com.goodsoft.plantlet.domain.entity.result.StatusEnum;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOfferStatistics;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingStatistics;
import com.goodsoft.plantlet.domain.entity.seedlinginfo.SupplyInfo;
import com.goodsoft.plantlet.service.SeedlingOutputService;
import com.goodsoft.plantlet.util.DomainNameUtil;
import com.goodsoft.plantlet.util.ExcelSeedlingUtil;
import com.goodsoft.plantlet.util.WriteSupplyPdfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * function 苗木数据导出业务接口实现类
 * Created by 严彬荣 on 2017/10/9.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Service
public class SeedlingOutputServicelmpl implements SeedlingOutputService {
    @Resource
    private SeedlingOutputDao dao;
    @Resource
    private SeedlingDao suppDao;
    @Resource
    private FileDao fileDao;
    //实例化日志管理工具类
    private Logger logger = LoggerFactory.getLogger(SeedlingOutputServicelmpl.class);
    //实例化导出excel工具类
    private ExcelSeedlingUtil excelSeedlingUtil = ExcelSeedlingUtil.getInstance();
    //实例化获取服务器域名工具类
    private DomainNameUtil http = DomainNameUtil.getInstance();
    //实例化pdf工具类
    private WriteSupplyPdfUtil pdfUtil = WriteSupplyPdfUtil.getInstance();

    /**
     * 苗木造价数据导出业务方法
     *
     * @param year  年份
     * @param month 月份
     * @param <T>   泛型
     * @return 导出结果
     */
    @Override
    public <T> T outputSeedlingOfferService(HttpServletRequest request, int year, int month) {
        StringBuilder sb = new StringBuilder();
        try {
            List<SeedlingOffer> data = this.dao.querySeedlingOfferDao(year, month);
            if (data.size() > 0) {
                sb.append(this.http.getServerDomainName(request).toString());
                sb.append(this.excelSeedlingUtil.writeExcel(data, "only", year, month));
                return (T) new Result(0, sb.toString());
            }
        } catch (Exception e) {
            this.logger.error(e.toString());
            return (T) new Status(StatusEnum.DEFEAT.getCODE(), StatusEnum.DEFEAT.getEXPLAIN());
        }
        return (T) new Status(StatusEnum.NO_EXCEL_DATA.getCODE(), StatusEnum.NO_EXCEL_DATA.getEXPLAIN());
    }

    /**
     * 苗木造价统计数据导出业务方法
     *
     * @param <T> 泛型
     * @return 导出结果
     */
    @Override
    public <T> T outputSeedlingOfferService(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            NewestOfferParam nop = this.suppDao.queryNewestOfferDao();
            int year = nop.getYear();
            List<SeedlingOfferStatistics> data = this.dao.querySeedlingOfferStatisticsDao(year);
            if (data.size() > 0) {
                sb.append(this.http.getServerDomainName(request).toString());
                sb.append(this.excelSeedlingUtil.writeExcel(data, "more", year, 0));
                return (T) new Result(0, sb.toString());
            }
        } catch (Exception e) {
            this.logger.error(e.toString());
            return (T) new Status(StatusEnum.DEFEAT.getCODE(), StatusEnum.DEFEAT.getEXPLAIN());
        }
        return (T) new Status(StatusEnum.NO_EXCEL_DATA.getCODE(), StatusEnum.NO_EXCEL_DATA.getEXPLAIN());
    }

    /**
     * 苗木信息统计数据导出业务方法
     *
     * @param <T> 泛型
     * @return 导出结果
     */
    @Override
    public <T> T outputSeedlingStatisticsService(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            List<SeedlingStatistics> data = this.dao.querySeedlingStatisticsDao();
            if (data.size() > 0) {
                sb.append(this.http.getServerDomainName(request).toString());
                sb.append(this.excelSeedlingUtil.writeExcel(data));
                return (T) new Result(0, sb.toString());
            }
        } catch (Exception e) {
            this.logger.error(e.toString());
            return (T) new Status(StatusEnum.DEFEAT.getCODE(), StatusEnum.DEFEAT.getEXPLAIN());
        }
        return (T) new Status(StatusEnum.NO_EXCEL_DATA.getCODE(), StatusEnum.NO_EXCEL_DATA.getEXPLAIN());
    }

    /**
     * 供需数据导出业务方法
     *
     * @param request 请求
     * @param <T>     泛型
     * @return 导出结果
     */
    @Override
    public <T> T outputSeedlingSupplyService(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            List<SupplyInfo> data = this.dao.querySeedlingSupplyDao();
            int len = data.size();
            if (len > 0) {
                for (int i = 0; i < len; ++i) {
                    FileData file = this.fileDao.queryFileOneDao(data.get(i).getId());
                    if (file != null) {
                        sb.append(file.getBases());
                        sb.append(file.getPath());
                        data.get(i).setDirectory(sb.toString());
                        sb.delete(0, sb.length());
                    }
                }
                sb.append(this.http.getServerDomainName(request).toString());
                sb.append(this.pdfUtil.writePdf(data));
                return (T) new Result(0, sb.toString());
            }
        } catch (Exception e) {
            this.logger.error(e.toString());
            return (T) new Status(StatusEnum.DEFEAT.getCODE(), StatusEnum.DEFEAT.getEXPLAIN());
        }
        return (T) new Status(StatusEnum.NO_PDF_DATA.getCODE(), StatusEnum.NO_PDF_DATA.getEXPLAIN());
    }
}