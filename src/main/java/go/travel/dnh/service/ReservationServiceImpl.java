package go.travel.dnh.service;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;
import go.travel.dnh.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final MemberLoginService memberLoginService;

    private int ar_res_cnt;

    @Override
    @Transactional
    public void reservation(AirReservationDTO dto, ReservationDetail detail, @AuthenticationPrincipal LoginUser loginUser, Authentication authentication) {

        /*재고수량 유효성 검사 및 수량 업데이트*/
        if(dto.getIn_ano()!=null){
            stockCheck(reservationRepository.readCnt(dto.getIn_ano()),dto.getArp_count());
            stockUpdate(dto.getIn_ano(),dto.getArp_count());
        }
        stockCheck(reservationRepository.readCnt(dto.getOut_ano()),dto.getArp_count());
        stockUpdate(dto.getOut_ano(),dto.getArp_count());

        Integer mno = memberLoginService.findMember(loginUser,authentication).getMno();
        dto.setMno(mno);
        Integer ano = dto.getOut_ano();
        //rno 만들기
        Long rno = makeRno(mno, ano);
        dto.setRno(rno);
        /*예약 db 인서트*/
        reservationRepository.reservation(dto);
        /*detail 인서트*/
        for (int i = 0; i < dto.getArp_count(); i++) {
            ReservationDetail resDetail = detail.getDetailList().get(i);
            resDetail.setRno(rno);
            reservationRepository.resDetail(detail.getDetailList().get(i));
        }
    }

    @Override
    public AirReservationListDTO getReservation(Long rno){
        return reservationRepository.getReservation(rno);
    }

    @Override
    public AirReservationListDTO getReservationRound(Long rno){
        return reservationRepository.getReservationRound(rno);
    }

    @Override
    public List<AirReservationDTO> readList(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication) {

        int mno = memberLoginService.findMember(loginUser, authentication).getMno();
        return reservationRepository.readList(mno);
    }

    @Override
    public List<AirReservationListDTO> selectMyRes(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication){
        int mno = memberLoginService.findMember(loginUser, authentication).getMno();
        return reservationRepository.selectMyRes(mno);
    }

    @Override
    public List<AirReservationListDTO> getReservationDetail(Long rno) {
        return reservationRepository.getReservationDetail(rno);
    }

    @Override
    public void update(Long rno){
        reservationRepository.update(rno);
    }

    @Override
    public void updateRefund(Long rno){
        reservationRepository.updateRefund(rno);
    }

    /*선택수량보다 재고수량이 적으면 메서드 종료*/
    private void stockCheck(Integer stock, Integer selectSize){
       if(stock<selectSize){
           return;
       }
    }

    /*재고수량 업데이트 메서드*/
    private void stockUpdate(Integer ano, Integer selectSize){
        ar_res_cnt = reservationRepository.readCnt(ano)-selectSize;
        reservationRepository.updateResCnt(ano,ar_res_cnt);
    }

    /*rno생성 메서드*/
    private Long makeRno(Integer mno,Integer ano){
        //예약날짜
        LocalDate date = LocalDate.now();
        String dateString = date.toString().replace("-","").substring(2,8);
        //회원번호
        String mnoString = String.format("%03d", mno);
        //예약할 항공권 상품번호(가는편 기준)
        String anoString = String.format("%03d", ano);
        //0~99까지 임의의 수 만들기
        Random r = new Random();
        int ran= r.nextInt(99);
        String ranString = String.format("%02d", ran);
        //위의 세가지 합체
        StringBuffer sb = new StringBuffer();
        sb.append(dateString);
        sb.append(mnoString);
        sb.append(anoString);
        sb.append(ranString);
        String resNumString = String.valueOf(sb);
        return Long.parseLong(resNumString);
    }

}
