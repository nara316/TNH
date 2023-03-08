package go.travel.dnh.mapper;

import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.pay.RefundDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    void insertPay(PayDTO payDTO);

    List<PayDTO> read();

    PayDTO readOne(String pno);

    void update(String pno);

    void insertRefund(RefundDTO refundDTO);

    int readOneRefund(String pno);

    String readPno(Long rno);

    RefundDTO readRefund(String pno);
}
