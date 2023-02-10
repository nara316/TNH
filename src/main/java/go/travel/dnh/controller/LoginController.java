package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.security.TokenInfo;
import go.travel.dnh.service.MemberLoginServiceImpl;
import go.travel.dnh.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final MemberLoginServiceImpl memberLoginService;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loginForm")
    public String loginForm(MemberDTO memberDTO) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("memberDTO") MemberDTO memberDTO, Model model) {
//        암호화 회원가입 된 이후 사용 가능
        String submitPwd = "";
        String encodePwd = "";
        String errormessage = "에러";
        MemberDTO loginUser = memberLoginService.findById(memberDTO);
        if (loginUser != null) {
            //로그인 유저가 있으면
//            submitPwd = bCryptPasswordEncoder.encode(memberDTO.getMem_pwd());
            submitPwd = (memberDTO.getMem_pwd());
//            encodePwd = bCryptPasswordEncoder.encode(loginUser.getMem_pwd());
            encodePwd = (loginUser.getMem_pwd());
            if (submitPwd.equals(encodePwd)) {
                //비밀번호 암호화 해서 비교 후 같으면 초기화 후 토큰 발생
                //true == bCryptPasswordEncoder.matches(submitPwd, encodePwd)
                loginUser.setMem_pwd("");
                TokenInfo tokenInfo = userService.jwtlogin(memberDTO.getMem_id(), memberDTO.getMem_pwd());
                System.out.println("tokenInfo = " + tokenInfo);
                return "index";
            } else {
                System.out.println("submitPwd = " + submitPwd);
                System.out.println("memberDTO.getMem_id() = " + memberDTO.getMem_id());
                System.out.println("encodePwd = " + encodePwd);
                System.out.println("loginUser.getMem_id() = " + loginUser.getMem_id());;
                return "login/loginForm";
            }
        }
        return errormessage;
//
//        if("success".equalsIgnoreCase(memberLoginService.login(memberDTO))){
//            TokenInfo tokenInfo = userService.jwtlogin(memberDTO.getMem_id(), memberDTO.getMem_pwd());
//            System.out.println("tokenInfo = " + tokenInfo);
//            return "index";
//        }
//        System.out.println("result = " + memberLoginService.login(memberDTO));
//        return "login/loginForm";
//    }
    }
}