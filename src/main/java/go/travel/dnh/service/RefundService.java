package go.travel.dnh.service;

import go.travel.dnh.domain.pay.RefundDTO;


public interface RefundService {

    int refund(String pno,String rf_reason);

    int readOneRefund(String pno);

    RefundDTO readRefund(String pno);
}
