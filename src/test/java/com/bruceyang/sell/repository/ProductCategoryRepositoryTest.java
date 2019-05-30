package com.bruceyang.sell.repository;

import com.bruceyang.sell.dao.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.awt.image.ImageWatched;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by yangxiaoge
 * 2019/5/23 15:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findById(){
        Optional<ProductCategory> optional = repository.findById(1);
        if (optional.isPresent()){
            ProductCategory productCategory = optional.get();
            System.out.println(productCategory.toString());
        }
        else {
            System.out.println("没有对应id数据");
        }
    }

    @Test
    @Transactional //不插入数据库，只测试数据
    public void saveTest(){
//        ProductCategory productCategory = new ProductCategory();
//        productCategory.setCategoryId(3);
//        productCategory.setCategoryName("男生最爱");
//        productCategory.setCategoryType(666);
//        repository.save(productCategory);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(101);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest(){
        Optional<ProductCategory> optional = repository.findById(1);
        if (optional.isPresent()){
            ProductCategory productCategory = optional.get();
            productCategory.setCategoryType(10);
            repository.save(productCategory);
        }
        else {
            System.out.println("没有对应id数据");
        }
    }

    @Test
    public  void findByCategoryTypeIn(){
        List<Integer> list = Arrays.asList(1,2,3,5);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}