package com.goodsoft.plantlet.util;

import com.goodsoft.plantlet.domain.entity.param.AnalysisParam;

/**
 * function 数据解析工具类
 * Created by 严彬荣 on 2017/9/20.
 */
@SuppressWarnings("ALL")
public class DataAnalysisUtil {
    /**
     * 创建ExcelUtil类的单例（详情见本包下UUIDUtil类） start
     **/
    private volatile static DataAnalysisUtil instance;

    private DataAnalysisUtil() {
    }

    public static DataAnalysisUtil getInstance() {
        if (instance == null) {
            synchronized (DataAnalysisUtil.class) {
                if (instance == null)
                    instance = new DataAnalysisUtil();
            }
        }
        return instance;
    }

    /**
     * 规格数据解析出来方法
     *
     * @param var 待解析数据
     * @return 解析后数据
     */
    public AnalysisParam getSpecAnalysis(String var) {
        AnalysisParam data = new AnalysisParam();
        //获取植物规格并去掉空格
        String str = var.replaceAll(" ", "");
        //获取规格前缀
        String specStr1 = str.substring(1, 2);
        if ("≤".equals(specStr1) || "≥".equals(specStr1) || "<".equals(specStr1)
                || ">".equals(specStr1) || "=".equals(specStr1)) {
            //含有特殊字符则获取特殊字符
            String str1 = var.substring(0, 2);
            try {
                data.setStr(str1);
                double min = Double.parseDouble(var.substring(2));
                data.setNum(min);
            } catch (NumberFormatException e) {
                System.out.println(e.toString());
            }
        } else {
            //将获取植物规格以“-”进行拆分
            String[] spec = str.split("-");
            //判断是否满足拆分条件
            if (spec.length > 1) {
                try {
                    //获取前缀
                    data.setStr(spec[0].substring(0, 1));
                    //获取规格范围
                    double min = Double.parseDouble(spec[0].substring(1));
                    double max = Double.parseDouble(spec[1]);
                    data.setNum(min);
                    data.setNum_1(min);
                } catch (NumberFormatException e) {
                    System.out.println(e.toString());
                }
            } else {
                data.setStr(spec[0].substring(0, 1));
                try {
                    double min = Double.parseDouble(spec[0].substring(1));
                    data.setNum(min);
                } catch (NumberFormatException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return data;
    }
}
