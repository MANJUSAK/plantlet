package com.goodsoft.plantlet.domain.entity.param;

import java.util.Objects;

/**
 * function 数据解析辅助类
 * Created by 严彬荣 on 2017/9/20.
 * version v1.0
 */
public class AnalysisParam implements java.io.Serializable {
    private static final long serialVersionUID = -6457887470637274777L;
    private String str;//字符
    //数字
    private double num;
    private double num_1;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public double getNum_1() {
        return num_1;
    }

    public void setNum_1(double num_1) {
        this.num_1 = num_1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalysisParam)) return false;
        AnalysisParam that = (AnalysisParam) o;
        return Double.compare(that.num, num) == 0 &&
                Double.compare(that.num_1, num_1) == 0 &&
                Objects.equals(str, that.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str, num, num_1);
    }
}
