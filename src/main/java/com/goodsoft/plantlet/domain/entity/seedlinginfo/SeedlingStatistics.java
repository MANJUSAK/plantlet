package com.goodsoft.plantlet.domain.entity.seedlinginfo;

import java.util.Objects;

/**
 * function 苗木信息统计实体
 * Created by 严彬荣 on 2017/9/19.
 * version v1.0
 */
public class SeedlingStatistics implements java.io.Serializable {
    private String id;//数据id
    private String spec;//规格
    private double specMin;//规格范围第一位
    private double specMax;//规格范围第二位
    private double offer;//造价
    private double marketPrice;//市场
    private String sdName;//苗木名称
    private int numOut;//省外数量
    private int num;//省内数量
    private double priceOut;//省外价格
    private double price;//省内价格
    private String unit;//单位
    private String comp;//省内来源
    private String compOut;//省外来源
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

    public double getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(double priceOut) {
        this.priceOut = priceOut;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getCompOut() {
        return compOut;
    }

    public void setCompOut(String compOut) {
        this.compOut = compOut;
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
                Double.compare(that.priceOut, priceOut) == 0 &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(sdName, that.sdName) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(comp, that.comp) &&
                Objects.equals(compOut, that.compOut) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spec, specMin, specMax, offer, marketPrice, sdName, numOut, num, priceOut, price, unit, comp, compOut, comment);
    }
}
