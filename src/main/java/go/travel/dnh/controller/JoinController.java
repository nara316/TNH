package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.JoinService;
import go.travel.dnh.validation.MemberjoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String join(@ModelAttribute("memberDTO") MemberjoinForm form){

        //성공로직
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMem_id(form.getMem_id());
        memberDTO.setMem_pwd(form.getMem_pwd());
        memberDTO.setMem_name(form.getMem_name());
        memberDTO.setMem_phone(form.getMem_phone());
        memberDTO.setMem_gender(form.getMem_gender());
        memberDTO.setMem_birth(form.getMem_birth());
        joinService.joinMember(memberDTO);
        return "member/loginForm";
    }
}
