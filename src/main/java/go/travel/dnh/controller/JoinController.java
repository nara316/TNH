package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.MemberJoinService;
import go.travel.dnh.validation.MemberjoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class JoinController {

    private final MemberJoinService memberJoinService;

    @GetMapping("/joinForm")
    public String joinForm(MemberDTO memberDTO) {
        return "member/joinForm";
    }

    @PostMapping("/joinForm")
    public String join(@Validated @ModelAttribute("memberDTO") MemberjoinForm form, BindingResult bindingResult) {

        //인증에 실패했을 때
        if (bindingResult.hasErrors()) {
            return "member/joinForm";
        }

        //성공했을 때 로그인폼으로!
        memberJoinService.joinMember(form);
        return "login/loginForm";
    }

    /*아이디 중복 검사*/
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(String mem_id) {
        int cnt = memberJoinService.findMember(mem_id);
        return cnt;
    }

    /*아이디 인증 메일 전송*/
    @PostMapping("/mailCheck")
    @ResponseBody
    public boolean mailCheck(String mem_id) {

        memberJoinService.Sendmail(mem_id);
        return true;
    }

    /*이메일 인증번호 확인하는 메서드*/
    @PostMapping("/emailNumCheck")
    @ResponseBody
    public int emailNumCheck(String checkInput){
        return memberJoinService.mailNumCheck(checkInput);
    }

    /*전화번호 중복 확인 메서드*/
    @PostMapping("/mobileCheck")
    @ResponseBody
    public int moblieCheck(int mem_phone) {
        int pnt = memberJoinService.findMemberbyPhone(mem_phone);
        return pnt;
    }

    @GetMapping("/pwdFind")
    public String pwdFind(String mem_id) {

        return "member/pwdFindForm";
    }

    /*패스워드 초기화 메서드*/
    @PostMapping("/pwdReset")
    @ResponseBody
    public String pwdFindEmail(String mem_id){

        String randomPwd = memberJoinService.sendPwdMail(mem_id);

        return randomPwd;
    }
}
