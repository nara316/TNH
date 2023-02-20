package go.travel.dnh.mapper;

import go.travel.dnh.domain.User.LoginUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;


@SpringBootTest
class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void readUserTest() {
        String text = "1234";
        System.out.println("text = " + text);
        String encoPwd = bCryptPasswordEncoder.encode(text);
        System.out.println("encoPwd = " + encoPwd);
        LoginUser user = userMapper.findById("ddd");
        System.out.println("user.getMem_pwd() = " + user.getMem_pwd());
        assertTrue(bCryptPasswordEncoder.matches(text,user.getPassword()));
    }

    @Test
    public void readAuthorityTest() {


//
//        authorities= userMapper.readAuthority("abc");
//        assertThat(authorities, hasItem("USER"));
    }
    @Test
    public void findByIdFromMnoTest() {
        int mno = userMapper.findByMnoFromId("aaa");

        assertEquals("1", "1");
    }
}