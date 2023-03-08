package go.travel.dnh.repository;

import go.travel.dnh.domain.pay.RefundDTO;

import java.util.List;

public interface RefundRepository {

    void insertRefund(RefundDTO refundDTO);

    int readOneRefund(String pno);

    RefundDTO readRefund(String pno);

}
