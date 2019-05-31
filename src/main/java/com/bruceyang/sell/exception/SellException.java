package com.bruceyang.sell.exception;

import com.bruceyang.sell.enums.ResultEnum;

/**
 * Created by yangxiaoge
 * 2019/5/31 11:05
 */
public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
