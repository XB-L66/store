package com.xb.store.service;

import com.xb.store.entity.User;
import com.xb.store.exception.PassWordNotMatchException;
import com.xb.store.exception.UserNameNotFountException;
import com.xb.store.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void testReg(){
        User user=new User();
        user.setUsername("admin");
        user.setPassword("123456");
        int reg = userService.reg(user);
        System.out.println(reg);
    }
    @Test
    void login(){
        try {
            User admin = userService.login("admin", "123456");
            System.out.println(admin);
        } catch (UserNameNotFountException e) {
            System.out.println(e.getMessage());
        }catch (PassWordNotMatchException e){
            System.out.println(e.getMessage());
        }
    }
}
