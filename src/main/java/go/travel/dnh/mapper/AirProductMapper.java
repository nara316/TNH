package go.travel.dnh.mapper;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.air.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirProductMapper {
    List<AirProductDTO> selectPro(SearchDTO sch);

    List<AirProductDTO> searchAir(SearchDTO sch);

    int count(SearchDTO sch);

    int searchCnt(SearchDTO sch);


}
