package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.MemberLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final MemberLoginServiceImpl memberLoginService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/loginForm")
    public String loginForm(MemberDTO memberDTO)  {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("memberDTO")MemberDTO memberDTO, Model model)  {
//        암호화 회원가입 된 이후 사용 가능
//        String submitPwd = "";
//        String encodePwd = "";
//
//        MemberDTO loginUser = memberLoginService.findById(memberDTO);
//        if (loginUser != null) {
//            submitPwd = memberDTO.getMem_pwd();
//            encodePwd = loginUser.getMem_pwd();
//            if(true == passwordEncoder.matches(submitPwd,encodePwd)) {
//                loginUser.setMem_pwd("");
//                return "index";
//            } else {
//                return "login/loginForm";
//            }
//        }

        if("success".equalsIgnoreCase(memberLoginService.login(memberDTO))){
            return "index";
        }
        System.out.println("result = " + memberLoginService.login(memberDTO));
        return "login/loginForm";
    }
}
