package com.bruceyang.sell.dto;

import com.bruceyang.sell.dao.OrderDetail;
import com.bruceyang.sell.enums.OrderStatusEnum;
import com.bruceyang.sell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by yangxiaoge
 * 2019/5/31 10:55
 * DESC:订单信息 - 只返回部分数据给前端
 */
@Data
public class OrderDTO {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 卖家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信openid
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态，默认0新下单
     */
    private Integer orderStatus  ;
    /**
     * 支付状态,默认0未支付
     */
    private Integer payStatus ;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    List<OrderDetail> orderDetailList;

}
