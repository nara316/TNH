package go.travel.dnh.mapper;

import go.travel.dnh.domain.User.SocialUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SocialUserMapperTest {

    @Autowired
    SocialUserMapper socialUserMapper;

    @Test
    void findByEmail() {
        String id = "asdf";
        String su = socialUserMapper.findByEmail("asdfg").getId();
        if(id.equals(su)) {
            System.out.println("같음");
        };

    }

    @Test
    void saveSocialUser() {
    }
}