package com.goodsoft.plantlet.domain.entity.seedlinginfo;

import java.util.Objects;

/**
 * function 苗木信息统计实体
 * Created by 严彬荣 on 2017/9/19.
 * version v1.0
 */
public class SeedlingStatistics implements java.io.Serializable {
    private String spec;
    private double offer;
    private double marketPrice;
    private String sdName;
    private int numOut;
    private int num;
    private double priceOut;
    private double price;
    private String unit;

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeedlingStatistics)) return false;
        SeedlingStatistics that = (SeedlingStatistics) o;
        return Double.compare(that.offer, offer) == 0 &&
                Double.compare(that.marketPrice, marketPrice) == 0 &&
                numOut == that.numOut &&
                num == that.num &&
                Double.compare(that.priceOut, priceOut) == 0 &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(sdName, that.sdName) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spec, offer, marketPrice, sdName, numOut, num, priceOut, price, unit);
    }
}
