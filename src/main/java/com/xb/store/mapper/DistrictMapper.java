package com.xb.store.mapper;

import com.xb.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    List<District> selectByParent(String parent);
    String selectNameByCode(String code);
}
