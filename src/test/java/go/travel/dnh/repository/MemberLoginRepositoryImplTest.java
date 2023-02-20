package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberLoginRepositoryImplTest {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
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
    
    @Test
    void findUserFromToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZGQiLCJhdXRoIjoiUk9MRV9bVVNFUl0iLCJleHAiOjE2NzY1NTMzNzZ9.WFG4HStvxIkGtaa7ez8Ar1L2j_nvHhwEvkQOHEoK-T4";
        Claims claims = jwtTokenProvider.parseClaims(token);
        System.out.println("claims.getSubject() = " + claims.getSubject());
        System.out.println("claims.getId() = " + claims.getId());
        
    }
}