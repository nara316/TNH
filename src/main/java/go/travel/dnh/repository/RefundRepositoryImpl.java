package go.travel.dnh.repository;

import go.travel.dnh.domain.pay.RefundDTO;
import go.travel.dnh.mapper.PaymentMapper;
import go.travel.dnh.mapper.RefundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class RefundRepositoryImpl implements RefundRepository {

    private final RefundMapper refundMapper;

    @Override
    public void insertRefund(RefundDTO refundDTO) {
        refundMapper.insertRefund(refundDTO);
    }

    @Override
    public int readOneRefund(String pno) {
        return refundMapper.readOneRefund(pno);
    }

    @Override
    public RefundDTO readRefund(String pno){
        return refundMapper.readRefund(pno);
    }

}
