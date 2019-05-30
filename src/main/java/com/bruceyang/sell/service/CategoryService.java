package com.bruceyang.sell.service;

import com.bruceyang.sell.dao.ProductCategory;

import java.util.List;

/**
 * Created by yangxiaoge
 * 2019/5/30 13:07
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
