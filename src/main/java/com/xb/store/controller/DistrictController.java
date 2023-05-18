package com.xb.store.controller;

import com.xb.store.entity.District;
import com.xb.store.service.DistrictService;
import com.xb.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseController{
    @Autowired
    private DistrictService districtService;
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getDistrictByParent(String parent){
        List<District> districtList = districtService.getDistrictByParent(parent);;
        JsonResult<List<District>> jsonResult=new JsonResult<>();
        jsonResult.setState(200);
        jsonResult.setMessage("查询成功!");
        jsonResult.setData(districtList);
        return jsonResult;
    }
}
