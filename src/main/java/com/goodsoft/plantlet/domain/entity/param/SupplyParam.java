package com.goodsoft.plantlet.domain.entity.param;

import java.util.Objects;

/**
 * function 苗木信息参数辅助类
 * Created by 严彬荣 on 2017/9/18.
 * version v1.0
 */
public class SupplyParam implements java.io.Serializable {

    private String keyWord;//关键字
    private String tp;//种类
    private String specMin;//规格范围第一位
    private String specMax;//规格范围第二位
    private String sdAdd;//供货地址
    private int num;//页码

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
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

    public String getSdAdd() {
        return sdAdd;
    }

    public void setSdAdd(String sdAdd) {
        this.sdAdd = sdAdd;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplyParam)) return false;
        SupplyParam that = (SupplyParam) o;
        return num == that.num &&
                Objects.equals(keyWord, that.keyWord) &&
                Objects.equals(tp, that.tp) &&
                Objects.equals(specMin, that.specMin) &&
                Objects.equals(specMax, that.specMax) &&
                Objects.equals(sdAdd, that.sdAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyWord, tp, specMin, specMax, sdAdd, num);
    }
}
