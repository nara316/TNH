package go.travel.dnh.controller;

import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.service.PaymentService;
import go.travel.dnh.service.ReservationService;
import lombok.RequiredArgsConstructor;
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


    @GetMapping("/payList")
    public String payForm(Model model) {
        List<PayDTO> payList = paymentService.readPayList();

        model.addAttribute("payList", payList);

        return "order/payList";
    }

    @GetMapping("/payList/{pno}")
    public String payPractice(@PathVariable("pno") String pno, Model model) {
        PayDTO pay = paymentService.readPay(pno);
        model.addAttribute("list", pay);
        return "order/refundPractice";
    }

    @GetMapping("{rno}")
    public String payrev(@PathVariable("rno") Long rno, Model model) {
        AirReservationListDTO revDto = reservationService.getReservation(rno);
        List<AirReservationListDTO> revDtDto = reservationService.getReservationDetail(rno);
        model.addAttribute("list", revDto);
        model.addAttribute("listDetail", revDtDto);
        return "order/payment";
    }

    @PostMapping("/refund/complete")
    @ResponseBody
    public int refund (String pno, String rf_reason) {

        //이미 환불완료된 건은 넘어가면 안된다.
        if(paymentService.readOneRefund(pno)==1){
           result = 1;
        } else {
            result = paymentService.refund(pno,rf_reason);
        }
        return result;
    }

}
