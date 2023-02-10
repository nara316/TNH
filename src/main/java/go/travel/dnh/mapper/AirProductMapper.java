package go.travel.dnh.mapper;

import go.travel.dnh.domain.air.AirProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirProductMapper {

    List<AirProductDTO> selectPro();
}
