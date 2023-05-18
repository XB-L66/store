package com.xb.store.controller;

import com.xb.store.entity.Product;
import com.xb.store.service.ProductService;
import com.xb.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotProductList(){
        JsonResult<List<Product>> jsonResult=new JsonResult<>();
        List<Product> allProduct = productService.getAllProduct();
        jsonResult.setState(200);
        jsonResult.setMessage("查询成功");
        jsonResult.setData(allProduct);
        return jsonResult;
    }

    @RequestMapping("/{id}/details")
    public JsonResult<Product> getProductById(@PathVariable("id") Integer id){
        JsonResult<Product> jsonResult=new JsonResult<>();
        Product product = productService.getProductById(id);
        jsonResult.setState(200);
        jsonResult.setData(product);
        return jsonResult;
    }
}
