package com.xb.store.service;

import com.xb.store.entity.User;


public interface UserService {
    int reg(User user);
    User login(String username,String password);
    void editPassword(Integer uid,String username,String oldPassword,String password);
    void editInfo(Integer uid,String username,User user);
    User getById(Integer uid);
    void editAvatar(Integer uid, String avatar,String username);
}
