package go.travel.dnh.service;

import go.travel.dnh.domain.member.MemberDTO;


public interface MemberLoginService {
    public String login(MemberDTO memberDTO);
    public MemberDTO findById(MemberDTO memberDTO);

    MemberDTO findById(String mem_id);
}
