package go.travel.dnh.repository;

import go.travel.dnh.domain.pay.PayDTO;

public interface PaymentRepository {

    public void insertPay(PayDTO payDTO);
}
