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

        Integer mno = memberLoginService.findMember(loginUser,authentication).getMno();
        dto.setMno(mno);
        Integer ano = dto.getOut_ano();
        //rno 만들기
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
        Long rno = Long.parseLong(resNumString);
        dto.setRno(rno);
        System.out.println(dto.getRno());
        /*예약 db 인서트*/
        reservationRepository.reservation(dto);
        /*detail 인서트*/
        for (int i = 0; i < dto.getArp_count(); i++) {
            ReservationDetail resDetail = detail.getDetailList().get(i);
            resDetail.setRno(rno);
            reservationRepository.resDetail(detail.getDetailList().get(i));
        }

        /*ar_res_cnt 개수 업데이트*/
        if(dto.getIn_ano()!=null){
            ar_res_cnt = reservationRepository.readCnt(dto.getIn_ano())-dto.getArp_count();
            reservationRepository.updateResCnt(dto.getIn_ano(),ar_res_cnt);
        }
        ar_res_cnt = reservationRepository.readCnt(ano)-dto.getArp_count();
        reservationRepository.updateResCnt(ano,ar_res_cnt);
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
}
