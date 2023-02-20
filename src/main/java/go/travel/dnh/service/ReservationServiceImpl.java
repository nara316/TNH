package go.travel.dnh.service;

import go.travel.dnh.domain.reservation.ReservationDTO;
import go.travel.dnh.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
