package go.travel.dnh.service;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.repository.MemberJoinRepository;
import go.travel.dnh.validation.MemberjoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService{

    private final MemberJoinRepository memberJoinRepository;

    public void joinMember(MemberjoinForm form){

       //성공로직
       MemberDTO memberDTO = new MemberDTO();
       memberDTO.setMem_id(form.getMem_id());
       memberDTO.setMem_pwd(form.getMem_pwd());
       memberDTO.setMem_name(form.getMem_name());
       memberDTO.setMem_phone(form.getMem_phone());
       memberDTO.setMem_gender(form.getMem_gender());
       memberDTO.setMem_birth(form.getMem_birth());

        memberJoinRepository.joinMember(memberDTO);
    }

    public int findMember(String mem_id){
        return memberJoinRepository.findMember(mem_id);
    }
}
