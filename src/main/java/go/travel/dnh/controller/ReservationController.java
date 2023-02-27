package go.travel.dnh.controller;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDTO;
import go.travel.dnh.service.MemberLoginService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberLoginService memberLoginService;

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

    @GetMapping("/bookingList")
    public String bookingList(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication, Model model) {

        MemberDTO memberDTO = memberLoginService.findMember(loginUser,authentication);
        List<AirReservationListDTO> revList = reservationService.selectMyRes(loginUser,authentication);

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("revList", revList);

        return "order/bookingList";
    }

    //연습용 메서드
    @GetMapping("/bookingList2")
    public String orderForm(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication, Model model) {

        List<AirReservationDTO> reservationList = reservationService.readList(loginUser, authentication);

        model.addAttribute("reservationList", reservationList);

        return "order/bookingList2";
    }

}
