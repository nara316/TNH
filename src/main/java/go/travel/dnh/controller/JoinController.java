package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.JoinService;
import go.travel.dnh.validation.MemberjoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinForm(MemberDTO memberDTO) {
        return "member/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("memberDTO") MemberjoinForm form, BindingResult
            bindingResult, RedirectAttributes redirectAttributes) {

        //인증에 실패했을 때
        if (bindingResult.hasErrors()) {
            return "member/joinForm";
        }

        //성공했을 때
        joinService.joinMember(form);
        return "member/loginForm";
    }

    // 아이디 중복 검사
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(String mem_id) {
        int cnt = joinService.findMember(mem_id);
        return cnt;
    }

    //아이디 인증 메일 전송
    @PostMapping("/mailCheck")
    @ResponseBody
    public boolean mailCheck(String mem_id) {

        joinService.Sendmail(mem_id);
        return true;
    }

    //이메일 인증번호 확인하는 메서드
    @PostMapping("/emailNumCheck")
    @ResponseBody
    public int emailNumCheck(String checkInput){
        return joinService.mailNumCheck(checkInput);
    }
}
