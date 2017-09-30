package com.goodsoft.plantlet.service.supp;

import com.goodsoft.plantlet.domain.entity.nursery.Nursery;
import com.goodsoft.plantlet.domain.entity.nursery.NurseryOut;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * function 苗圃管理接口实现辅助功能类
 * Created by 严彬荣 on 2017/9/21.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Service
public class NurseryServiceSupp {


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
}
