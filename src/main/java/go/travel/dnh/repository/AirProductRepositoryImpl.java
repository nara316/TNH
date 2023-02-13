package go.travel.dnh.repository;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.air.Pagination;
import go.travel.dnh.domain.air.PagingResponse;
import go.travel.dnh.domain.air.SearchDTO;
import go.travel.dnh.mapper.AdminAirProductMapper;
import go.travel.dnh.mapper.AirProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AirProductRepositoryImpl implements AirProductRepository{

    private final AdminAirProductMapper airProductAdminMapper;
    private final AirProductMapper airProductMapper;

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

    @Override
    public PagingResponse<AirProductDTO> airProductList(SearchDTO sch) {

        int count = airProductMapper.count(sch);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, sch);
        sch.setPagination(pagination);

        List<AirProductDTO> list = airProductMapper.selectPro(sch);
        return new PagingResponse<>(list, pagination);

    }

    @Override
    public PagingResponse<AirProductDTO> airProductSch(SearchDTO sch) {
        int count = airProductMapper.searchCnt(sch);

        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, sch);
        sch.setPagination(pagination);

        List<AirProductDTO> list = airProductMapper.searchAir(sch);
        return new PagingResponse<>(list, pagination);
    }
}
