package go.travel.dnh.repository;

import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentMapper paymentMapper;

    @Override
    public void insertPay(PayDTO payDTO) {
        System.out.println("진입성공8");
        paymentMapper.insert(payDTO);
    }
}
