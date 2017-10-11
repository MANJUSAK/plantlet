package com.goodsoft.plantlet.domain.entity.seedlinginfo;

import java.util.List;
import java.util.Objects;

/**
 * function 苗木信息实体
 * Created by 严彬荣 on 2017/9/18.
 * version v1.0
 */
public class SupplyInfo implements java.io.Serializable {
    private static final long serialVersionUID = -2837551732745666959L;
    private String id;//数据id
    private String seedlingComp;//苗木企业
    private String seedlingIntro;//苗木简介
    private String sdName;//植物名称
    private String sdType;//种类
    private int type;//供需类型（1为供应，2为需求）
    private String spec;//规格前缀
    private double specMin;//规格范围第一位
    private double specMax;//规格范围第二位
    private String unit;//单位
    private double num;//数量
    private String contact;//联系人
    private long tel;//手机号
    private String sdAdd;//供货地址
    private double price;//价格
    private String directory;//文件根目录，用户导入到pdf
    private List<String> picture;//文件

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeedlingComp() {
        return seedlingComp;
    }

    public void setSeedlingComp(String seedlingComp) {
        this.seedlingComp = seedlingComp;
    }

    public String getSeedlingIntro() {
        return seedlingIntro;
    }

    public void setSeedlingIntro(String seedlingIntro) {
        this.seedlingIntro = seedlingIntro;
    }

    public String getSdName() {
        return sdName;
    }

    public void setSdName(String sdName) {
        this.sdName = sdName;
    }

    public String getSdType() {
        return sdType;
    }

    public void setSdType(String sdType) {
        this.sdType = sdType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public String getContact() {
        return contact;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getSdAdd() {
        return sdAdd;
    }

    public void setSdAdd(String sdAdd) {
        this.sdAdd = sdAdd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplyInfo)) return false;
        SupplyInfo that = (SupplyInfo) o;
        return type == that.type &&
                Double.compare(that.specMin, specMin) == 0 &&
                Double.compare(that.specMax, specMax) == 0 &&
                Double.compare(that.num, num) == 0 &&
                tel == that.tel &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(seedlingComp, that.seedlingComp) &&
                Objects.equals(seedlingIntro, that.seedlingIntro) &&
                Objects.equals(sdName, that.sdName) &&
                Objects.equals(sdType, that.sdType) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(sdAdd, that.sdAdd) &&
                Objects.equals(directory, that.directory) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seedlingComp, seedlingIntro, sdName, sdType, type, spec, specMin, specMax, unit, num, contact, tel, sdAdd, price, directory, picture);
    }
}

