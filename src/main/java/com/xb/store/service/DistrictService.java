package com.xb.store.service;

import com.xb.store.entity.District;

import java.util.List;

public interface DistrictService {
    List<District> getDistrictByParent(String parent);
    String getNameByCode(String code);
}
