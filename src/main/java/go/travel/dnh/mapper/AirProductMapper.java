package go.travel.dnh.mapper;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.air.AirportDTO;
import go.travel.dnh.domain.air.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirProductMapper {
    List<AirProductDTO> selectPro(SearchDTO sch);

    List<AirProductDTO> searchAirFrom(SearchDTO sch);
    List<AirProductDTO> searchAirTo(SearchDTO sch);

    int count(SearchDTO sch);

    int searchCntFrom(SearchDTO sch);
    int searchCntTo(SearchDTO sch);

    List<AirportDTO> selectAP();

    AirProductDTO selectRes(Integer ano);

}
