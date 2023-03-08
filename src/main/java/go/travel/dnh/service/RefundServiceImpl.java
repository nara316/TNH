package go.travel.dnh.service;


import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.pay.RefundDTO;
import go.travel.dnh.repository.PaymentRepository;
import go.travel.dnh.repository.RefundRepository;
import go.travel.dnh.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService{

    private final RefundRepository refundRepository;
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    private int ar_res_cnt;

    @Override
    @Transactional
    public int refund(String pno, String rf_reason) {
        //pno로 db에 결제완료된 리스트가 있는지 확인하고 있으면 pay테이블은 update
        //그리고 refund테이블에 Insert
        //reservation 테이블도 업데이트

        PayDTO payDTO = paymentRepository.readPay(pno);

        if(payDTO!=null){
            paymentRepository.updatePayState(pno);

            RefundDTO refundDTO = new RefundDTO();
            refundDTO.setRfno(payDTO.getPno());
            refundDTO.setPno(payDTO.getPno());
            refundDTO.setMno(payDTO.getMno());
            refundDTO.setRf_reason(rf_reason);

            refundRepository.insertRefund(refundDTO);
            reservationRepository.updateRefund(payDTO.getRno());
            /*환불 성공시 air_res_cnt 수량 변경*/
            /*ar_res_cnt 개수 업데이트*/
            if(reservationRepository.getReservation(payDTO.getRno()).getIn_ano()!=null){
                ar_res_cnt = reservationRepository.readCnt(reservationRepository.getReservation(payDTO.getRno()).getIn_ano())+reservationRepository.getReservation(payDTO.getRno()).getArp_count();
                reservationRepository.updateResCnt(reservationRepository.getReservation(payDTO.getRno()).getIn_ano(),ar_res_cnt);
            }
            ar_res_cnt = reservationRepository.readCnt(reservationRepository.getReservation(payDTO.getRno()).getOut_ano())+reservationRepository.getReservation(payDTO.getRno()).getArp_count();
            reservationRepository.updateResCnt(reservationRepository.getReservation(payDTO.getRno()).getOut_ano(),ar_res_cnt);
        }

        return 0;
    }

    @Override
    public int readOneRefund(String pno) {
        return refundRepository.readOneRefund(pno);
    }


    @Override
    public RefundDTO readRefund(String pno){
        return refundRepository.readRefund(pno);
    }
}
