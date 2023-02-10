package go.travel.dnh.repository;

import go.travel.dnh.domain.air.AirProductDTO;

import java.util.List;

public interface AirProductRepository {

    List<AirProductDTO> adminAirProductList();

    int write(AirProductDTO dto);

}
