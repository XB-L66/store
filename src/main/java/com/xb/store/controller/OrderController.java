package com.xb.store.controller;

import com.xb.store.service.OrderService;
import com.xb.store.utils.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public JsonResult<Void> createOrder(Integer aid, Integer[] cids, HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUnameFromSession(session);
        JsonResult<Void> jsonResult=new JsonResult<>();
        orderService.createOrder(aid,cids,uid,username);
        jsonResult.setState(200);
        return jsonResult;
    }

}
