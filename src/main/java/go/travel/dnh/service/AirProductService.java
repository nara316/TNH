package go.travel.dnh.service;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.air.AirportDTO;
import go.travel.dnh.domain.air.PagingResponse;
import go.travel.dnh.domain.air.SearchDTO;

import java.util.List;

public interface AirProductService {

    List<AirProductDTO> getListAdmin();

    int write(AirProductDTO dto);

    int modify(AirProductDTO dto);

    int remove(Integer ano);

    AirProductDTO read(Integer ano);

    PagingResponse<AirProductDTO> getList(SearchDTO sch);

    PagingResponse<AirProductDTO> getSearchFromList(SearchDTO sch);
    PagingResponse<AirProductDTO> getSearchToList(SearchDTO sch);

    List<AirportDTO> getListAirport();

}
