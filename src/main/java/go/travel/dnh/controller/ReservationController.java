package go.travel.dnh.controller;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.member.MemberDTO;
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

        int mno = memberLoginService.findMno(loginUser,authentication);
        reservationService.insert(reservationDTO, mno);
        return "admin/main";
    }


}
