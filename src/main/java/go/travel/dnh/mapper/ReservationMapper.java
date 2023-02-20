package go.travel.dnh.mapper;

import go.travel.dnh.domain.reservation.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {

    public List<ReservationDTO> getReservationList();

    public ReservationDTO getReservation(int rno);
}
