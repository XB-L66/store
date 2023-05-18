package com.xb.store.service.impl;

import com.xb.store.entity.User;
import com.xb.store.exception.*;
import com.xb.store.mapper.UserMapper;
import com.xb.store.service.UserService;
import com.xb.store.utils.MD5Util;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    @Override
    public int reg(User user){
        User user1=userMapper.selectByUserName(user.getUsername());
        if(user1!=null){
            throw new UserNameDuplicatedException("用户名已存在!");
        }
        String oldpassword=user.getPassword();
        String salt= UUID.randomUUID().toString();
        String password= MD5Util.getMD5(oldpassword,salt);
        user.setPassword(password);
        user.setSalt(salt);

        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date=new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        int num = userMapper.insertUser(user);
        if(num==1){
            return 1;
        }else{
            throw new InsertException("插入异常！");
        }
    }

    @Override
    public User login(String username,String password) {
        User user = userMapper.selectByUserName(username);
        if(user==null){
            throw new UserNameNotFountException("用户名不存在！");
        }
        String newPassword=MD5Util.getMD5(password,user.getSalt());
        if(!newPassword.equals(user.getPassword())){
            throw new PassWordNotMatchException("密码不正确");
        }
        if(user.getIsDelete()==1){
            throw new UserNameNotFountException("用户不存在！");
        }
        User user2=new User();
        user2.setUid(user.getUid());
        user2.setUsername(user.getUsername());
        user2.setAvatar(user.getAvatar());
        return user2;
    }

    @Override
    public void editPassword(Integer uid,String username,String oldPassword, String newPassword) {
        User user = userMapper.selectByUid(uid);
        if(user==null||user.getIsDelete()==1){
            throw new UserNameNotFountException("用户名不存在！");
        }
        String md5Password=MD5Util.getMD5(oldPassword,user.getSalt());
        if(!md5Password.equals(user.getPassword())){
            throw new PassWordNotMatchException("原密码错误！");
        }
        String newMd5Password=MD5Util.getMD5(newPassword,user.getSalt());
        int num = userMapper.updatePasswordById(uid, newMd5Password, username, new Date());
        if(num!=1){
            throw new UpdateException("更新异常");
        }
    }

    @Override
    public void editInfo(Integer uid,String username,User user) {
        User result=userMapper.selectByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNameNotFountException("用户名不存在!");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        Date date=new Date();
        user.setModifiedTime(date);
        int num = userMapper.updateInfoById(user);
        if(num!=1){
            throw new UpdateException("修改错误!");
        }
    }

    @Override
    public User getById(Integer uid) {
        User user = userMapper.selectByUid(uid);
        if(user==null||user.getIsDelete()==1){
            throw new UserNameNotFountException("用户名不存在!");
        }
        User user1=new User();
        user1.setUsername(user.getUsername());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        user1.setGender(user.getGender());
        return user1;
    }

    @Override
    public void editAvatar(Integer uid, String avatar,String username) {
        User user = userMapper.selectByUid(uid);
        if(user==null||user.getIsDelete()==1){
            throw new UserNameNotFountException("用户名不存在！");
        }
        int num = userMapper.updateAvatar(uid, avatar, username, new Date());
        if(num!=1){
            throw new UpdateException("上传错误！");
        }
    }
}
