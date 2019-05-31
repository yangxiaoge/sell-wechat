package com.bruceyang.sell.dto;

import lombok.Data;

/**
 * Created by yangxiaoge
 * 2019/5/31 13:34
 * desc:购物车
 */
@Data
public class CartDTO {
    /**
     *商品id
     */
    private String productId;
    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
