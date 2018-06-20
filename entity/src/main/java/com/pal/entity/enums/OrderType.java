package com.pal.entity.enums;

/**
 * 订单类型
 * @author pal
 */
public enum OrderType {

    APPLY("申购"),

    REDEEM("赎回");

    private String desc;

    OrderType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
