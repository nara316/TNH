package go.travel.dnh.repository;

import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.pay.RefundDTO;

import java.util.List;

public interface PaymentRepository {

    public void insertPay(PayDTO payDTO);

    public List<PayDTO> readPayList();

    public PayDTO readPay(String pno);

    public void updatePayState(String pno);

    public void insertRefund(RefundDTO refundDTO);

    public int readOneRefund(String pno);

    public String readPno(Long rno);
}
