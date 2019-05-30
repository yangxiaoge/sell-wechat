package com.bruceyang.sell.enums;

import lombok.Getter;

/**
 * Created by yangxiaoge
 * 2019/5/30 14:30
 * 商品状态枚举
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
