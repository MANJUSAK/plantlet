package com.goodsoft.plantlet.util.result;

import java.util.Objects;

/**
 * function 苗木统计条件检索参数辅助类
 * Created by 严彬荣 on 2017/9/19.
 * version v1.0
 */
public class SeedlingStatisticsParam implements java.io.Serializable {
    private String keyWord;//关键字
    private String spec;//规格
    private String specMin;//规格范围第一位
    private String specMax;//规格范围第二位
    private int num;//页码

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSpecMin() {
        return specMin;
    }

    public void setSpecMin(String specMin) {
        this.specMin = specMin;
    }

    public String getSpecMax() {
        return specMax;
    }

    public void setSpecMax(String specMax) {
        this.specMax = specMax;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeedlingStatisticsParam)) return false;
        SeedlingStatisticsParam that = (SeedlingStatisticsParam) o;
        return num == that.num &&
                Objects.equals(keyWord, that.keyWord) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(specMin, that.specMin) &&
                Objects.equals(specMax, that.specMax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyWord, spec, specMin, specMax, num);
    }
}
