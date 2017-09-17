package com.goodsoft.plantlet.domain.entity.seedlinginfo;

import java.util.Objects;

/**
 * 苗木参考报价实体
 * Created by 严彬荣 on 2017/9/4.
 * version v1.0
 */
public class SeedlingOffer implements java.io.Serializable {
    private String id;//数据id
    private String seedlingName;//苗木名称
    private long tel;//联系电话
    private String province;//省份
    private String city;//市
    private String specification;//规格
    private String unit;//单位
    private String address;//地址
    private double price;//价格
    private String types;//类别
    private String comment;//备注
    private String date;//采集时间
    private String company;//企业
    private int num;//数量
    private String fileId;//文件编号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeedlingName() {
        return seedlingName;
    }

    public void setSeedlingName(String seedlingName) {
        this.seedlingName = seedlingName;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeedlingOffer)) return false;
        SeedlingOffer that = (SeedlingOffer) o;
        return tel == that.tel && Objects.equals(address, that.address) &&
                Double.compare(that.price, price) == 0 &&
                num == that.num &&
                Objects.equals(id, that.id) &&
                Objects.equals(seedlingName, that.seedlingName) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(specification, that.specification) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(types, that.types) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(date, that.date) &&
                Objects.equals(company, that.company) &&
                Objects.equals(fileId, that.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seedlingName, tel, address, province, city, specification, unit, price, types, comment, date, company, num, fileId);
    }
}
