package com.goodsoft.plantlet.domain.entity.seedlinginfo;

import java.util.Objects;

/**
 * function 苗木造价信息实体
 * Created by 严彬荣 on 2017/9/19.
 * version v1.0
 */
public class SeedlingOffer implements java.io.Serializable {

    private String id;//数据id
    private String sdName;//苗木名称
    private String spec;//规格前缀
    private double specMin;//规格范围第一位
    private double specMax;//规格范围第二位
    private String unit;//单位
    private double sdOffer;//造价
    private int year;//年
    private int month;//月
    private String comment;//备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSdName() {
        return sdName;
    }

    public void setSdName(String sdName) {
        this.sdName = sdName;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getSdOffer() {
        return sdOffer;
    }

    public void setSdOffer(double sdOffer) {
        this.sdOffer = sdOffer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeedlingOffer)) return false;
        SeedlingOffer that = (SeedlingOffer) o;
        return Double.compare(that.specMin, specMin) == 0 &&
                Double.compare(that.specMax, specMax) == 0 &&
                Double.compare(that.sdOffer, sdOffer) == 0 &&
                year == that.year &&
                month == that.month &&
                Objects.equals(id, that.id) &&
                Objects.equals(sdName, that.sdName) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sdName, spec, specMin, specMax, unit, sdOffer, year, month, comment);
    }
}
