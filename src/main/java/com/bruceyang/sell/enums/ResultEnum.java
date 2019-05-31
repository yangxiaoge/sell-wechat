package com.bruceyang.sell.enums;

import lombok.Getter;

/**
 * Created by yangxiaoge
 * 2019/5/31 11:06
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),

    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
