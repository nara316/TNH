package go.travel.dnh.repository;

import go.travel.dnh.domain.reservation.ReservationDTO;

import java.util.List;

public interface ReservationRepository {

    public List<ReservationDTO> getReservationList();

    public ReservationDTO getReservation(int rno);

}
