package go.travel.dnh.service;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface ReservationService {

    void reservation(AirReservationDTO dto, ReservationDetail detail, @AuthenticationPrincipal LoginUser loginUser, Authentication authentication);

    AirReservationListDTO getReservation(Long rno);

    AirReservationListDTO getReservationRound(Long rno);

    List<AirReservationDTO> readList(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication);

    List<AirReservationListDTO> selectMyRes(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication);

    List<AirReservationListDTO> getReservationDetail(Long rno);

    void update(Long rno);

    void updateRefund(Long rno);

}
