package go.travel.dnh.controller;

import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.repository.RefundRepository;
import go.travel.dnh.service.PaymentService;
import go.travel.dnh.service.RefundService;
import go.travel.dnh.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class RefundController {

    private final RefundService refundService;
    private final PaymentService paymentService;
    private final ReservationService reservationService;
    private int result;

    /*환불 상세페이지*/
    @GetMapping("/payList/{pno}")
    public String payPractice(@PathVariable("pno") String pno, Model model) {
        PayDTO pay = paymentService.readPay(pno);
        AirReservationListDTO revDto = reservationService.getReservation(pay.getRno());
        List<AirReservationListDTO> revDtDto = reservationService.getReservationDetail(pay.getRno());

        model.addAttribute("plist", pay);
        model.addAttribute("rlist", revDto);
        model.addAttribute("rdtlist", revDtDto);
        return "order/refund";
    }

    /*환불 인서트*/
    @PostMapping("/refund/complete")
    @ResponseBody
    public int refund (String pno, String rf_reason) {

        result = refundService.refund(pno,rf_reason);
        return result;
    }
}
