package go.travel.dnh.repository;

import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.pay.RefundDTO;
import go.travel.dnh.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentMapper paymentMapper;

    @Override
    public void insertPay(PayDTO payDTO) {
        paymentMapper.insertPay(payDTO);
    }

    @Override
    public List<PayDTO> readPayList() {
       return paymentMapper.read();
    }

    @Override
    public PayDTO readPay(String pno) {
        return paymentMapper.readOne(pno);
    }

    @Override
    public void updatePayState(String pno) {
        paymentMapper.update(pno);
    }

    @Override
    public void insertRefund(RefundDTO refundDTO) {
        paymentMapper.insertRefund(refundDTO);
    }

    @Override
    public int readOneRefund(String pno) {
        return paymentMapper.readOneRefund(pno);
    }

    @Override
    public String readPno(Long rno){
        return paymentMapper.readPno(rno);
    }

}
