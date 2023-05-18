package com.xb.store.mapper;

import com.xb.store.entity.User;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;
    @Test
    void testInsert() {
        User user=new User();
        user.setUsername("admin");
        user.setPassword("123");
        int num = userMapper.insertUser(user);
        System.out.println(num);
    }
    @Test
    void testSelect(){
        User user=userMapper.selectByUserName("admin");
        System.out.println(user);
    }
}
