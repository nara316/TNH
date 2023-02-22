package go.travel.dnh.repository;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.air.AirportDTO;
import go.travel.dnh.domain.air.PagingResponse;
import go.travel.dnh.domain.air.SearchDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;

import java.util.List;

public interface AirProductRepository {

    List<AirProductDTO> adminAirProductList();

    int write(AirProductDTO dto);

    int modify(AirProductDTO dto);

    int delete(Integer ano);

    AirProductDTO read(Integer ano);

    PagingResponse<AirProductDTO> airProductList(SearchDTO sch);

    PagingResponse<AirProductDTO> airProductSchFrom(SearchDTO sch);
    PagingResponse<AirProductDTO> airProductSchTo(SearchDTO sch);

    List<AirportDTO> airportList();

    /////////////////////////예약
    //선택한 항공권 예약화면에 보여주기
    AirProductDTO resRead(Integer ano);
    //예약저장
    int reservation(AirReservationDTO dto);

    int resDetail(ReservationDetail detail);




}
