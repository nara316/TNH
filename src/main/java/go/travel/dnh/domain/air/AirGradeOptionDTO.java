package go.travel.dnh.domain.air;

import lombok.Data;

@Data
public class AirGradeOptionDTO {
    private String agop_code;
    private String agop_name;
    private Integer agop_use;
    private Integer agop_default;
    private Integer agop_price;
    private Integer agop_baggage;
}
