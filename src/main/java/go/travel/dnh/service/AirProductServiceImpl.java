package go.travel.dnh.service;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.air.PagingResponse;
import go.travel.dnh.domain.air.SearchDTO;
import go.travel.dnh.repository.AirProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AirProductServiceImpl implements AirProductService{

    private final AirProductRepository airProductRepository;

    @Override
    public List<AirProductDTO> getListAdmin() {
        return airProductRepository.adminAirProductList();
    }

    @Override
    public int write(AirProductDTO dto) {
        return airProductRepository.write(dto);
    }

    @Override
    public int modify(AirProductDTO dto) {
        return airProductRepository.modify(dto);
    }

    @Override
    public int remove(Integer ano) {
        return airProductRepository.delete(ano);
    }

    @Override
    public AirProductDTO read(Integer ano) {
        return airProductRepository.read(ano);
    }

    @Override
    public PagingResponse<AirProductDTO> getList(SearchDTO sch) {
        return airProductRepository.airProductList(sch);
    }
}
