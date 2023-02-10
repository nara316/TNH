package go.travel.dnh.mapper;

import go.travel.dnh.domain.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;


@SpringBootTest
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void readUserTest() {
        User user = userMapper.readUser(Integer.parseInt("1"));

        assertEquals("서현",user.getName());
    }

    @Test
    public void readAuthorityTest() {
        List<String> authorities = userMapper.readAuthority(Integer.parseInt("1"));
        assertEquals(authorities, hasItem("USER"));
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