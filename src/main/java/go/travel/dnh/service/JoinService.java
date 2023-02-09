package go.travel.dnh.service;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.mapper.MemberJoinMapper;
import go.travel.dnh.repository.MemberJoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberJoinRepository memberJoinRepository;

    public void joinMember(MemberDTO memberDTO){
        memberJoinRepository.joinMember(memberDTO);
    }

}
