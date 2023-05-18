package com.xb.store.controller;

import com.xb.store.entity.Address;
import com.xb.store.service.AddressService;
import com.xb.store.utils.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/addresses")
@RestController
public class AddressController extends BaseController{
    @Autowired
    private AddressService addressService;
    @RequestMapping("/add_new_address")
    public JsonResult<Void> addAddress(Address address,HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUnameFromSession(session);
        addressService.addAddress(uid,username,address);
        JsonResult<Void> jsonResult=new JsonResult<>();
        jsonResult.setState(200);
        jsonResult.setMessage("添加地址成功！");
        return jsonResult;
    }
    @RequestMapping({"/",""})
    public JsonResult<List<Address>> getAllAddress(HttpSession session){
        Integer uid=getUidFromSession(session);
        JsonResult<List<Address>> jsonResult=new JsonResult<>();
        List<Address> allAddress = addressService.getAllAddress(uid);
        jsonResult.setState(200);
        jsonResult.setMessage("查询成功！");
        jsonResult.setData(allAddress);
        return jsonResult;
    }
    @RequestMapping("/{aid}/set_default")
    public JsonResult<Void> updateAddressIsDefault(@PathVariable("aid") Integer aid,HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUnameFromSession(session);
        JsonResult<Void> jsonResult=new JsonResult<>();
        addressService.updateAddressIsDefault(aid,uid,username);
        jsonResult.setState(200);
        jsonResult.setMessage("默认地址修改成功！");
        return jsonResult;
    }
    @RequestMapping("/{aid}/delete")
    public JsonResult<Void> deleteAddressByAid(@PathVariable("aid") Integer aid,HttpSession session){
        Integer uid=getUidFromSession(session);
        JsonResult<Void> jsonResult=new JsonResult<>();
        addressService.deleteAddressByAid(aid,uid);
        jsonResult.setState(200);
        jsonResult.setMessage("删除成功！");
        return jsonResult;
    }
}
