package com.xb.store.service.impl;

import com.xb.store.entity.Product;
import com.xb.store.exception.ProductNotFoundException;
import com.xb.store.mapper.ProductMapper;
import com.xb.store.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Override
    public List<Product> getAllProduct() {
        List<Product> products = productMapper.selectAllProduct();
        return products;
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = productMapper.selectProductById(id);
        if(product==null){
            throw new ProductNotFoundException("商品不存在！");
        }
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }
}
