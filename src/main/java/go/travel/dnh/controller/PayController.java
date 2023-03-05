package go.travel.dnh.controller;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.service.MemberLoginService;
import go.travel.dnh.service.PaymentService;
import go.travel.dnh.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class PayController {

    private final PaymentService paymentService;
    private final ReservationService reservationService;
    private int result;

    /*환불*/
    @GetMapping("/payList/{pno}")
    public String payPractice(@PathVariable("pno") String pno, @AuthenticationPrincipal LoginUser loginUser, Authentication authentication, Model model) {
        PayDTO pay = paymentService.readPay(pno);
        AirReservationListDTO revDto = reservationService.getReservation(pay.getRno());
        List<AirReservationListDTO> revDtDto = reservationService.getReservationDetail(pay.getRno());

        model.addAttribute("plist", pay);
        model.addAttribute("rlist", revDto);
        model.addAttribute("rdtlist", revDtDto);
        return "order/refund";
    }

    @GetMapping("/confirm")
    public String bookingConfirm(@RequestParam Long rno, Model model) {
        AirReservationListDTO revDto = reservationService.getReservation(rno);
        PayDTO payDTO = paymentService.readPay(paymentService.readPno(rno));

        /*결제 취소된 이후 여기 페이지로 오면 안된다.*/
        if(revDto.getArp_state().equalsIgnoreCase("결제 취소")){
            model.addAttribute("message", "이미 결제 취소된 내역입니다");
            model.addAttribute("searchURL", "/");
            return "message";
        }

        model.addAttribute("rlist", revDto);
        model.addAttribute("plist", payDTO);
        return "order/bookingConfirm";
    }

    @PostMapping("/refund/complete")
    @ResponseBody
    public int refund (String pno, String rf_reason) {

        return result = paymentService.refund(pno,rf_reason);
    }

}
