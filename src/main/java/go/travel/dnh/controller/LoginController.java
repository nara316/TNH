package go.travel.dnh.controller;

import go.travel.dnh.domain.RequestUser;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.MemberLoginService;
import go.travel.dnh.service.MemberLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final MemberLoginServiceImpl memberLoginService;

    @GetMapping("/loginForm")
    public String loginForm(MemberDTO memberDTO)  {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("memberDTO")MemberDTO memberDTO)  {
//        System.out.println(memberDTO.getMem_id());
//        System.out.println(memberDTO.getMem_pwd());

        if("success".equalsIgnoreCase(memberLoginService.login(memberDTO))){
//            System.out.println("로긴 성공");
            return "index";
        }
//
//        System.out.println("로긴 실패");
        System.out.println("result = " + memberLoginService.login(memberDTO));
        return "login/loginForm";
    }
}
