package com.xb.store.service.impl;

import com.xb.store.entity.Address;
import com.xb.store.exception.*;
import com.xb.store.mapper.AddressMapper;
import com.xb.store.service.AddressService;
import com.xb.store.service.DistrictService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Autowired
    private DistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer Count;
    @Override
    public void addAddress(Integer uid,String username,Address address) {
        int num = addressMapper.countById(uid);
        Integer isDefault=num==0?0:1;
        if(num>=Count){
            throw new AddressCountLimitException("地址总数超出限制！");
        }
        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));
        address.setIsDefault(isDefault);
        address.setUid(uid);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        Date date=new Date();
        address.setModifiedTime(date);
        address.setCreatedTime(date);
        int num1 = addressMapper.insertAddress(address);
        if(num1!=1){
            throw new InsertException("地址添加异常！");
        }
    }

    @Override
    public List<Address> getAllAddress(Integer uid) {
        List<Address> addressesList = addressMapper.selectAllAddress(uid);
        for(Address address:addressesList){
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setZip(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return addressesList;
    }

    @Override
    public void updateAddressIsDefault(Integer aid,Integer uid,String username) {
        Address address = addressMapper.selectAddressByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("地址不存在！");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问地址！");
        }
        int num = addressMapper.initIsDefault(uid);
        if(num<1){
            throw new UpdateException("更新异常！");
        }
       num= addressMapper.updateAddressIsDefault(aid,username,new Date());
        if(num!=1){
            throw new UpdateException("更新异常！");
        }
    }

    @Override
    public void deleteAddressByAid(Integer aid, Integer uid) {
        Address address = addressMapper.selectAddressByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("地址不存在！");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问异常！");
        }
        int num = addressMapper.deleteAddressByAid(aid);
        if(num!=1){
            throw new DeleteException("删除错误！");
        }
    }

    @Override
    public Address getAddressByAid(Integer aid,Integer uid) {

        Address address = addressMapper.selectAddressByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("地址不存在！");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问！");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setModifiedUser(null);
        address.setCreatedTime(null);
        address.setModifiedTime(null);
        return address;
    }
}
