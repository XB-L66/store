package com.xb.store.service;

import com.xb.store.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    void addAddress(Integer uid,String username,Address address);
    List<Address> getAllAddress(Integer uid);
    void updateAddressIsDefault(Integer aid,Integer uid,String username);
    void deleteAddressByAid(Integer aid,Integer uid);
    Address getAddressByAid(Integer aid,Integer uid);
}
