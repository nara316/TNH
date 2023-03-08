package go.travel.dnh.mapper;

import go.travel.dnh.domain.pay.PayDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    void insertPay(PayDTO payDTO);

    List<PayDTO> read();

    PayDTO readOne(String pno);

    void update(String pno);

    String readPno(Long rno);

}
