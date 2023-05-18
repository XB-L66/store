package com.xb.store.mapper;

import com.xb.store.entity.User;

import javax.xml.crypto.Data;
import java.util.Date;

public interface UserMapper {
    int insertUser(User user);
    User selectByUserName(String userName);
    int updatePasswordById(Integer uid, String password, String modifiedUser, Date modifiedTime);
    User selectByUid(Integer uid);
    int updateInfoById(User user);
    int updateAvatar(Integer uid,String avatar,String modifiedUser,Date modifiedTime);
}
