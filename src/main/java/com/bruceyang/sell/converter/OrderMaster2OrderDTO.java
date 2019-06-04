package com.bruceyang.sell.converter;

import com.bruceyang.sell.dao.OrderMaster;
import com.bruceyang.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangxiaoge
 * 2019/6/4 20:14
 */
public class OrderMaster2OrderDTO {
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        /*List<OrderDTO> orderDTO = new ArrayList<>();
        BeanUtils.copyProperties(orderMasterList, orderDTO);
        return orderDTO;*/
        //lambda表达式
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
