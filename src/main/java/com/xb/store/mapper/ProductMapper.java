package com.xb.store.mapper;

import com.xb.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> selectAllProduct();
    Product selectProductById(Integer id);
}
