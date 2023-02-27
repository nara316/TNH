package go.travel.dnh.repository;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDTO;

import java.util.List;

public interface ReservationRepository {

    public List<ReservationDTO> getReservationList();

    public ReservationDTO getReservation(int rno);

    public void insert(ReservationDTO reservationDTO);

    public List<AirReservationDTO> readList(int mno);

    public List<AirReservationListDTO> selectMyRes(int mno);
}
