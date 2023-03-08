package go.travel.dnh.repository;

import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;

import java.util.List;

public interface ReservationRepository {

    int reservation(AirReservationDTO dto);

    int resDetail(ReservationDetail detail);

    void updateResCnt(Integer ano, Integer ar_res_cnt);

    int readCnt(Integer ano);

    AirReservationListDTO getReservation(Long rno);

    AirReservationListDTO getReservationRound(Long rno);

    List<AirReservationDTO> readList(int mno);

    List<AirReservationListDTO> selectMyRes(int mno);

    List<AirReservationListDTO> getReservationDetail(Long rno);

    void update(Long rno);

    void updateRefund(Long rno);
}
