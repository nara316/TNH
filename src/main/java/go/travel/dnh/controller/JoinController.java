package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.JoinService;
import go.travel.dnh.validation.MemberjoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.internet.MimeMessage;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class JoinController {

    private final JoinService joinService;
    private final JavaMailSender mailSender;

    String answerNum = "";

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

        //인증숫자 생성
        answerNum = randomNum(); //인증번호 생성했음

//        //이메일 보낼 문구
//        String setFrom = "skfk6352@naver.com";
//        String toMail = mem_id;
//        String title = "회원가입 인증 이메일 입니다.";
//        String content =
//                "인증 번호는 " + answerNum + "입니다." +
//                        "<br>" +
//                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
//
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
//            helper.setFrom(setFrom);
//            helper.setTo(toMail);
//            helper.setSubject(title);
//            helper.setText(content, true);
//            mailSender.send(message);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(answerNum);
       return true;
    }

    //이메일 인증번호 확인하는 메서드
    @PostMapping("/emailNumCheck")
    @ResponseBody
    public int emailNumCheck(String checkInput){

        int result; //프론트에 넘겨줄 값

        if(checkInput.equals(answerNum)){
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    private String randomNum() {
        Random random = new Random();
        int num = 0; //1자리 수
        String stringNum = ""; //String 만들어줄라고 (ajax로 값 넘길 때 String이어야 한다.)
        String resultNum = "";
        int size = 6; //랜덤값은 6자리 수이다.

        for (int i = 0; i < size; i++) {
            num = random.nextInt(10);
            stringNum = Integer.toString(num);
            resultNum += stringNum;
        }
        return resultNum;
    }
}
