package go.travel.dnh.mapper;

import go.travel.dnh.domain.pay.PayDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    public void insert(PayDTO payDTO);
}
