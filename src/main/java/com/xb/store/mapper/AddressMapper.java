package com.xb.store.mapper;

import com.xb.store.entity.Address;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    int insertAddress(Address address);
    int countById(Integer uid);
    List<Address> selectAllAddress(Integer uid);
    int initIsDefault(Integer uid);
    int updateAddressIsDefault(Integer aid, String modifiedUser, Date modifiedTime);
    Address selectAddressByAid(Integer aid);
    int deleteAddressByAid(Integer aid);
}
