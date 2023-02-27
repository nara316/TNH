package go.travel.dnh.repository;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDTO;
import go.travel.dnh.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository{

    private final ReservationMapper reservationMapper;

    @Override
    public List<ReservationDTO> getReservationList() {
        return reservationMapper.getReservationList();
    }

    @Override
    public ReservationDTO getReservation(int rno){
        return reservationMapper.getReservation(rno);
    }

    @Override
    public void insert(ReservationDTO reservationDTO) {
        reservationMapper.insert(reservationDTO);
    }

    @Override
    public List<AirReservationDTO> readList(int mno) {
        return reservationMapper.readList(mno);
    }

    @Override
    public List<AirReservationListDTO> selectMyRes(int mno){
        return reservationMapper.selectMyRes(mno);
    }
}
