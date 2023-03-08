package go.travel.dnh.repository;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;

import java.util.List;

public interface ReservationRepository {

    public AirReservationListDTO getReservation(Long rno);

    public AirReservationListDTO getReservationRound(Long rno);

    public List<AirReservationDTO> readList(int mno);

    public List<AirReservationListDTO> selectMyRes(int mno);

    public List<AirReservationListDTO> getReservationDetail(Long rno);

    public void update(Long rno);

    public void updateRefund(Long rno);
}
