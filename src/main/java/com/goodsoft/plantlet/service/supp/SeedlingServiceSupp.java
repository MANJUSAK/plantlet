package com.goodsoft.plantlet.service.supp;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.util.DataAnalysisUtil;
import com.goodsoft.plantlet.util.result.AnalysisParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * function 苗木信息业务接口实现辅助类
 * Created by 严彬荣 on 2017/9/21.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Service
public class SeedlingServiceSupp {
    //实例化日志管理工具类
    private Logger logger = Logger.getLogger(NurseryServiceSupp.class);
    //实例化数据解析工具类
    private DataAnalysisUtil analysisUtil = DataAnalysisUtil.getInstance();
    //初始化读取excel字段值
    private Object str = "";

    /**
     * 苗木造价解析excel数据业务辅助类
     *
     * @param list 待解析数据
     * @return 解析后数据
     */
    public List<SeedlingOffer> getSeedlingOfferExcelData(List<List<Object>> list) {
        //实例化数据保存集合类
        List<SeedlingOffer> sdData = sdData = new ArrayList<SeedlingOffer>();
        for (int i = 0, len = list.size(); i < len; ++i) {
            List<Object> data = list.get(i);
            int d = data.size();
            SeedlingOffer sd = new SeedlingOffer();
            for (int j = 0; j < d; ++j) {
                this.str = data.get(j);
                switch (j) {
                    case 0:
                        sd.setId((String) this.str);
                        break;
                    case 1:
                        sd.setSdName((String) this.str);
                        break;
                    case 2:
                        if (this.str != "") {
                            AnalysisParam var = null;
                            try {
                                var = this.analysisUtil.getSpecAnalysis((String) this.str);
                                sd.setSpec(var.getStr());
                                sd.setSpecMin(var.getNum());
                                sd.setSpecMax(var.getNum_1());
                            } catch (Exception e) {
                                System.out.println(e.toString());
                            }
                        }
                        break;
                    case 3:
                        sd.setUnit((String) this.str);
                        break;
                    case 4:
                        if (this.str != "") {
                            try {
                                double price = Double.parseDouble((String) this.str);
                                sd.setSdOffer(price);
                            } catch (Exception e) {
                                this.logger.error(e);
                                System.out.println(e.toString());
                            }
                        }
                        break;
                    case 5:
                        if (this.str != "") {
                            try {
                                int yera = Integer.parseInt(new SimpleDateFormat("yyyy").format(this.str));
                                int month = Integer.parseInt(new SimpleDateFormat("MM").format(this.str));
                                sd.setYear(yera);
                                sd.setMonth(month);
                            } catch (Exception e) {
                                System.out.println(e.toString());
                                this.logger.error(e);
                            }
                        }
                        break;
                    case 6:
                        sd.setComment((String) this.str);
                        break;
                    default:
                        break;
                }
            }
            sdData.add(sd);
        }
        return sdData;
    }

    /**
     * 解析苗木造价excel数据有效性辅助方法
     *
     * @param sdData 待解析excel数据
     * @return 解析后有效数据条数
     */
    public int getSeedlingOfferExcelDataAnalysis(List<SeedlingOffer> sdData) {
        int len = sdData.size();
        for (int i = 0; i < len; ++i) {
            if (sdData.get(i).getSdName() == "" || sdData.get(i).getSdOffer() == 0 || sdData.get(i).getUnit() == "" ||
                    sdData.get(i).getYear() == 0 || sdData.get(i).getMonth() == 0 || sdData.get(i).getSpec() == null) {
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
        if (!(o instanceof SeedlingServiceSupp)) return false;
        SeedlingServiceSupp that = (SeedlingServiceSupp) o;
        return Objects.equals(str, that.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }
}
