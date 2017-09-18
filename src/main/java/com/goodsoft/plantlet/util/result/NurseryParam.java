package com.goodsoft.plantlet.util.result;

import java.util.Objects;

/**
 * function 苗木企业条件查询参数辅助实体类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public class NurseryParam implements java.io.Serializable {
    private String keyWord;//关键字
    private String province;//省份
    private String city;//区县市
    private String specMin;//规格范围第一位
    private String specMax;//规格范围第二位
    private String minPrice;//起始价格
    private String maxPrice;//终止价格
    private String area;//苗圃面积
    private String tp;//种类
    private String name;//植物名称
    private int num;//页码

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof NurseryParam)) return false;
        NurseryParam that = (NurseryParam) o;
        return num == that.num &&
                Objects.equals(keyWord, that.keyWord) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(specMin, that.specMin) &&
                Objects.equals(specMax, that.specMax) &&
                Objects.equals(minPrice, that.minPrice) &&
                Objects.equals(maxPrice, that.maxPrice) &&
                Objects.equals(area, that.area) &&
                Objects.equals(tp, that.tp) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyWord, province, city, specMin, specMax, minPrice, maxPrice, area, tp, name, num);
    }
}
