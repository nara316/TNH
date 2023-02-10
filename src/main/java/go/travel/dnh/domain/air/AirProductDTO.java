package go.travel.dnh.domain.air;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AirProductDTO {
    private Integer ano;
    private Integer al_code;
    private String ar_from;
    private String ar_to;
    private String agop_code;
    private Integer ar_fl_no;
    private LocalDateTime ar_from_date;
    private Integer ar_time;
    private LocalDateTime ar_to_date;
    private Integer ar_price;
    private Integer ar_cnt;
    private Integer ar_res_cnt;
    private String ar_sales_state;
    private String in_user;
    private LocalDateTime in_date;
    private String up_user;
    private LocalDateTime up_date;

    private AirlineDTO AirlineDTO;
    private AirportDTO AirportDTO;
    private AirGradeOptionDTO AirGradeOptionDTO;

}
