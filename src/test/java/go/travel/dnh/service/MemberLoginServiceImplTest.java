package go.travel.dnh.service;
import static org.junit.jupiter.api.Assertions.*;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.repository.MemberLoginRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest

class MemberLoginServiceImplTest {

    @Autowired
    MemberLoginRepository memberLoginRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    void login() {
        String text = "Iwanttogohome";
        System.out.println("text = " + text);
        String encoPwd = passwordEncoder.encode(text);
        System.out.println("encoPwd = " + encoPwd);

        assertTrue(passwordEncoder.matches(text, encoPwd));

    }
}