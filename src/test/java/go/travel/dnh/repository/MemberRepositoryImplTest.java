package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDAO;
import go.travel.dnh.domain.member.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberLoginRepository memberLoginRepository;

    @Test
    void login() {
        //when
        MemberDTO testUser = new MemberDTO();
        testUser.setMem_id("aaa");
        testUser.setMem_pwd("155");
        //then
        String result = memberLoginRepository.findByMember(testUser);
        //given
        if(result.equals("success")) {
            System.out.println(testUser.getMem_id());
            System.out.println("로그인 성공");
        }             System.out.println("로그인 실패");

    }
}