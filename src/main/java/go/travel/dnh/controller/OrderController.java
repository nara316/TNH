package go.travel.dnh.controller;

import go.travel.dnh.domain.reservation.ReservationDTO;
import go.travel.dnh.domain.reservation.UIDDTO;
import go.travel.dnh.service.PaymentService;
import go.travel.dnh.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final ReservationService reservationService;
    private final PaymentService paymentService;

    @GetMapping("/order")
    public String orderForm(Model model) {
        List<ReservationDTO> reservationList = reservationService.getReservationList();

        model.addAttribute("reservationList", reservationList);

        return "order/practice";
    }

    //카드결제 성공 후
    @PostMapping("/payment/complete")
    @ResponseBody
    public ResponseEntity<String> paymentComplete(String imp_uid) throws IOException {

        System.out.println(imp_uid);
        //1. 아임포트 API키와 SECRET키로 토큰을 생성
        String token = paymentService.getToken();

        System.out.println("토큰: " + token);
        //토큰으로 결제 완료된 주문정보를 가져온다.
        int amount = paymentService.paymentInfo(imp_uid, token);

        //DB에서 실제 계산되어야 할 가격 가져오기
        try {

            long orderPriceCheck = 1200;

            // 계산 된 금액과 실제 금액이 다를 때
            if (orderPriceCheck != amount) {
                paymentService.paymentCancle(token,imp_uid, amount, "결제 금액 오류");
                return new ResponseEntity<String>("결제 금액 오류, 결제 취소", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>("결제가 완료되었습니다.", HttpStatus.OK);

        } catch (Exception e) {
            paymentService.paymentCancle(token, imp_uid, amount, "결제 에러");
            return new ResponseEntity<String>("결제 에러!", HttpStatus.BAD_REQUEST);
        }
    }
}