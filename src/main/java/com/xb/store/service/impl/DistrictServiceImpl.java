package com.xb.store.service.impl;

import com.xb.store.entity.District;
import com.xb.store.mapper.DistrictMapper;
import com.xb.store.service.DistrictService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Resource
    private DistrictMapper districtMapper;
    @Override
    public List<District> getDistrictByParent(String parent) {
        List<District> districtList = districtMapper.selectByParent(parent);
        for(District district:districtList){
            district.setId(null);
            district.setParent(null);
        }
        return districtList;
    }

    @Override
    public String getNameByCode(String code) {
        String s = districtMapper.selectNameByCode(code);
        return s;
    }
}
