package go.travel.dnh.controller;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.User.updateForm;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.pay.RefundDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping( "/")
@RequiredArgsConstructor
public class MemberInfoController {

    private final MemberLoginServiceImpl memberLoginService;
    private final ReservationService reservationService;
    private final PaymentService paymentService;
    private final RefundService refundService;


    @GetMapping("/mypage")
    public String MyPage(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO findUser = memberLoginService.findById(username);
//        MemberDTO findUser = memberLoginService.findMember(loginUser,authentication);
        model.addAttribute("memberDTO", findUser);
        return "login/mypage";
    }


    @PostMapping("/mypage")
    public String upDate(@Validated @ModelAttribute("memberDTO") updateForm updateForm, BindingResult bindingResult, Model model)  {
        if(bindingResult.hasErrors()) {
            return "login/mypage";
        } else {
            //성공했을 때
            memberLoginService.updateUser(updateForm);
            model.addAttribute("message", "회원 정보 수정이 완료되었습니다.");
            model.addAttribute("searchURL", "/mypage");
            return "message";
        }
    }
    @PostMapping("/mypage/withdrawal")
    public @ResponseBody boolean deleteMember(@RequestBody Map<String, Object> deleteUser, HttpServletRequest request, HttpServletResponse response)  {
        memberLoginService.deleteMember(deleteUser);
        memberLoginService.logout(request, response);
        return true;
    }

    /*마이페이지 내 예약리스트*/
    @GetMapping("/bookingList")
    public String bookingList(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication, Model model) {

        MemberDTO memberDTO = memberLoginService.findMember(loginUser,authentication);
        List<AirReservationListDTO> revList = reservationService.selectMyRes(loginUser,authentication);

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("revList", revList);

        return "order/bookingList";
    }

    /*예약상세*/
    @GetMapping("/bookingList/{rno}")
    public String showRevDetail(@PathVariable("rno") Long rno, Model model) {
        AirReservationListDTO revDto = reservationService.getReservation(rno);
        AirReservationListDTO revRoundDto = reservationService.getReservationRound(rno);
        List<AirReservationListDTO> revDtDto = reservationService.getReservationDetail(rno);
        PayDTO payDTO = paymentService.readPay(paymentService.readPno(rno));

        if(payDTO !=null){
            RefundDTO refundDTO = refundService.readRefund(payDTO.getPno());
            model.addAttribute("rflist",refundDTO);
        }

        model.addAttribute("list", revDto);
        model.addAttribute("rolist",revRoundDto);
        model.addAttribute("listDetail", revDtDto);
        model.addAttribute("plist", payDTO);
        return "order/bookingDetail";
    }

}
