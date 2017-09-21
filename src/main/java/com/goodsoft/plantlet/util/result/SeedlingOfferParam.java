package com.goodsoft.plantlet.util.result;

import java.util.Objects;

/**
 * function 查询苗木造价参数辅助实体类
 * Created by 严彬荣 on 2017/9/19.
 */
public class SeedlingOfferParam implements java.io.Serializable {
    private String keyWord;//关键字
    private String spec;//规格前缀
    private String specMin;//规格范围第一位
    private String specMax;//规格范围第二位
    private String minPrice;//最小价格
    private String maxPrice;//最大价格
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

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
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
        if (!(o instanceof SeedlingOfferParam)) return false;
        SeedlingOfferParam that = (SeedlingOfferParam) o;
        return num == that.num &&
                Objects.equals(keyWord, that.keyWord) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(specMin, that.specMin) &&
                Objects.equals(specMax, that.specMax) &&
                Objects.equals(minPrice, that.minPrice) &&
                Objects.equals(maxPrice, that.maxPrice) &&
                Objects.equals(year, that.year) &&
                Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyWord, spec, specMin, specMax, minPrice, maxPrice, year, month, num);
    }
}
