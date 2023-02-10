package go.travel.dnh.repository;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.mapper.AdminAirProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AirProductRepositoryImpl implements AirProductRepository{

    private final AdminAirProductMapper airProductAdminMapper;

    @Override
    public List<AirProductDTO> adminAirProductList() {
        return airProductAdminMapper.selectAllAirProduct();
    }
}
