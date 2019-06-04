package com.bruceyang.sell.service.impl;

import com.bruceyang.sell.converter.OrderMaster2OrderDTO;
import com.bruceyang.sell.dao.OrderDetail;
import com.bruceyang.sell.dao.OrderMaster;
import com.bruceyang.sell.dao.ProductInfo;
import com.bruceyang.sell.dto.CartDTO;
import com.bruceyang.sell.dto.OrderDTO;
import com.bruceyang.sell.enums.OrderStatusEnum;
import com.bruceyang.sell.enums.PayStatusEnum;
import com.bruceyang.sell.enums.ResultEnum;
import com.bruceyang.sell.exception.SellException;
import com.bruceyang.sell.repository.OrderDetailRepository;
import com.bruceyang.sell.repository.OrderMasterRepository;
import com.bruceyang.sell.service.OrderService;
import com.bruceyang.sell.service.ProductService;
import com.bruceyang.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by yangxiaoge
 * 2019/5/31 10:59
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDto) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1.查询商品(数量，价格)
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            //orderDetail.getProductPrice()*orderDetail.getProductQuantity()
            orderAmount = productInfo.getProductPrice()//价格取数据库中的，前端不会传价格过来
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        //3.写入订单数据库（OrderMaster，OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        //防止属性拷贝覆盖原有数据
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList = orderDto.getOrderDetailList()
                .stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDto;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        Example<OrderMaster> example = Example.of(orderMaster);
        //查询订单表
        Optional<OrderMaster> orderResult = orderMasterRepository.findOne(example);
        OrderMaster master = orderResult.orElse(null);
        if (master == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //查询订单详情表
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
