package com.goodsoft.plantlet.service.supp;

import com.goodsoft.plantlet.config.aop.ControllerAop;
import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import com.goodsoft.plantlet.domain.entity.param.AnalysisParam;
import com.goodsoft.plantlet.util.DataAnalysisUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * function 苗圃管理接口实现辅助功能类
 * Created by 严彬荣 on 2017/9/21.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Service
public class NurseryServiceSupp {
    //实例化日志管理工具类
    private org.slf4j.Logger logger = LoggerFactory.getLogger(ControllerAop.class);
    //实例化数据解析工具类
    private DataAnalysisUtil analysisUtil = DataAnalysisUtil.getInstance();
    //初始化读取excel字段值
    private Object str = "";

    /**
     * 省内苗圃获取excel数据解析辅助方法
     *
     * @param list 读取到的excel数据
     * @return 解析后数据
     */
    public List<Nursery> getNurseryExcelData(List<List<Object>> list) {
        //实例化数据保存集合类
        List<Nursery> sdData = sdData = new ArrayList<Nursery>();
        for (int i = 0, len = list.size(); i < len; ++i) {
            List<Object> data = list.get(i);
            int d = data.size();
            Nursery sd = new Nursery();
            for (int j = 0; j < d; ++j) {
                this.str = data.get(j);
                switch (j) {
                    case 0:
                        sd.setId((String) this.str);
                        break;
                    case 1:
                        sd.setProvince((String) this.str);
                        break;
                    case 2:
                        sd.setDistricts((String) this.str);
                        break;
                    case 3:
                        sd.setCounty((String) this.str);
                        break;
                    case 4:
                        sd.setNurseryName((String) this.str);
                        break;
                    case 5:
                        sd.setNurseryAdd((String) this.str);
                        break;
                    case 6:
                        try {
                            if (this.str != "") {
                                int code = Integer.parseInt((String) this.str);
                                sd.setPostCode(code);
                            }
                        } catch (Exception e) {
                            this.logger.error(e.toString());
                        }
                        break;
                    case 7:
                        try {
                            if (this.str != "") {
                                long tel = Long.parseLong((String) this.str);
                                sd.setTel(tel);
                            }
                        } catch (Exception e) {
                            this.logger.error(e.toString());
                        }
                        break;
                    case 8:
                        sd.setFax((String) this.str);
                        break;
                    case 9:
                        sd.setContact((String) this.str);
                        break;
                    case 10:
                        sd.setEmail((String) this.str);
                        break;
                    case 11:
                        sd.setPlantName((String) this.str);
                        break;
                    case 12:
                        if (this.str != "") {
                            try {
                                AnalysisParam var = this.analysisUtil.getSpecAnalysis((String) this.str);
                                sd.setSpec(var.getStr());
                                sd.setSpecMin(var.getNum());
                                sd.setSpecMax(var.getNum_1());
                            } catch (Exception e) {
                                this.logger.error(e.toString());
                                sd.setSpec("");
                            }
                        } else {
                            sd.setSpec("");
                        }
                        break;
                    case 13:
                        try {
                            if (this.str != "") {
                                int num = Integer.parseInt((String) this.str);
                                sd.setNum(num);
                            }
                        } catch (Exception e) {
                            this.logger.error(e.toString());
                        }
                        break;
                    case 14:
                        try {
                            if (this.str != "") {
                                double price = Double.parseDouble((String) this.str);
                                sd.setPrice(price);
                            }
                        } catch (Exception e) {
                            this.logger.error(e.toString());
                        }
                        break;
                    case 15:
                        sd.setTypes((String) this.str);
                        break;
                    case 16:
                        try {
                            if (this.str != "") {
                                double area = Double.parseDouble((String) this.str);
                                sd.setArea(area);
                            }
                        } catch (Exception e) {
                            this.logger.error(e.toString());
                        }
                        break;
                    case 17:
                        sd.setProLicenseNum((String) this.str);
                        break;
                    case 18:
                        sd.setOperLicenseNum((String) this.str);
                        break;
                    default:
                        break;
                }
            }
            sd.setNurseryIntro("");
            sd.setFileId("");
            sdData.add(sd);
        }
        return sdData;
    }

    /**
     * 解析省内苗圃excel数据有效性辅助方法
     *
     * @param sdData 待解析excel数据
     * @return 解析后有效数据条数
     */
    public int getNurseryExcelDataAnalysis(List<Nursery> sdData) {
        int len = sdData.size();
        for (int i = 0; i < len; ++i) {
            if (sdData.get(i).getPlantName() == "") {
                sdData.remove(i);
                --i;
                len = sdData.size();
            }
        }
        return len;
    }

    /**
     * 省外苗圃获取excel数据解析辅助方法
     *
     * @param list 读取到的excel数据
     * @return 解析后数据
     */
    public List<NurseryOut> getNurseryOutExcelData(List<List<Object>> list) {
        //实例化数据保存集合类
        List<NurseryOut> sdData = sdData = new ArrayList<NurseryOut>();
        for (int i = 0, len = list.size(); i < len; ++i) {
            List<Object> data = list.get(i);
            int d = data.size();
            NurseryOut sd = new NurseryOut();
            for (int j = 0; j < d; ++j) {
                this.str = data.get(j);
                switch (j) {
                    case 0:
                        sd.setId((String) this.str);
                        break;
                    case 1:
                        sd.setProvince((String) this.str);
                        break;
                    case 2:
                        sd.setCompany((String) this.str);
                        break;
                    case 3:
                        sd.setAddress((String) this.str);
                        break;
                    case 4:
                        try {
                            if (this.str != "") {
                                long tel = Long.parseLong((String) this.str);
                                sd.setTel(tel);
                            }
                        } catch (Exception e) {
                            this.logger.error(e.toString());

                        }
                        break;
                    case 5:
                        sd.setFax((String) this.str);
                        break;
                    case 6:
                        sd.setWebSite((String) this.str);
                        break;
                    case 7:
                        sd.setEmail((String) this.str);
                        break;
                    case 8:
                        sd.setSeedlingName((String) this.str);
                        break;
                    case 9:
                        if (this.str != "") {
                            try {
                                AnalysisParam var = this.analysisUtil.getSpecAnalysis((String) this.str);
                                sd.setSpec(var.getStr());
                                sd.setSpecMin(var.getNum());
                                sd.setSpecMax(var.getNum_1());
                            } catch (Exception e) {
                                this.logger.error(e.toString());
                                sd.setSpec("");
                            }
                        } else {
                            sd.setSpec("");
                        }
                        break;
                    case 10:
                        sd.setUnit((String) this.str);
                        break;
                    case 11:
                        int num = 0;
                        try {
                            if (this.str != "") {
                                num = Integer.parseInt((String) this.str);
                                sd.setNum(num);
                            }
                        } catch (Exception e) {
                            this.logger.error(e.toString());
                        }
                        break;
                    case 12:
                        try {
                            if (this.str != "") {
                                double price = Double.parseDouble((String) this.str);
                                sd.setPrice(price);
                            }
                        } catch (Exception e) {
                            sd.setPrice(0);
                            this.logger.error(e.toString());
                        }
                        break;
                    case 13:
                        sd.setTypes((String) this.str);
                        break;
                    case 14:
                        sd.setDate((String) this.str);
                        break;
                    case 15:
                        sd.setComment((String) this.str);
                        break;
                    default:
                        break;
                }
            }
            sd.setFileId("");
            sd.setCity("");
            sdData.add(sd);
        }
        return sdData;
    }

    /**
     * 解析省外苗圃excel数据有效性辅助方法
     *
     * @param sdData 待解析excel数据
     * @return 解析后有效数据条数
     */
    public int getNurseryOutExcelDataAnalysis(List<NurseryOut> sdData) {
        int len = sdData.size();
        for (int i = 0; i < len; ++i) {
            if (sdData.get(i).getSeedlingName() == "") {
                sdData.remove(i);
                --i;
                len = sdData.size();
            }
        }
        return len;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NurseryServiceSupp)) return false;
        NurseryServiceSupp that = (NurseryServiceSupp) o;
        return Objects.equals(str, that.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }
}
