package com.bruceyang.sell.service.impl;

import com.bruceyang.sell.dao.ProductInfo;
import com.bruceyang.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yangxiaoge
 * 2019/5/30 14:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertNotNull("123456", productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
//        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("回味鸭血粉丝");
        productInfo.setProductPrice(new BigDecimal(12));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("南京特色美食");
        productInfo.setProductIcon("http://nnnnn.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(1);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }
}