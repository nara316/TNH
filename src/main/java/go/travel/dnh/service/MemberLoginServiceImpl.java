package go.travel.dnh.service;


import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.User.WithdrawalForm;
import go.travel.dnh.domain.User.updateForm;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.mapper.SocialUserMapper;
import go.travel.dnh.repository.MemberLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService {

    private final MemberLoginRepository memberLoginRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SocialUserMapper socialUserMapper;
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

    @Override
    public void withdrawal(int mno, WithdrawalForm withdrawalForm) {
        memberLoginRepository.andInsert(withdrawalForm);
        memberLoginRepository.delete(mno);
    }
    @Transactional
    public void deleteMember(Map<String, Object> deleteUser) {
        String id = deleteUser.get("id").toString();
        String pwd = deleteUser.get("pwd").toString();
        MemberDTO user = findById(id);
        WithdrawalForm withdrawalForm = new WithdrawalForm();
        //아이디 비밀번호 확인 후
        //소셜로그인 유저라면 소셜 로그인도 지워야함
        if(socialUserMapper.findByEmail(id)!=null) {
            int sno = socialUserMapper.findByEmail(id).getSno();
            socialUserMapper.deleteSocialUser(sno);
            int smno = memberLoginRepository.findById(id).getMno();
            withdrawalForm.setWithdrawal_id(id);
            withdrawalForm.setWithdrawal_mno(smno);
            withdrawal(smno , withdrawalForm);
        } else {
            if (user.getMem_id().equals(id) && bCryptPasswordEncoder.matches(pwd,user.getMem_pwd())) {
                int mno = user.getMno();
                withdrawalForm.setWithdrawal_id(id);
                withdrawalForm.setWithdrawal_mno(mno);
                //삭제 테이블에 값 넣고
                //기존 DB삭제
                withdrawal(mno,withdrawalForm);
            }

        }


    }

}
