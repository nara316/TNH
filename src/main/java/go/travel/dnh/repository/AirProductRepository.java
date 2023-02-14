package go.travel.dnh.repository;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.air.AirportDTO;
import go.travel.dnh.domain.air.PagingResponse;
import go.travel.dnh.domain.air.SearchDTO;

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

}
