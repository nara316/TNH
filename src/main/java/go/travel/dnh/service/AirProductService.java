package go.travel.dnh.service;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.air.*;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface AirProductService {

    List<AirProductDTO> getListAdmin();

    int write(AirProductDTO dto);

    int modify(AirProductDTO dto);

    int remove(Integer ano);

    AirProductDTO read(Integer ano);

    PagingResponse<AirProductDTO> getList(SearchDTO sch);

    PagingResponse<AirProductDTO> getSearchOneWayList(SearchDTO sch);
    PagingResponse<AirProductDTO> getSearchFromList(SearchDTO sch);
    PagingResponse<AirProductDTO> getSearchToList(SearchDTO sch);

    PagingResponse<AirProductDTO> OneWaySort(SearchDTO sch);
    PagingResponse<AirProductDTO> roundSortOut(SearchDTO sch);
    PagingResponse<AirProductDTO> roundSortIn(SearchDTO sch);


    List<AirportDTO> getListAirport();

    List<AirlineDTO> getListAirline();


    //예약

    AirProductDTO readRes(Integer ano);

    void reservation(AirReservationDTO dto, ReservationDetail detail, @AuthenticationPrincipal LoginUser loginUser, Authentication authentication);



}
