package go.travel.dnh.mapper;

import go.travel.dnh.domain.pay.RefundDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RefundMapper {

    void insertRefund(RefundDTO refundDTO);

    int readOneRefund(String pno);

    RefundDTO readRefund(String pno);
}
