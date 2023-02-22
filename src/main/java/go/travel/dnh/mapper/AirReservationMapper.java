package go.travel.dnh.mapper;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirReservationMapper {

    int insertReservation(AirReservationDTO dto);

    int insertResDetail(ReservationDetail detail);


}
