package go.travel.dnh.mapper;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {

    public List<ReservationDTO> getReservationList();

    public AirReservationListDTO getReservation(Long rno);

    public void insert(ReservationDTO reservationDTO);

    public List<AirReservationDTO> readList(int mno);

    public List<AirReservationListDTO> selectMyRes(int mno);

    public List<AirReservationListDTO> getReservationDetail(Long rno);
}
