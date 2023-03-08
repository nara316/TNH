package go.travel.dnh.controller;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.User.updateForm;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.JoinService;
import go.travel.dnh.service.MemberLoginServiceImpl;
import go.travel.dnh.validation.MemberjoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping( "/")
@RequiredArgsConstructor
public class MemberInfoController {

    private final MemberLoginServiceImpl memberLoginService;
    private final JoinService joinService;


    @GetMapping("/mypage")
    public String MyPage(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO findUser = memberLoginService.findById(username);
//        MemberDTO findUser = memberLoginService.findMember(loginUser,authentication);
        model.addAttribute("memberDTO", findUser);
        return "login/mypage";
    }


    @PostMapping("/mypage")
    public String upDate(@Validated @ModelAttribute("memberDTO") updateForm updateForm, BindingResult bindingResult, Model model)  {
        if(bindingResult.hasErrors()) {
            return "login/mypage";
        } else {
            //성공했을 때
            memberLoginService.updateUser(updateForm);
            model.addAttribute("message", "회원 정보 수정이 완료되었습니다.");
            model.addAttribute("searchURL", "/mypage");
            return "message";
        }
    }
    @PostMapping("/mypage/withdrawal")
    public @ResponseBody boolean deleteMember(@RequestBody Map<String, Object> deleteUser, HttpServletRequest request, HttpServletResponse response)  {
        memberLoginService.deleteMember(deleteUser);
        memberLoginService.logout(request, response);
        return true;
    }

}
