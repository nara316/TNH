package go.travel.dnh.mapper;

import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {

    public AirReservationListDTO getReservation(Long rno);

    public AirReservationListDTO getReservationRound(Long rno);

    public List<AirReservationDTO> readList(int mno);

    public List<AirReservationListDTO> selectMyRes(int mno);

    public List<AirReservationListDTO> getReservationDetail(Long rno);

    public void update(Long rno);

    public void updateRefund(Long rno);
}
