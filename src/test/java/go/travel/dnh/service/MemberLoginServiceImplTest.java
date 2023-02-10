package go.travel.dnh.service;
import static org.junit.jupiter.api.Assertions.*;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.repository.MemberLoginRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest

class MemberLoginServiceImplTest {

    @Autowired
    MemberLoginRepository memberLoginRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    void login() {
        String text = "1234";
        System.out.println("text = " + text);
        String encoPwd = bCryptPasswordEncoder.encode(text);
        System.out.println("encoPwd = " + encoPwd);

        assertTrue(bCryptPasswordEncoder.matches(text, encoPwd));

    }
}