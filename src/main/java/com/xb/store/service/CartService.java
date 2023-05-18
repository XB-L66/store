package com.xb.store.service;

import com.xb.store.entity.Cart;
import com.xb.store.vo.CartVo;

import java.util.List;

public interface CartService {
    void addCartByUid(Integer uid,String username,Integer pid,Integer amount);
    List<CartVo> getAllCart(Integer uid);
    int addCartByCid(Integer cid);
    int reduceCartNumByCid(Integer cid);
    void deleteCartByCid(Integer cid);
    List<CartVo> getCartByCids(Integer uid,Integer[] cids);
}
