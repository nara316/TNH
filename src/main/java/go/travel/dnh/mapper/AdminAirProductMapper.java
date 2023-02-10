package go.travel.dnh.mapper;

import go.travel.dnh.domain.air.AirProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminAirProductMapper {

    List<AirProductDTO> selectAllAirProduct();
    int insert(AirProductDTO dto);

    int update(AirProductDTO dto);

    int delete(Integer ano);
    AirProductDTO select(Integer ano);
}
