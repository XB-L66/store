package com.xb.store.service.impl;

import com.xb.store.entity.Address;
import com.xb.store.entity.Order;
import com.xb.store.entity.OrderItem;
import com.xb.store.exception.InsertException;
import com.xb.store.mapper.OrderMapper;
import com.xb.store.service.AddressService;
import com.xb.store.service.CartService;
import com.xb.store.service.OrderService;
import com.xb.store.vo.CartVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;
    @Override
    public void createOrder(Integer aid, Integer[] cids, Integer uid, String username) {
        Address addressByAid = addressService.getAddressByAid(aid, uid);
        List<CartVo> cartByCids = cartService.getCartByCids(uid,cids);
        Long totalPrice= 0L;
        for(CartVo cartVo:cartByCids){
            totalPrice+=cartVo.getRealPrice();
        }
        Order order=new Order();
        order.setUid(uid);
        order.setAid(aid);
        order.setRecvName(addressByAid.getName());
        order.setRecvPhone(addressByAid.getPhone());
        order.setRecvProvince(addressByAid.getProvinceName());
        order.setRecvCity(addressByAid.getCityName());
        order.setRecvArea(addressByAid.getAreaName());
        order.setRecvAddress(addressByAid.getAddress());
        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        Date date=new Date();
        order.setOrderTime(date);
        order.setCreatedUser(username);
        order.setModifiedUser(username);
        order.setCreatedTime(date);
        order.setModifiedTime(date);
        Integer num = orderMapper.insertOrder(order);
        if(num!=1){
            throw new InsertException("订单生成失败！");
        }

        for(CartVo cartVo:cartByCids){
            OrderItem orderItem=new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(date);
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(date);
            orderItem.setNum(cartVo.getNum());
            orderItem.setPrice(cartVo.getRealPrice());
            orderItem.setImage(cartVo.getImage());
            orderItem.setTitle(cartVo.getTitle());
            orderItem.setPid(cartVo.getPid());
            num=orderMapper.insertOrderItem(orderItem);
            if(num!=1){
                throw new InsertException("订单详细信息生成失败！");
            }
        }


    }
}
