package com.goodsoft.plantlet.domain.entity.seedlinginfo;

import java.util.Objects;

/**
 * function 苗木信息统计实体
 * Created by 严彬荣 on 2017/9/19.
 * version v1.0
 */
public class SeedlingStatistics implements java.io.Serializable {
    private static final long serialVersionUID = -2624223591840645523L;
    private String id;//数据编号
    private String spec;//规格
    private double specMin;//规格范围第一位
    private double specMax;//规格范围第二位
    private double offer;//造价
    private double marketPrice;//市场
    private String sdName;//苗木名称
    private int numOut;//省外数量
    private int num;//省内数量
    private String unit;//单位
    private double maxPrice;//按百分比分析价格
    private int year;//年份
    private int month;//月份
    private String comp;//企业
    private String comment;//备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public double getSpecMin() {
        return specMin;
    }

    public void setSpecMin(double specMin) {
        this.specMin = specMin;
    }

    public double getSpecMax() {
        return specMax;
    }

    public void setSpecMax(double specMax) {
        this.specMax = specMax;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getSdName() {
        return sdName;
    }

    public void setSdName(String sdName) {
        this.sdName = sdName;
    }

    public int getNumOut() {
        return numOut;
    }

    public void setNumOut(int numOut) {
        this.numOut = numOut;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeedlingStatistics)) return false;
        SeedlingStatistics that = (SeedlingStatistics) o;
        return Double.compare(that.specMin, specMin) == 0 &&
                Double.compare(that.specMax, specMax) == 0 &&
                Double.compare(that.offer, offer) == 0 &&
                Double.compare(that.marketPrice, marketPrice) == 0 &&
                numOut == that.numOut &&
                num == that.num &&
                Double.compare(that.maxPrice, maxPrice) == 0 &&
                year == that.year &&
                month == that.month &&
                Objects.equals(id, that.id) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(sdName, that.sdName) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(comp, that.comp) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spec, specMin, specMax, offer, marketPrice, sdName, numOut, num, unit, maxPrice, year, month, comp, comment);
    }
}
