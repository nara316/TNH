package go.travel.dnh.service;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface ReservationService {

    public List<ReservationDTO> getReservationList();

    public AirReservationListDTO getReservation(Long rno);

    public void insert(ReservationDTO reservationDTO, int mno);

    public List<AirReservationDTO> readList(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication);

    public List<AirReservationListDTO> selectMyRes(@AuthenticationPrincipal LoginUser loginUser, Authentication authentication);

    public List<AirReservationListDTO> getReservationDetail(Long rno);

    public void update(Long rno);

    public void updateRefund(Long rno);

}
