package com.goodsoft.plantlet.domain.entity.param;

import java.util.Arrays;
import java.util.Objects;

/**
 * function 苗木企业条件查询参数辅助实体类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public class NurseryParam implements java.io.Serializable {
    private static final long serialVersionUID = -2663103036526929655L;
    private String id;//数据id
    private String keyWord;//关键字
    private String[] provinces;//多省份
    private String province;//单省份
    private String city;//区县市
    private String county;//县
    private String spec;//规格前缀
    private String specMin;//规格范围第一位
    private String specMax;//规格范围第二位
    private String comp;//企业名称
    private int num;//页码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String[] getProvinces() {
        return provinces;
    }

    public void setProvinces(String[] provinces) {
        this.provinces = provinces;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSpecMin() {
        return specMin;
    }

    public void setSpecMin(String specMin) {
        this.specMin = specMin;
    }

    public String getSpecMax() {
        return specMax;
    }

    public void setSpecMax(String specMax) {
        this.specMax = specMax;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NurseryParam)) return false;
        NurseryParam that = (NurseryParam) o;
        return num == that.num &&
                Objects.equals(id, that.id) &&
                Objects.equals(keyWord, that.keyWord) &&
                Arrays.equals(provinces, that.provinces) &&
                Objects.equals(city, that.city) &&
                Objects.equals(province, that.province) &&
                Objects.equals(county, that.county) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(specMin, that.specMin) &&
                Objects.equals(specMax, that.specMax) &&
                Objects.equals(comp, that.comp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keyWord, provinces, province, city, county, spec, specMin, specMax, comp, num);
    }
}
