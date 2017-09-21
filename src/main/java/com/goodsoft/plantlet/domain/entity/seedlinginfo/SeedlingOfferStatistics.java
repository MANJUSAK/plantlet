package com.goodsoft.plantlet.domain.entity.seedlinginfo;

import java.util.Objects;

/**
 * function 苗木信息统计年月份实体
 * Created by 严彬荣 on 2017/9/19.
 * version v1.0
 */
public class SeedlingOfferStatistics implements java.io.Serializable {
    private String sdName;// 苗木名称
    private String spec;// 规格前缀
    private double specMin;// 规格范围第一位
    private double specMax;// 规格范围第二位
    private String comment;// 备注
    private int year;// 年份
    private double Jan;// 1月份造价
    private double Feb;// 2月份造价
    private double Mar;// 3月份造价
    private double Apr;// 4月份造价
    private double May;// 5月份造价
    private double Jun;// 6月份造价
    private double Jul;// 7月份造价
    private double Aug;// 8月份造价
    private double Sep;// 9月份造价
    private double Oct;// 10月份造价
    private double Nov;// 11月份造价
    private double Dec;// 12月份造价

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

    public double getJan() {
        return Jan;
    }

    public void setJan(double jan) {
        Jan = jan;
    }

    public double getFeb() {
        return Feb;
    }

    public void setFeb(double feb) {
        Feb = feb;
    }

    public double getMar() {
        return Mar;
    }

    public void setMar(double mar) {
        Mar = mar;
    }

    public double getApr() {
        return Apr;
    }

    public void setApr(double apr) {
        Apr = apr;
    }

    public double getMay() {
        return May;
    }

    public void setMay(double may) {
        May = may;
    }

    public double getJun() {
        return Jun;
    }

    public void setJun(double jun) {
        Jun = jun;
    }

    public double getJul() {
        return Jul;
    }

    public void setJul(double jul) {
        Jul = jul;
    }

    public double getAug() {
        return Aug;
    }

    public void setAug(double aug) {
        Aug = aug;
    }

    public double getSep() {
        return Sep;
    }

    public void setSep(double sep) {
        Sep = sep;
    }

    public double getOct() {
        return Oct;
    }

    public void setOct(double oct) {
        Oct = oct;
    }

    public double getNov() {
        return Nov;
    }

    public void setNov(double nov) {
        Nov = nov;
    }

    public double getDec() {
        return Dec;
    }

    public void setDec(double dec) {
        Dec = dec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeedlingOfferStatistics)) return false;
        SeedlingOfferStatistics that = (SeedlingOfferStatistics) o;
        return Double.compare(that.specMin, specMin) == 0 &&
                Double.compare(that.specMax, specMax) == 0 &&
                year == that.year &&
                Double.compare(that.Jan, Jan) == 0 &&
                Double.compare(that.Feb, Feb) == 0 &&
                Double.compare(that.Mar, Mar) == 0 &&
                Double.compare(that.Apr, Apr) == 0 &&
                Double.compare(that.May, May) == 0 &&
                Double.compare(that.Jun, Jun) == 0 &&
                Double.compare(that.Jul, Jul) == 0 &&
                Double.compare(that.Aug, Aug) == 0 &&
                Double.compare(that.Sep, Sep) == 0 &&
                Double.compare(that.Oct, Oct) == 0 &&
                Double.compare(that.Nov, Nov) == 0 &&
                Double.compare(that.Dec, Dec) == 0 &&
                Objects.equals(sdName, that.sdName) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sdName, spec, specMin, specMax, comment, year, Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec);
    }
}
