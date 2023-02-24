package go.travel.dnh.service;


import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.User.updateForm;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.repository.MemberLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void updateUser(updateForm updateForm) {
        MemberDTO memberDTO = new MemberDTO();
        String encodePWd = bCryptPasswordEncoder.encode(updateForm.getMem_pwd());
        memberDTO.setMem_id(updateForm.getMem_id());
        memberDTO.setMem_pwd(encodePWd);
        memberDTO.setMem_phone(updateForm.getMem_phone());
        memberLoginRepository.update(memberDTO);
    }

    /*시큐리티로 mno찾기*/
    public MemberDTO findMember(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication){
        MemberDTO findUser;
        if (loginUser == null) {
            UserDetails userDetails=(UserDetails) authentication.getPrincipal();
            findUser = findById(userDetails.getUsername());
        } else {
            findUser = findById(loginUser.getMem_id());
        }

        return findUser;
    }
}
