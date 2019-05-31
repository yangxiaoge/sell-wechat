package com.bruceyang.sell.repository;

import com.bruceyang.sell.dao.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yangxiaoge
 * 2019/5/30 19:06
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
