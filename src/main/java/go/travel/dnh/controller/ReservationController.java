package go.travel.dnh.controller;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.pay.RefundDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDTO;
import go.travel.dnh.service.MemberLoginService;
import go.travel.dnh.service.PaymentService;
import go.travel.dnh.service.ReservationService;
import go.travel.dnh.validation.MemberjoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberLoginService memberLoginService;
    private final PaymentService paymentService;

    @GetMapping("/air")
    public String reservationForm(ReservationDTO reservationDTO) {
        return "order/reservation";
    }

    @PostMapping("/air")
    public String join(ReservationDTO reservationDTO, @AuthenticationPrincipal LoginUser loginUser, Authentication authentication) {

        int mno = memberLoginService.findMember(loginUser,authentication).getMno();
        reservationService.insert(reservationDTO, mno);
        return "admin/main";
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
        List<AirReservationListDTO> revDtDto = reservationService.getReservationDetail(rno);
        PayDTO payDTO = paymentService.readPay(paymentService.readPno(rno));

        if(payDTO !=null){
            RefundDTO refundDTO = paymentService.readRefund(payDTO.getPno());
            model.addAttribute("rflist",refundDTO);
        }

        model.addAttribute("list", revDto);
        model.addAttribute("listDetail", revDtDto);
        model.addAttribute("plist", payDTO);
        return "order/bookingDetail";
    }

}
