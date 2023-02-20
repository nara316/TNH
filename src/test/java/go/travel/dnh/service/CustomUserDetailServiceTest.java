package go.travel.dnh.service;

import go.travel.dnh.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomUserDetailServiceTest {
    @Autowired
    UserMapper userMapper;
    @Test
    void loadUserByUsername() {
        String mem_id = "aaa";
        UserDetails user = userMapper.findById(mem_id);
        System.out.println("user.getUsername() = " + user.getUsername());
        System.out.println("user.getPassword() = " + user.getPassword());
        if(user==null) {
            throw new UsernameNotFoundException(mem_id);
        }
        System.out.println("CustomUserDetailsService 들어옴~~");
    }
}
