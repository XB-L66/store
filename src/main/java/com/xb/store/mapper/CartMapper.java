package com.xb.store.mapper;

import com.xb.store.entity.Cart;
import com.xb.store.vo.CartVo;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    int insertCartUid(Cart car);
    int updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);
    Cart selectCartByUidAndPid(Integer uid,Integer pid);
    List<CartVo> selectAllCart(Integer uid);
    Cart selectCartByCid(Integer cid);
    int updateCartNumByCid(Integer cid,Integer num);
    int deleteCartByCid(Integer cid);
    List<CartVo> selectCartByCids(Integer[] cids);
}
