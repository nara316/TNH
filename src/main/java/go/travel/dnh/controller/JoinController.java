package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.JoinService;
import go.travel.dnh.validation.MemberjoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinForm(MemberDTO memberDTO){
        return "member/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("memberDTO") MemberjoinForm form, BindingResult
            bindingResult, RedirectAttributes redirectAttributes){

        //인증에 실패했을 때
        if(bindingResult.hasErrors()){
            return "member/joinForm";
        }

        //성공했을 때
        joinService.joinMember(form);
        return "member/loginForm";
    }
}
