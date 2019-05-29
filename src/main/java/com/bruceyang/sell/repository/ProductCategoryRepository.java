package com.bruceyang.sell.repository;

import com.bruceyang.sell.dao.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yangxiaoge
 * 2019/5/23 15:52
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
}
