package go.travel.dnh.service;


import go.travel.dnh.domain.User.updateForm;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.repository.MemberLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService {

    private final MemberLoginRepository memberLoginRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
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

    public void updateUser(updateForm updateForm) {
        MemberDTO memberDTO = new MemberDTO();
        String encodePWd = bCryptPasswordEncoder.encode(updateForm.getMem_pwd());
        memberDTO.setMem_id(updateForm.getMem_id());
        memberDTO.setMem_pwd(encodePWd);
        memberDTO.setMem_phone(updateForm.getMem_phone());
        memberLoginRepository.update(memberDTO);
    }
}
