package go.travel.dnh.service;

import go.travel.dnh.domain.reservation.ReservationDTO;

import java.util.List;

public interface ReservationService {

    public List<ReservationDTO> getReservationList();

    public ReservationDTO getReservation(int rno);

}
