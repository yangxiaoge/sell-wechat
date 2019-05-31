package com.bruceyang.sell.enums;

import lombok.Getter;

/**
 * Created by yangxiaoge
 * 2019/5/30 18:38
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
