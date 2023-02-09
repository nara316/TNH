package go.travel.dnh.service;

import go.travel.dnh.domain.RequestUser;
import go.travel.dnh.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;


public interface MemberLoginService {
    String login(MemberDTO memberDTO);
}
