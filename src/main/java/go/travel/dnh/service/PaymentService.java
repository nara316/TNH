package go.travel.dnh.service;

import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.pay.RefundDTO;

import java.io.IOException;
import java.util.List;

public interface PaymentService {

    String getToken() throws IOException;

    int paymentInfo(String imp_uid, String access_token) throws IOException;

    public void paymentCancle(String access_token, String imp_uid, int amount, String reason) throws IOException;

    public void insertPay(String imp_uid,Long merchant_uid,int totalPrice, String pay_method);

    public List<PayDTO> readPayList();

    public PayDTO readPay(String pno);

    public int refund(String pno,String rf_reason);

    public int readOneRefund(String pno);
}
