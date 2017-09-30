package com.goodsoft.plantlet.domain.entity.param;

import java.util.Objects;

/**
 * function 苗木统计条件检索参数辅助类
 * Created by 严彬荣 on 2017/9/19.
 * version v1.0
 */
public class SeedlingStatisticsParam implements java.io.Serializable {
    private static final long serialVersionUID = 8423688272252505911L;
    private String keyWord;//关键字
    private String provincOut;//外省
    private String city;//省内市
    private String county;//省内区县
    private String spec;//规格
    private String specMin;//规格范围第一位
    private String specMax;//规格范围第二位
    private String year;//年
    private String month;//月
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

    public String getProvincOut() {
        return provincOut;
    }

    public void setProvincOut(String provincOut) {
        this.provincOut = provincOut;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeedlingStatisticsParam)) return false;
        SeedlingStatisticsParam that = (SeedlingStatisticsParam) o;
        return num == that.num &&
                Objects.equals(keyWord, that.keyWord) &&
                Objects.equals(provincOut, that.provincOut) &&
                Objects.equals(city, that.city) &&
                Objects.equals(county, that.county) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(specMin, that.specMin) &&
                Objects.equals(specMax, that.specMax) &&
                Objects.equals(year, that.year) &&
                Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyWord, provincOut, city, county, spec, specMin, specMax, year, month, num);
    }
}
