package go.travel.dnh.service;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.reservation.ReservationDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface ReservationService {

    public List<ReservationDTO> getReservationList();

    public ReservationDTO getReservation(int rno);

    public void insert(ReservationDTO reservationDTO, int mno);

}
