package com.goodsoft.plantlet.domain.entity.seedlinginfo;

import java.util.List;
import java.util.Objects;

/**
 * function 苗木信息实体
 * Created by 严彬荣 on 2017/9/18.
 * version v1.0
 */
public class SeedlingInfo implements java.io.Serializable {
    private String id;//数据id
    private String sdName;//植物名称
    private String sdType;//种类
    private String spec;//规格前缀
    private double specMin;//规格范围第一位
    private double specMax;//规格范围第二位
    private String unit;//单位
    private double num;//数量
    private String content;//内容
    private String fileId;//文件编号
    private String contact;//联系人
    private String tel;//手机号
    private String sdAdd;//供货地址
    private List<String> picture;//文件

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

    public String getSdType() {
        return sdType;
    }

    public void setSdType(String sdType) {
        this.sdType = sdType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSdAdd() {
        return sdAdd;
    }

    public void setSdAdd(String sdAdd) {
        this.sdAdd = sdAdd;
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
        if (!(o instanceof SeedlingInfo)) return false;
        SeedlingInfo that = (SeedlingInfo) o;
        return Double.compare(that.specMin, specMin) == 0 &&
                Double.compare(that.specMax, specMax) == 0 &&
                Double.compare(that.num, num) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(sdName, that.sdName) &&
                Objects.equals(sdType, that.sdType) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(content, that.content) &&
                Objects.equals(fileId, that.fileId) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(sdAdd, that.sdAdd) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sdName, sdType, spec, specMin, specMax, unit, num, content, fileId, contact, tel, sdAdd, picture);
    }
}

