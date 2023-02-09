package go.travel.dnh.service;

import go.travel.dnh.domain.RequestUser;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.repository.MemberLoginRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberLoginServiceImplTest {

    @Autowired
    MemberLoginRepository memberLoginRepository;
    @Test
    void login() {
        RequestUser ru = new RequestUser();
        ru.setMem_id("aaa");
        ru.setMem_pwd("111");

        String mem_id = ru.getMem_id();
        String mem_pwd = ru.getMem_pwd();
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMem_id(mem_id);
        memberDTO.setMem_pwd(mem_pwd);

        if(memberLoginRepository.findByMember(memberDTO)!=null) {
            System.out.println("로그인");
        } {
            System.out.println("실패");
        }
    }
}