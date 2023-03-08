package go.travel.dnh.mapper;

import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.pay.RefundDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    public void insertPay(PayDTO payDTO);

    public List<PayDTO> read();

    public PayDTO readOne(String pno);

    public void update(String pno);

    public void insertRefund(RefundDTO refundDTO);

    public int readOneRefund(String pno);

    public String readPno(Long rno);

    public RefundDTO readRefund(String pno);
}
