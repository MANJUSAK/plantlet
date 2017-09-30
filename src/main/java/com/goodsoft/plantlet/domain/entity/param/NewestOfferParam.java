package com.goodsoft.plantlet.domain.entity.param;

import java.util.Objects;

/**
 * function 获取苗木造价最新年月份数据
 * Created by 严彬荣 on 2017/9/25.
 * version v1.0
 */
public class NewestOfferParam implements java.io.Serializable {
    private static final long serialVersionUID = -2727532216166774144L;
    private int year;//年
    private int month;//月

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
        if (!(o instanceof NewestOfferParam)) return false;
        NewestOfferParam that = (NewestOfferParam) o;
        return year == that.year &&
                month == that.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month);
    }
}
