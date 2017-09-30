package com.goodsoft.plantlet.service.supp;

import com.goodsoft.plantlet.domain.entity.seedlinginfo.SeedlingOffer;
import com.goodsoft.plantlet.util.DataAnalysisUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * function 苗木信息业务接口实现辅助类
 * Created by 严彬荣 on 2017/9/21.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Service
public class SeedlingServiceSupp {
    //实例化数据解析工具类
    private DataAnalysisUtil analysisUtil = DataAnalysisUtil.getInstance();

    /**
     * 解析苗木造价excel数据有效性辅助方法
     *
     * @param sdData 待解析excel数据
     * @return 解析后有效数据条数
     */
    public int getSeedlingOfferExcelDataAnalysis(List<SeedlingOffer> sdData) {
        int len = sdData.size();
        for (int i = 0; i < len; ++i) {
            if (sdData.get(i).getSdName() == "" || sdData.get(i).getYear() == 0) {
                sdData.remove(i);
                --i;
                len = sdData.size();
            }
        }
        return len;
    }
}
