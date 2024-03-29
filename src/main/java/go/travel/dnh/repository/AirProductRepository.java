package go.travel.dnh.repository;

import go.travel.dnh.domain.air.*;
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
    PagingResponse<AirProductDTO> roundSortOut(SearchDTO sch);
    PagingResponse<AirProductDTO> airProductSchTo(SearchDTO sch);
    PagingResponse<AirProductDTO> roundSortIn(SearchDTO sch);
    PagingResponse<AirProductDTO> airProductSchOneWay(SearchDTO sch);
    PagingResponse<AirProductDTO> OneWaySort(SearchDTO sch);

    PagingResponse<AirProductDTO> indexSearch(SearchDTO sch);


    List<AirportDTO> airportList();
    List<AirlineDTO> airlineList();

    //선택한 항공권 예약화면에 보여주기
    AirProductDTO resRead(Integer ano);

}
