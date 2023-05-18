package com.xb.store.controller;

import com.xb.store.service.CartService;
import com.xb.store.utils.JsonResult;
import com.xb.store.vo.CartVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController{
    @Autowired
    private CartService cartService;

    @RequestMapping("/add_to_cart")
    public JsonResult<Void> addCart(HttpSession session,Integer pid,Integer amount){
        JsonResult<Void> jsonResult=new JsonResult<>();
        Integer uid=getUidFromSession(session);
        String username=getUnameFromSession(session);
        cartService.addCartByUid(uid,username,pid,amount);
        jsonResult.setState(200);
        return jsonResult;
    }
    @RequestMapping({"/",""})
    public JsonResult<List<CartVo>> getAllCartVo(HttpSession session){
        Integer uid=getUidFromSession(session);
        List<CartVo> allCart = cartService.getAllCart(uid);
        JsonResult<List<CartVo>> jsonResult=new JsonResult<>();
        jsonResult.setState(200);
        jsonResult.setData(allCart);
        return jsonResult;
    }
    @RequestMapping("/{cid}/num/add")
    public JsonResult<Integer> addCartNum(@PathVariable("cid") Integer cid){
        JsonResult<Integer> jsonResult=new JsonResult<>();
        int num = cartService.addCartByCid(cid);
        jsonResult.setState(200);
        jsonResult.setData(num);
        return jsonResult;
    }
    @RequestMapping("/{cid}/num/reduce")
    public JsonResult<Integer> reduceCartNum(@PathVariable("cid") Integer cid){
        JsonResult<Integer> jsonResult=new JsonResult<>();
        int num = cartService.reduceCartNumByCid(cid);
        jsonResult.setState(200);
        jsonResult.setData(num);
        return jsonResult;
    }
    @RequestMapping("/{cid}/num/delete")
    public JsonResult<Void> deleteCartNum(@PathVariable("cid") Integer cid){
        JsonResult<Void> jsonResult=new JsonResult<>();
        cartService.deleteCartByCid(cid);
        jsonResult.setState(200);
        return jsonResult;
    }
    @RequestMapping("/list")
    public JsonResult<List<CartVo>> getCartByCid(Integer[] cids,HttpSession session){
        Integer uid=getUidFromSession(session);
        JsonResult<List<CartVo>> jsonResult=new JsonResult<>();
        List<CartVo> cartByCids = cartService.getCartByCids(uid,cids);
        jsonResult.setState(200);
        jsonResult.setData(cartByCids);
        return jsonResult;
    }
}
