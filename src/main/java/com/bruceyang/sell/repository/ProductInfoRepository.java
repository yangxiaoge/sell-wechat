package com.bruceyang.sell.repository;

import com.bruceyang.sell.dao.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yangxiaoge
 * 2019/5/30 13:49
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
