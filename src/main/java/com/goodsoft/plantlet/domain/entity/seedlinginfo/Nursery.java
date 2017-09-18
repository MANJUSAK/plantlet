package com.goodsoft.plantlet.domain.entity.seedlinginfo;

import java.util.List;
import java.util.Objects;

/**
 * function 苗木企业信息实体
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public class Nursery implements java.io.Serializable {
    private String id;//数据id
    private String plantName;//植物名称
    private String types;//种类
    private String nurseryName;//苗圃名称
    private String contact;//联系人
    private long tel;//联系电话
    private String nurseryIntro;//苗圃简介
    private String fax;//传真
    private String email;//邮箱
    private String nurseryAdd;//苗圃地址
    private String province;//省
    private String districts;//区县市
    private String spec;//规格前缀
    private double specMin;//规格范围第一位
    private double specMax;//规格范围第二位
    private double num;//数量
    private double price;//单价
    private double area;//苗圃面积
    private String fileId;//文件编号
    private String proLicenseNum;//生产许可证编号
    private String operLicenseNum;//经营许可证编号
    private int postCode;//邮编
    private List<String> picture;//文件图片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getContact() {
        return contact;
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

    public String getNurseryIntro() {
        return nurseryIntro;
    }

    public void setNurseryIntro(String nurseryIntro) {
        this.nurseryIntro = nurseryIntro;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNurseryAdd() {
        return nurseryAdd;
    }

    public void setNurseryAdd(String nurseryAdd) {
        this.nurseryAdd = nurseryAdd;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
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

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getProLicenseNum() {
        return proLicenseNum;
    }

    public void setProLicenseNum(String proLicenseNum) {
        this.proLicenseNum = proLicenseNum;
    }

    public String getOperLicenseNum() {
        return operLicenseNum;
    }

    public void setOperLicenseNum(String operLicenseNum) {
        this.operLicenseNum = operLicenseNum;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
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
        if (!(o instanceof Nursery)) return false;
        Nursery nursery = (Nursery) o;
        return tel == nursery.tel &&
                Double.compare(nursery.specMin, specMin) == 0 &&
                Double.compare(nursery.specMax, specMax) == 0 &&
                Double.compare(nursery.num, num) == 0 &&
                Double.compare(nursery.price, price) == 0 &&
                Double.compare(nursery.area, area) == 0 &&
                postCode == nursery.postCode &&
                Objects.equals(id, nursery.id) &&
                Objects.equals(plantName, nursery.plantName) &&
                Objects.equals(types, nursery.types) &&
                Objects.equals(nurseryName, nursery.nurseryName) &&
                Objects.equals(contact, nursery.contact) &&
                Objects.equals(nurseryIntro, nursery.nurseryIntro) &&
                Objects.equals(fax, nursery.fax) &&
                Objects.equals(email, nursery.email) &&
                Objects.equals(nurseryAdd, nursery.nurseryAdd) &&
                Objects.equals(province, nursery.province) &&
                Objects.equals(districts, nursery.districts) &&
                Objects.equals(spec, nursery.spec) &&
                Objects.equals(fileId, nursery.fileId) &&
                Objects.equals(proLicenseNum, nursery.proLicenseNum) &&
                Objects.equals(operLicenseNum, nursery.operLicenseNum) &&
                Objects.equals(picture, nursery.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, plantName, types, nurseryName, contact, tel, nurseryIntro, fax, email, nurseryAdd, province, districts, spec, specMin, specMax, num, price, area, fileId, proLicenseNum, operLicenseNum, postCode, picture);
    }
}
