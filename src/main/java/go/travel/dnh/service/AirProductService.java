package go.travel.dnh.service;

import go.travel.dnh.domain.air.AirProductDTO;

import java.util.List;

public interface AirProductService {

    List<AirProductDTO> getListAdmin();

    int write(AirProductDTO dto);

}