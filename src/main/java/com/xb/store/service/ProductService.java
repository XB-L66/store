package com.xb.store.service;

import com.xb.store.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product getProductById(Integer id);
}
