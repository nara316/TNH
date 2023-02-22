package go.travel.dnh.controller;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.User.updateForm;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.mapper.UserMapper;
import go.travel.dnh.repository.MemberLoginRepository;
import go.travel.dnh.service.JoinService;
import go.travel.dnh.service.MemberLoginServiceImpl;
import go.travel.dnh.validation.MemberjoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping( "/login")
@RequiredArgsConstructor
public class LoginController {
    private final JoinService joinService;
    private final MemberLoginRepository memberLoginRepository;
    private final MemberLoginServiceImpl memberLoginService;


    @GetMapping("/loginForm")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,
                            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login/loginForm";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        //로그인시 인증되어 SecurityContextHolder에 저장된 유저를 꺼내서 로그아웃시킴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

        return "redirect:/";
    }
    @GetMapping("/oauth/update/{email}")
    public String oauthJoinAndUpdate(@PathVariable("email") String email, Model model, HttpSession httpSession) {
        email = httpSession.getAttribute("socialEmail").toString();
        model.addAttribute("memberDTO" , new MemberDTO());
        return "login/oauthjoin";
    }
    @PostMapping("/oauth/update/{email}")
    public String update(@Validated @ModelAttribute("memberDTO") MemberjoinForm form, BindingResult
            bindingResult ,@PathVariable("email") String email, HttpSession httpSession) {
        email = httpSession.getAttribute("socialEmail").toString();

        if (bindingResult.hasErrors()) {
            return "login/oauthjoin";
        }
        //성공했을 때
        joinService.joinMember(form);
        return "redirect:/";
    }
    @GetMapping("/mypage")
    public String MyPage(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication, Model model) {
        MemberDTO findUser;
        if (loginUser == null) {
            UserDetails userDetails=(UserDetails) authentication.getPrincipal();
            findUser = memberLoginService.findById(userDetails.getUsername());
        } else {
            findUser = memberLoginService.findById(loginUser.getMem_id());
        }
        model.addAttribute("memberDTO", findUser);
        return "login/mypage";
    }

    @PostMapping("/mypage")
    public String upDate(@Validated @ModelAttribute("memberDTO")updateForm updateForm,BindingResult bindingResult,HttpServletResponse response) throws IOException {
        System.out.println("updateForm.getMem_pwd() = " + updateForm.getMem_pwd());
        if (bindingResult.hasErrors()||updateForm.getMem_pwd()==null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('공백이나 빈값이 있을경우 수정이 불가합니다.'); </script>");
            out.flush();
            return "login/mypage";
        }
            //성공했을 때
            memberLoginService.updateUser(updateForm);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('수정이 완료되었습니다.'); </script>");
            out.flush();
            return "index";
    }

    }


