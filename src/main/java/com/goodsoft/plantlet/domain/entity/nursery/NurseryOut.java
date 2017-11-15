package com.goodsoft.plantlet.domain.entity.nursery;

import java.util.Objects;

/**
 * 苗木参考报价实体
 * Created by 严彬荣 on 2017/9/4.
 * version v1.0
 */
public class NurseryOut implements java.io.Serializable {
    private static final long serialVersionUID = 602967075477219871L;
    private String id;//数据id
    private String seedlingName;//苗木名称
    private long tel;//联系电话
    private String province;//省份
    private String city;//市
    private String spec;//规格前缀
    private double specMin;//规格范围第一位
    private double specMax;//规格范围第二位
    private String fax;//传真
    private String webSite;//网址
    private String email;//邮箱
    private String unit;//单位
    private String address;//地址
    private double price;//价格
    private String types;//类别
    private String comment;//备注
    private String date;//采集时间
    private String company;//企业
    private int num;//数量
    private String fileId;//文件编号

    public NurseryOut() {
        this.fileId = "";
        this.city = "";
    }

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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof NurseryOut)) return false;
        NurseryOut that = (NurseryOut) o;
        return tel == that.tel &&
                Double.compare(that.specMin, specMin) == 0 &&
                Double.compare(that.specMax, specMax) == 0 &&
                Double.compare(that.price, price) == 0 &&
                num == that.num &&
                Objects.equals(id, that.id) &&
                Objects.equals(seedlingName, that.seedlingName) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(fax, that.fax) &&
                Objects.equals(webSite, that.webSite) &&
                Objects.equals(email, that.email) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(address, that.address) &&
                Objects.equals(types, that.types) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(date, that.date) &&
                Objects.equals(company, that.company) &&
                Objects.equals(fileId, that.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seedlingName, tel, province, city, spec, specMin, specMax, fax, webSite, email, unit, address, price, types, comment, date, company, num, fileId);
    }
}
