package com.bruceyang.sell.service.impl;

import com.bruceyang.sell.dao.OrderDetail;
import com.bruceyang.sell.dto.OrderDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoge
 * 2019/5/31 13:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private static String BUYER_OPENID = "10211021";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("小珠珠");
        orderDTO.setBuyerAddress("弘阳广场");
        orderDTO.setBuyerPhone("19905171930");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(3);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(5);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单] result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
//        OrderDTO result = orderService.findOne("111");
//        OrderDTO result = orderService.findOne("123456");
        OrderDTO result = orderService.findOne("1559284495771232026");
        log.info("【查询单个订单】 result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findList() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        log.info("【查询用户订单列表】 result={}", new Gson().toJson(orderDTOPage.getContent()));
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}