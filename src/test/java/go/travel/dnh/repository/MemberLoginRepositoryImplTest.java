package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberLoginRepositoryImplTest {

    @Autowired
    MemberLoginRepository memberLoginRepository;


    @Test
    void findByMember() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMem_id("aaa");
        memberDTO.setMem_pwd("111");
        MemberDTO loginUser = memberLoginRepository.findByMember(memberDTO);
        if(loginUser!=null) {
            System.out.println("성공");
        } else {
        System.out.println("실패");}
    }
}