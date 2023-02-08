package go.travel.dnh.domain.air;

import lombok.Data;

@Data
public class AirlineDTO {
    private Integer al_code;
    private String al_name;
    private String al_logo_name;
    private String al_logo_uri;
    private Integer al_logo_size;
    private String al_explain;
}
