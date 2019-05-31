package com.bruceyang.sell.service.impl;

import com.bruceyang.sell.dao.ProductInfo;
import com.bruceyang.sell.dto.CartDTO;
import com.bruceyang.sell.enums.ProductStatusEnum;
import com.bruceyang.sell.enums.ResultEnum;
import com.bruceyang.sell.exception.SellException;
import com.bruceyang.sell.repository.ProductInfoRepository;
import com.bruceyang.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by yangxiaoge
 * 2019/5/30 14:25
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productId);
        Example<ProductInfo> example = Example.of(productInfo);
        Optional<ProductInfo> result = repository.findOne(example);
        return result.orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo qryProductInfo = new ProductInfo();
            qryProductInfo.setProductId(cartDTO.getProductId());
            Example<ProductInfo> example = Example.of(qryProductInfo);
            Optional<ProductInfo> optional = repository.findOne(example);
            if (optional.isPresent()) {
                ProductInfo productInfo = optional.get();
                int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
                if (result < 0) {
                    throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
                }
                productInfo.setProductStock(result);
                repository.save(productInfo);
            } else {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
        }
    }
}
