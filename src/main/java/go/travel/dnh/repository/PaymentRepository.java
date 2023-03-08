package go.travel.dnh.repository;

import go.travel.dnh.domain.pay.PayDTO;

import java.util.List;

public interface PaymentRepository {

    void insertPay(PayDTO payDTO);

    List<PayDTO> readPayList();

    PayDTO readPay(String pno);

    void updatePayState(String pno);

    String readPno(Long rno);
}
