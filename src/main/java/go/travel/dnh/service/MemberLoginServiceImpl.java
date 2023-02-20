package go.travel.dnh.service;


import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.repository.MemberLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService {

    private final MemberLoginRepository memberLoginRepository;

    @Override
    public String login(MemberDTO memberDTO) {
        MemberDTO user = memberLoginRepository.findByMember(memberDTO);
        if (user != null) {
            return "success";
        }
        return "fail";
    }

    @Override
    public MemberDTO findById(MemberDTO memberDTO) {
        return memberLoginRepository.findByMember(memberDTO);
    }

    @Override
    public MemberDTO findById(String mem_id) {
        return memberLoginRepository.findById(mem_id);
    }

}
