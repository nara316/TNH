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

    @Override
    public int write(AirProductDTO dto) {
        return airProductAdminMapper.insert(dto);
    }

    @Override
    public int modify(AirProductDTO dto) {
        return airProductAdminMapper.update(dto);
    }

    @Override
    public int delete(Integer ano) {
        return airProductAdminMapper.delete(ano);
    }

    @Override
    public AirProductDTO read(Integer ano) {
        return airProductAdminMapper.select(ano);
    }
}
