package com.xb.store.service.impl;

import com.xb.store.entity.Cart;
import com.xb.store.entity.Product;
import com.xb.store.exception.DeleteException;
import com.xb.store.exception.InsertException;
import com.xb.store.exception.UpdateException;
import com.xb.store.mapper.CartMapper;
import com.xb.store.mapper.ProductMapper;
import com.xb.store.service.CartService;
import com.xb.store.vo.CartVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private ProductMapper productMapper;
    @Override
    public void addCartByUid(Integer uid, String username, Integer pid,Integer amount) {
        Cart  result = cartMapper.selectCartByUidAndPid(uid, pid);
        if(result==null){
            Cart cart=new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            Product product = productMapper.selectProductById(pid);
            cart.setPrice(product.getPrice());
            cart.setCreatedUser(username);
            cart.setModifiedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedTime(new Date());
            int num = cartMapper.insertCartUid(cart);
            if(num!=1){
                throw new InsertException("添加购物车失败！");
            }
        }else{
            Integer num= result.getNum()+amount;
            int count = cartMapper.updateNumByCid(result.getCid(), num, username, new Date());
            if(count!=1){
                throw new UpdateException("购物车添加失败！");
            }
        }

    }

    @Override
    public List<CartVo> getAllCart(Integer uid) {
        List<CartVo> cartVos = cartMapper.selectAllCart(uid);
        return cartVos;
    }

    @Override
    public int addCartByCid(Integer cid) {
        Cart cart = cartMapper.selectCartByCid(cid);
        int count=cart.getNum()+1;
        int num = cartMapper.updateCartNumByCid(cid, count);
        if(num!=1){
            throw new UpdateException("数量添加失败！");
        }
        return count;
    }

    @Override
    public int reduceCartNumByCid(Integer cid) {
        Cart cart = cartMapper.selectCartByCid(cid);
        if(cart.getNum()==1){
            return 1;
        }
        int count=cart.getNum()-1;
        int num = cartMapper.updateCartNumByCid(cid, count);
        if(num!=1){
            throw new UpdateException("数量添加失败！");
        }
        return count;
    }

    @Override
    public void deleteCartByCid(Integer cid) {
        int num = cartMapper.deleteCartByCid(cid);
        if(num!=1){
            throw new DeleteException("购物车删除失败！");
        }
    }

    @Override
    public List<CartVo> getCartByCids(Integer uid,Integer[] cids) {
        List<CartVo> cartVos = cartMapper.selectCartByCids(cids);
        for(CartVo cartVo:cartVos){
            if (!cartVo.getUid().equals(uid)) {
                cartVos.remove(cartVo);
            }
        }
        return cartVos;
    }
}
