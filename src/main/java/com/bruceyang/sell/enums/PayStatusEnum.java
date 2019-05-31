package com.bruceyang.sell.enums;

import lombok.Getter;

/**
 * Created by yangxiaoge
 * 2019/5/30 18:47
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
