package go.travel.dnh.service;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final MemberLoginService memberLoginService;

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
