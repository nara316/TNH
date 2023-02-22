package go.travel.dnh.service;

import go.travel.dnh.domain.member.MemberDTO;


public interface MemberLoginService {
    String login(MemberDTO memberDTO);
    MemberDTO findById(MemberDTO memberDTO);

    MemberDTO findById(String mem_id);


}
