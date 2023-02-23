package go.travel.dnh.service;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.domain.reservation.ReservationDTO;
import go.travel.dnh.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    @Override
    public List<ReservationDTO> getReservationList() {
        return reservationRepository.getReservationList();
    }

    @Override
    public ReservationDTO getReservation(int rno){
        return reservationRepository.getReservation(rno);
    }

    @Override
    public void insert(ReservationDTO reservationDTO, int mno) {

        ReservationDTO rv = new ReservationDTO();
        rv.setRno(reservationDTO.getRno());
        rv.setAno(reservationDTO.getAno());
        rv.setMno(mno);
        rv.setArp_count(reservationDTO.getArp_count());
        rv.setArp_price(reservationDTO.getArp_price());

        reservationRepository.insert(rv);
    }

}
