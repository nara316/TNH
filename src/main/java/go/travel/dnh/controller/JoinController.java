package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.mapper.MemberJoinMapper;
import go.travel.dnh.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String join(MemberDTO memberDTO){

        return "";
    }
}
