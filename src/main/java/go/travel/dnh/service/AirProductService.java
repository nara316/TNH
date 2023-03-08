package go.travel.dnh.service;

import go.travel.dnh.domain.air.*;


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

    PagingResponse<AirProductDTO> indexSearch(SearchDTO sch);

    PagingResponse<AirProductDTO> OneWaySort(SearchDTO sch);
    PagingResponse<AirProductDTO> roundSortOut(SearchDTO sch);
    PagingResponse<AirProductDTO> roundSortIn(SearchDTO sch);


    List<AirportDTO> getListAirport();

    List<AirlineDTO> getListAirline();

    /*예약*/
    AirProductDTO readRes(Integer ano);

}
