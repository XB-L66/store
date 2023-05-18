package com.xb.store.service;

public interface OrderService {
    void createOrder(Integer aid,Integer[] cids,Integer uid,String username);
}
