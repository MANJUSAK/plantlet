package com.goodsoft.plantlet.util.result;

import java.util.Objects;

/**
 * function 苗木条件查询参数辅助类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public class SeedlingParm implements java.io.Serializable {
    private String keyWord;//苗木名称
    private String tp;//种类
    private String comp;//企业名称
    private String province;//省份
    private String city;//市
    private String address;//地址
    private String minPrice;//价格起始范围
    private String maxPrice;//价格终止范围
    private int num;//页码
    private String spec;//规格

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeedlingParm)) return false;
        SeedlingParm that = (SeedlingParm) o;
        return num == that.num &&
                Objects.equals(keyWord, that.keyWord) &&
                Objects.equals(tp, that.tp) &&
                Objects.equals(comp, that.comp) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(address, that.address) &&
                Objects.equals(minPrice, that.minPrice) &&
                Objects.equals(maxPrice, that.maxPrice) &&
                Objects.equals(spec, that.spec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyWord, tp, comp, province, city, address, minPrice, maxPrice, num, spec);
    }
}
