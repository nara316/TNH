package go.travel.dnh.service;

import go.travel.dnh.domain.pay.PayDTO;

import java.io.IOException;
import java.util.List;

public interface PaymentService {

    String getToken() throws IOException;

    int paymentInfo(String imp_uid, String access_token) throws IOException;

    void paymentCancle(String access_token, String imp_uid, int amount, String reason) throws IOException;

    void insertPay(String imp_uid,Long merchant_uid,int totalPrice, String pay_method);

    List<PayDTO> readPayList();

    PayDTO readPay(String pno);

    String readPno(Long rno);

}
