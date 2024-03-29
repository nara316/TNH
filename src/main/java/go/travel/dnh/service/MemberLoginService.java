package go.travel.dnh.service;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.User.WithdrawalForm;
import go.travel.dnh.domain.member.MemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface MemberLoginService {
    String login(MemberDTO memberDTO);
    MemberDTO findById(MemberDTO memberDTO);

    MemberDTO findById(String mem_id);

    MemberDTO findMember(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication);

    void withdrawal(int mno, WithdrawalForm withdrawalForm);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
